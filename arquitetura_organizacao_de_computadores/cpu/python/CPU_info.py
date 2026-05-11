#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
================================================================================
PROGRAMA: CPUInfo - Informações Completas da CPU (Python)
================================================================================
Este programa obtém e exibe todas as informações possíveis sobre a CPU do computador,
incluindo: número de núcleos, arquitetura, frequência, cache, memória RAM,
informações do sistema operacional e realiza um benchmark de performance.

Funciona em Windows, Linux e macOS.

Requer: Python 3.6+ e biblioteca 'psutil' (opcional, para informações avançadas)
Instalação: pip install psutil

Autor:
Versão: 1.0
================================================================================
"""

# ==================== IMPORTAÇÕES ====================

import os           # Módulo para comandos do sistema operacional (limpar tela, executar comandos)
import platform     # Módulo para obter informações do sistema (SO, arquitetura, versão)
import sys          # Módulo para informações do interpretador Python
import time         # Módulo para medição de tempo no benchmark
import math         # Módulo para operações matemáticas (seno, cosseno, raiz, etc.)
import subprocess   # Módulo para executar comandos no terminal e capturar sua saída
import multiprocessing  # Módulo para obter número de núcleos da CPU
import random       # Módulo para gerar números aleatórios (usado no benchmark de ordenação)

# Tenta importar a biblioteca psutil (opcional, mas recomendada)
# psutil fornece informações detalhadas de CPU, memória, discos, rede etc.
# O bloco try-except evita que o programa quebre se a biblioteca não estiver instalada
try:
    import psutil
    PSUTIL_DISPONIVEL = True  # Flag que indica se psutil está disponível
except ImportError:
    PSUTIL_DISPONIVEL = False  # Flag que indica que psutil NÃO está disponível
    print("⚠️  Aviso: Biblioteca 'psutil' não instalada.")
    print("   Para informações mais detalhadas, instale com: pip install psutil")
    print()


# ==================== FUNÇÕES AUXILIARES ====================

def limpar_tela():
    """
    Limpa a tela do console.
    Funciona em Windows (comando 'cls') e Linux/Mac (comando 'clear').
    
    Como funciona:
    - os.name retorna 'nt' para Windows, 'posix' para Linux/Mac
    - O operador ternário escolhe o comando correto baseado no SO
    """
    # os.name: 'nt' para Windows, 'posix' para Linux/Mac
    os.system('cls' if os.name == 'nt' else 'clear')


def formatar_bytes(bytes_valor: int) -> str:
    """
    Formata um valor em bytes para uma string legível (KB, MB, GB, TB).
    
    Exemplos:
    - 500 bytes → "500 B"
    - 2048 bytes → "2.00 KB"
    - 3_145_728 bytes → "3.00 MB"
    - 2_147_483_648 bytes → "2.00 GB"
    
    Args:
        bytes_valor: Valor em bytes a ser formatado (pode ser int ou float)
        
    Returns:
        String formatada com a unidade apropriada (B, KB, MB, GB, TB)
        
    Como funciona:
        Verifica em ordem crescente qual unidade é mais adequada baseada no tamanho
    """
    # Menos de 1 KB (1024 bytes)
    if bytes_valor < 1024:
        return f"{bytes_valor} B"
    # Entre 1 KB e 1 MB
    elif bytes_valor < 1024 ** 2:
        return f"{bytes_valor / 1024:.2f} KB"
    # Entre 1 MB e 1 GB
    elif bytes_valor < 1024 ** 3:
        return f"{bytes_valor / (1024 ** 2):.2f} MB"
    # Entre 1 GB e 1 TB
    elif bytes_valor < 1024 ** 4:
        return f"{bytes_valor / (1024 ** 3):.2f} GB"
    # Acima de 1 TB
    else:
        return f"{bytes_valor / (1024 ** 4):.2f} TB"


def formatar_segundos(segundos: float) -> str:
    """
    Formata um valor em segundos para uma string legível.
    
    Args:
        segundos: Valor em segundos (pode ser float)
        
    Returns:
        String formatada (ex: "1.23 segundos" ou "123.45 ms")
        
    Como funciona:
        Se o tempo for menor que 1 segundo, exibe em milissegundos (ms)
        Caso contrário, exibe em segundos
    """
    if segundos < 1:
        # Converte para milissegundos multiplicando por 1000
        return f"{segundos * 1000:.2f} ms"
    else:
        return f"{segundos:.2f} segundos"


def executar_comando(comando: str) -> str:
    """
    Executa um comando no sistema operacional e retorna sua saída.
    
    Funciona tanto para Windows (cmd.exe) quanto para Linux/Mac (bash).
    
    Args:
        comando: O comando a ser executado (ex: "wmic cpu", "lscpu", "cat /proc/cpuinfo")
        
    Returns:
        String com a saída do comando (stdout) ou mensagem de erro se falhar
        
    Como funciona:
        - Detecta o sistema operacional via os.name
        - Usa subprocess.run() para executar o comando
        - Tem timeout de 10 segundos para evitar travamentos
        - Captura tanto a saída padrão quanto a saída de erro
    """
    try:
        # Detecta o sistema operacional para usar o shell correto
        if os.name == 'nt':  # Windows
            # ['cmd.exe', '/c', comando] - /c executa o comando e fecha o terminal
            resultado = subprocess.run(
                ["cmd.exe", "/c", comando],
                capture_output=True,  # Captura a saída do comando
                text=True,            # Retorna como string (não bytes)
                timeout=10            # Timeout de 10 segundos (evita travamentos)
            )
        else:  # Linux/Mac (os.name == 'posix')
            # ['bash', '-c', comando] - -c executa o comando no shell bash
            resultado = subprocess.run(
                ["bash", "-c", comando],
                capture_output=True,
                text=True,
                timeout=10
            )
        
        # Verifica se o comando foi bem-sucedido (código de retorno 0 = sucesso)
        if resultado.returncode == 0:
            return resultado.stdout  # Retorna a saída padrão
        else:
            # Retorna mensagem de erro com o stderr
            return f"Erro: {resultado.stderr}"
            
    except subprocess.TimeoutExpired:
        # Ocorre se o comando levar mais de 10 segundos
        return "Erro: Comando excedeu o tempo limite (10 segundos)"
    except Exception as e:
        # Qualquer outro erro (comando não encontrado, permissão negada, etc.)
        return f"Erro ao executar comando: {e}"


def obter_informacoes_cpu_psutil() -> dict:
    """
    Obtém informações detalhadas da CPU usando a biblioteca psutil.
    
    Returns:
        Dicionário com informações da CPU ou None se psutil não estiver disponível
        
    Como funciona:
        psutil é uma biblioteca multiplataforma que fornece acesso a informações do sistema
        Os métodos cpu_count, cpu_freq, cpu_percent, getloadavg e cpu_times retornam
        dados detalhados sobre a CPU
    """
    # Verifica se a biblioteca psutil está disponível
    if not PSUTIL_DISPONIVEL:
        return None
    
    # Dicionário que armazenará todas as informações
    info = {}
    
    try:
        # ========== NÚCLEOS ==========
        # cpu_count(logical=False) retorna número de núcleos FÍSICOS (sem hyper-threading)
        info['fisicos'] = psutil.cpu_count(logical=False)
        
        # cpu_count(logical=True) retorna número de núcleos LÓGICOS (com hyper-threading)
        info['logicos'] = psutil.cpu_count(logical=True)
        
        # ========== FREQUÊNCIA ==========
        # cpu_freq() retorna um objeto com current, min e max (em MHz)
        freq = psutil.cpu_freq()
        if freq:
            info['freq_atual'] = freq.current      # Frequência atual (MHz)
            info['freq_min'] = freq.min            # Frequência mínima (MHz)
            info['freq_max'] = freq.max            # Frequência máxima (MHz)
        
        # ========== PORCENTAGEM DE USO ==========
        # cpu_percent(interval=0.5, percpu=True) mede o uso nos últimos 0.5 segundos
        info['uso_porcentagem'] = psutil.cpu_percent(interval=0.5, percpu=True)
        
        # uso total de todos os núcleos
        info['uso_total'] = psutil.cpu_percent(interval=0.5)
        
        # ========== CARGA MÉDIA (apenas Linux/Unix) ==========
        # getloadavg() retorna média de processos na fila nos últimos 1, 5 e 15 minutos
        load_avg = psutil.getloadavg()
        if load_avg:
            info['load1'] = load_avg[0]   # 1 minuto
            info['load5'] = load_avg[1]   # 5 minutos
            info['load15'] = load_avg[2]  # 15 minutos
        
        # ========== TEMPO DE ATIVIDADE ==========
        # cpu_times() retorna quanto tempo a CPU gastou em diferentes modos
        cpu_times = psutil.cpu_times()
        info['tempo_usuario'] = cpu_times.user      # Tempo rodando processos do usuário
        info['tempo_sistema'] = cpu_times.system    # Tempo rodando processos do sistema
        info['tempo_idle'] = cpu_times.idle         # Tempo ocioso (sem fazer nada)
        
    except Exception as e:
        print(f"Erro ao obter informações do psutil: {e}")
        return None
    
    return info


# ==================== FUNÇÕES DO MENU ====================

def exibir_menu():
    """
    Exibe o menu principal com todas as opções disponíveis.
    
    Usa caracteres especiais (Unicode) para desenhar bordas e criar uma interface amigável.
    Os caracteres '┌', '─', '┐', '│', '├', '┤', '└', '┘' formam as bordas da tabela.
    """
    print("\n" + "┌" + "─" * 65 + "┐")
    print("│" + " " * 20 + "MENU PRINCIPAL" + " " * 32 + "│")
    print("├" + "─" * 65 + "┤")
    print("│ 1 - Informações Básicas da CPU (Python puro)            │")
    print("│ 2 - Informações Avançadas (psutil + comandos do sistema)│")
    print("│ 3 - Informações do Sistema Operacional (OS)              │")
    print("│ 4 - Informações Completas (via comandos do sistema)      │")
    print("│ 5 - Benchmark Rápido da CPU                              │")
    print("│ 0 - Sair                                                 │")
    print("└" + "─" * 65 + "┘")


def informacoes_basicas_python():
    """
    Obtém informações básicas usando apenas Python puro (sem bibliotecas externas).
    
    Mostra: número de núcleos, arquitetura, versão do Python, informações do sistema,
           e (se disponível) uso da CPU e memória via psutil.
    
    Como funciona:
        - Usa módulos built-in: platform, sys, multiprocessing, os
        - Se psutil estiver disponível, mostra informações adicionais de memória e CPU
    """
    print("\n╔" + "═" * 63 + "╗")
    print("║" + " " * 16 + "INFORMAÇÕES BÁSICAS DA CPU (Python puro)" + " " * 14 + "║")
    print("╚" + "═" * 63 + "╝")
    
    # ========== INFORMAÇÕES DE HARDWARE ==========
    print("\n📌 DADOS GERAIS:")
    print("   ┌" + "─" * 57 + "┐")
    print(f"   │ {'ATRIBUTO':<25} │ {'VALOR':<30} │")
    print("   ├" + "─" * 57 + "┤")
    
    # Número de núcleos via módulo multiprocessing
    # cpu_count() retorna o número de CPUs disponíveis para o processo atual
    nucleos_fisicos = multiprocessing.cpu_count()
    print(f"   │ {'Núcleos (lógicos/threads)':<25} │ {nucleos_fisicos:<30} │")
    
    # Arquitetura do processador via platform.machine()
    # Retorna algo como 'AMD64', 'x86_64', 'arm64', etc.
    arquitetura = platform.machine()
    print(f"   │ {'Arquitetura':<25} │ {arquitetura:<30} │")
    
    # Processador (nome) via platform.processor()
    # Nem sempre disponível em todas as plataformas
    processador = platform.processor()
    if processador:
        print(f"   │ {'Processador':<25} │ {processador[:30]:<30} │")
    
    print("   └" + "─" * 57 + "┘")
    
    # ========== INFORMAÇÕES DE MEMÓRIA (se psutil disponível) ==========
    if PSUTIL_DISPONIVEL:
        # virtual_memory() retorna informações sobre a memória RAM
        mem = psutil.virtual_memory()
        # swap_memory() retorna informações sobre memória swap (disco usado como RAM)
        swap = psutil.swap_memory()
        
        print("\n📌 MEMÓRIA RAM:")
        print("   ┌" + "─" * 57 + "┐")
        print(f"   │ {'Total RAM':<25} │ {formatar_bytes(mem.total):<30} │")
        print(f"   │ {'RAM em uso':<25} │ {formatar_bytes(mem.used):<30} │")
        print(f"   │ {'RAM disponível':<25} │ {formatar_bytes(mem.available):<30} │")
        print(f"   │ {'Percentual em uso':<25} │ {mem.percent:.1f}%{' ' * 24} │")
        print(f"   │ {'Total Swap':<25} │ {formatar_bytes(swap.total):<30} │")
        print(f"   │ {'Swap em uso':<25} │ {formatar_bytes(swap.used):<30} │")
        print("   └" + "─" * 57 + "┘")
    else:
        # Mensagem informativa se psutil não estiver instalado
        print("\n   ℹ️  Informações detalhadas de memória não disponíveis.")
        print("      Instale psutil: pip install psutil")
    
    # ========== INFORMAÇÕES DO INTERPRETADOR PYTHON ==========
    print("\n📌 INFORMAÇÕES DO PYTHON:")
    print("   ┌" + "─" * 57 + "┐")
    # sys.version contém a versão completa do Python
    print(f"   │ {'Versão Python':<25} │ {sys.version.split()[0]:<30} │")
    # python_implementation() retorna 'CPython', 'PyPy', 'Jython', etc.
    print(f"   │ {'Implementação':<25} │ {platform.python_implementation():<30} │")
    # python_compiler() retorna informações do compilador usado
    print(f"   │ {'Compilador':<25} │ {platform.python_compiler():<30} │")
    print("   └" + "─" * 57 + "┘")
    
    # ========== USO DA CPU (se psutil disponível) ==========
    if PSUTIL_DISPONIVEL:
        print("\n📌 USO DA CPU:")
        print("   ┌" + "─" * 57 + "┐")
        
        # cpu_percent(interval=0.5) mede o uso médio nos últimos 0.5 segundos
        cpu_percent = psutil.cpu_percent(interval=0.5)
        print(f"   │ {'Uso total da CPU':<25} │ {cpu_percent:.1f}%{' ' * 26} │")
        
        # cpu_percent(percpu=True) retorna uma lista com o uso de cada núcleo
        cpu_percent_per_cpu = psutil.cpu_percent(interval=0.5, percpu=True)
        for i, percent in enumerate(cpu_percent_per_cpu[:8]):  # Mostra no máximo 8 núcleos
            print(f"   │ {'  Núcleo ' + str(i) + ':':<25} │ {percent:.1f}%{' ' * 26} │")
        
        # Se houver mais de 8 núcleos, informa quantos foram omitidos
        if len(cpu_percent_per_cpu) > 8:
            print(f"   │ {'  ... e mais ' + str(len(cpu_percent_per_cpu) - 8) + ' núcleos':<25} │ {' ' * 30} │")
        
        # getloadavg() retorna a carga média do sistema (disponível apenas em Unix)
        load_avg = psutil.getloadavg()
        print(f"   │ {'Carga média (1 min)':<25} │ {load_avg[0]:.2f}{' ' * 26} │")
        print(f"   │ {'Carga média (5 min)':<25} │ {load_avg[1]:.2f}{' ' * 26} │")
        print(f"   │ {'Carga média (15 min)':<25} │ {load_avg[2]:.2f}{' ' * 26} │")
        
        print("   └" + "─" * 57 + "┘")


def informacoes_avancadas():
    """
    Obtém informações avançadas da CPU usando psutil e comandos do sistema.
    
    Mostra: nome do processador, frequência, cache, virtualização, flags, etc.
    
    Como funciona:
        - Usa psutil para informações básicas (núcleos, frequência)
        - Usa comandos específicos do SO para informações detalhadas:
          * Windows: 'wmic' (Windows Management Instrumentation)
          * Linux: 'lscpu' e '/proc/cpuinfo'
          * macOS: 'sysctl'
    """
    print("\n╔" + "═" * 63 + "╗")
    print("║" + " " * 15 + "INFORMAÇÕES AVANÇADAS DA CPU" + " " * 19 + "║")
    print("╚" + "═" * 63 + "╝")
    
    # ========== INFORMAÇÕES DO PSUTIL ==========
    if PSUTIL_DISPONIVEL:
        # Obtém o dicionário com informações da CPU
        cpu_info = obter_informacoes_cpu_psutil()
        if cpu_info:
            print("\n📌 FREQUÊNCIA E NÚCLEOS:")
            print("   ┌" + "─" * 57 + "┐")
            print(f"   │ {'Núcleos físicos':<25} │ {cpu_info.get('fisicos', 'N/A'):<30} │")
            print(f"   │ {'Núcleos lógicos':<25} │ {cpu_info.get('logicos', 'N/A'):<30} │")
            print(f"   │ {'Frequência atual':<25} │ {cpu_info.get('freq_atual', 'N/A')} MHz{' ' * (23 - len(str(cpu_info.get('freq_atual', ''))))} │")
            print(f"   │ {'Frequência mínima':<25} │ {cpu_info.get('freq_min', 'N/A')} MHz{' ' * (23 - len(str(cpu_info.get('freq_min', ''))))} │")
            print(f"   │ {'Frequência máxima':<25} │ {cpu_info.get('freq_max', 'N/A')} MHz{' ' * (23 - len(str(cpu_info.get('freq_max', ''))))} │")
            print("   └" + "─" * 57 + "┘")
    
    # ========== COMANDOS DO SISTEMA ==========
    # platform.system() retorna o nome do SO: 'Windows', 'Linux', 'Darwin' (macOS)
    sistema = platform.system().lower()
    
    if sistema == 'windows':
        print("\n📌 INFORMAÇÕES DA CPU (Windows):")
        print("─────────────────────────────────────────────────────────────────")
        # wmic é a ferramenta de gerenciamento do Windows
        # /format:list exibe a saída no formato "chave=valor"
        saida = executar_comando("wmic cpu get Name,NumberOfCores,NumberOfLogicalProcessors,MaxClockSpeed,CurrentClockSpeed,L2CacheSize,L3CacheSize,Manufacturer /format:list")
        print(saida[:2000])  # Limita a saída para não poluir a tela
        
        print("\n📌 DETALHES TÉCNICOS:")
        saida = executar_comando("wmic cpu list full /format:list | findstr /i \"Name CurrentClock MaxClock L2 L3 Virtualization\"")
        print(saida[:1000])
        
    elif sistema == 'linux':
        print("\n📌 ARQUITETURA DA CPU (Linux):")
        print("─────────────────────────────────────────────────────────────────")
        # lscpu: comando que exibe informações detalhadas da CPU
        # grep filtra apenas linhas com os padrões especificados
        saida = executar_comando("lscpu | grep -E \"Model name|Architecture|CPU\\(s\\)|Thread|Core|Socket|L1|L2|L3|Virtualization|Vulnerability\"")
        print(saida)
        
        print("\n📌 INFORMAÇÕES POR NÚCLEO:")
        # /proc/cpuinfo é um arquivo virtual que contém informações detalhadas da CPU
        # head -20 limita a saída a 20 linhas
        saida = executar_comando("cat /proc/cpuinfo | grep -E \"processor|model name|cache size|cpu MHz\" | head -20")
        print(saida)
        
        print("\n📌 FLAGS DA CPU (instruções suportadas):")
        # flags indicam quais conjuntos de instruções a CPU suporta (AVX, SSE, etc.)
        saida = executar_comando("cat /proc/cpuinfo | grep flags | head -1")
        # Quebra as flags em linhas menores para melhor visualização
        if saida:
            # Remove o prefixo "flags\t\t: " da linha
            flags = saida.replace("flags\t\t: ", "").strip()
            # Divide a string em uma lista de palavras
            flags_lista = flags.split()
            # Exibe as flags em grupos de 10 para facilitar a leitura
            for i in range(0, len(flags_lista), 10):
                print("   " + " ".join(flags_lista[i:i+10]))
        
    elif sistema == 'darwin':  # macOS
        print("\n📌 ARQUITETURA DA CPU (macOS):")
        print("─────────────────────────────────────────────────────────────────")
        # sysctl é o comando para ler/modificar parâmetros do kernel no macOS
        saida = executar_comando("sysctl -n machdep.cpu.brand_string")
        print(f"   Modelo: {saida.strip()}")
        saida = executar_comando("sysctl -n hw.ncpu")
        print(f"   Núcleos: {saida.strip()}")
        saida = executar_comando("sysctl -n hw.l1icachesize hw.l1dcachesize hw.l2cachesize hw.l3cachesize")
        print(f"   Cache: {saida.strip()}")
        
    else:
        print(f"\nSistema operacional '{sistema}' não suportado para informações avançadas.")


def informacoes_sistema_operacional():
    """
    Exibe informações detalhadas do sistema operacional.
    
    Mostra: nome do SO, versão, arquitetura, usuário, diretórios do sistema,
            e informações complementares via comandos do sistema.
    
    Como funciona:
        - Usa módulo platform para informações básicas (cross-platform)
        - Usa comandos específicos do SO para informações detalhadas
    """
    print("\n╔" + "═" * 63 + "╗")
    print("║" + " " * 15 + "INFORMAÇÕES DO SISTEMA OPERACIONAL" + " " * 15 + "║")
    print("╚" + "═" * 63 + "╝")
    
    # ========== INFORMAÇÕES BÁSICAS (Python puro) ==========
    print("\n📌 SISTEMA ATUAL:")
    print("   ┌" + "─" * 57 + "┐")
    # platform.system() retorna 'Windows', 'Linux', 'Darwin'
    print(f"   │ {'Sistema':<20} │ {platform.system():<35} │")
    # platform.version() retorna a versão do sistema operacional
    print(f"   │ {'Versão':<20} │ {platform.version()[:35]:<35} │")
    # platform.machine() retorna a arquitetura do hardware
    print(f"   │ {'Arquitetura':<20} │ {platform.machine():<35} │")
    # platform.processor() retorna informações sobre o processador
    print(f"   │ {'Processador':<20} │ {platform.processor()[:35]:<35} │")
    # os.getlogin() retorna o nome do usuário logado
    print(f"   │ {'Usuário':<20} │ {os.getlogin() if hasattr(os, 'getlogin') else 'N/A':<35} │")
    # os.getcwd() retorna o diretório atual de trabalho
    print(f"   │ {'Diretório atual':<20} │ {os.getcwd()[:35]:<35} │")
    # os.path.expanduser('~') retorna o caminho para a home do usuário
    print(f"   │ {'Home do usuário':<20} │ {os.path.expanduser('~')[:35]:<35} │")
    print("   └" + "─" * 57 + "┘")
    
    # ========== INFORMAÇÕES COMPLEMENTARES ==========
    sistema = platform.system().lower()
    
    if sistema == 'windows':
        print("\n📌 COMANDOS DISPONÍVEIS (Windows):")
        print("─────────────────────────────────────────────────────────────────")
        # systeminfo exibe informações detalhadas do sistema Windows
        saida = executar_comando("systeminfo | findstr /i \"OS Name OS Version System Type\"")
        print(saida[:500])
        
    elif sistema == 'linux':
        print("\n📌 COMANDOS DISPONÍVEIS (Linux):")
        print("─────────────────────────────────────────────────────────────────")
        # uname -a exibe informações do kernel
        saida = executar_comando("uname -a")
        print(f"   Kernel: {saida.strip()}")
        # lsb_release exibe informações da distribuição Linux
        saida = executar_comando("lsb_release -a 2>/dev/null | grep -E 'Distributor|Description|Release'")
        if saida:
            print(f"   Distribuição:\n{saida}")
        else:
            # Fallback: lê o arquivo /etc/os-release (presente na maioria das distros modernas)
            saida = executar_comando("cat /etc/os-release | grep -E 'NAME|VERSION' | head -3")
            print(f"   Distribuição:\n{saida}")
        
    elif sistema == 'darwin':  # macOS
        print("\n📌 COMANDOS DISPONÍVEIS (macOS):")
        print("─────────────────────────────────────────────────────────────────")
        # sw_vers exibe informações da versão do macOS
        saida = executar_comando("sw_vers")
        print(f"   {saida.strip()}")
        saida = executar_comando("uname -a")
        print(f"   Kernel: {saida.strip()}")


def informacoes_completas():
    """
    Exibe todas as informações possíveis do sistema via comandos nativos.
    
    Em Windows: systeminfo (informações completas do sistema)
    Em Linux: /proc/cpuinfo, /proc/meminfo, lscpu, vmstat
    Em macOS: sysctl hw
    
    Como funciona:
        Executa comandos do sistema que produzem saídas extensas e detalhadas
        Limita a saída para evitar sobrecarregar o console
    """
    print("\n╔" + "═" * 63 + "╗")
    print("║" + " " * 16 + "INFORMAÇÕES COMPLETAS DO SISTEMA" + " " * 16 + "║")
    print("╚" + "═" * 63 + "╝")
    
    sistema = platform.system().lower()
    
    if sistema == 'windows':
        print("\n📌 INFORMAÇÕES COMPLETAS (Windows):")
        print("─────────────────────────────────────────────────────────────────")
        # systeminfo exibe TUDO sobre o sistema Windows
        saida = executar_comando("systeminfo")
        # Limita a saída para não sobrecarregar o console (primeiros 5000 caracteres)
        if len(saida) > 5000:
            print(saida[:5000])
            print("\n... (saída truncada, muitas informações) ...")
        else:
            print(saida)
        
    elif sistema == 'linux':
        print("\n📌 CPUINFO COMPLETO:")
        print("─────────────────────────────────────────────────────────────────")
        # /proc/cpuinfo: arquivo virtual com todas as informações da CPU
        saida = executar_comando("cat /proc/cpuinfo")
        if len(saida) > 3000:
            print(saida[:3000])
            print("\n... (saída truncada, muitas informações) ...")
        else:
            print(saida)
        
        print("\n📌 MEMINFO:")
        print("─────────────────────────────────────────────────────────────────")
        # /proc/meminfo: arquivo virtual com todas as informações de memória
        saida = executar_comando("cat /proc/meminfo")
        print(saida[:1500])
        
        print("\n📌 LSCPU:")
        print("─────────────────────────────────────────────────────────────────")
        # lscpu: comando que organiza as informações da CPU de forma legível
        saida = executar_comando("lscpu")
        print(saida[:1500])
        
    elif sistema == 'darwin':  # macOS
        print("\n📌 SYSCTL (Hardware):")
        print("─────────────────────────────────────────────────────────────────")
        # sysctl hw exibe todos os parâmetros de hardware do macOS
        saida = executar_comando("sysctl hw")
        print(saida[:2000])
        
    else:
        print(f"\nSistema '{sistema}' não suportado para informações completas.")


def benchmark_cpu():
    """
    Realiza um benchmark para testar a performance da CPU.
    
    Testes realizados:
    1. Ponto Flutuante (FPU): cálculos com seno, cosseno e raiz quadrada
    2. Inteiros (ALU): multiplicações e divisões com números inteiros
    3. Números Primos (Branches): teste de branch prediction e loops
    4. Ordenação: ordenação de listas grandes (testa memória e algoritmo)
    
    Mede o tempo de execução de cada teste e calcula operações por segundo.
    
    Como funciona:
        - Usa time.perf_counter() para medir tempo com alta precisão
        - Realiza loops pesados para estressar a CPU
        - Calcula métricas como operações por segundo
    """
    print("\n╔" + "═" * 63 + "╗")
    print("║" + " " * 21 + "BENCHMARK DA CPU" + " " * 30 + "║")
    print("╚" + "═" * 63 + "╝")
    
    print("\n🔬 Executando testes de performance...\n")
    print("   (Cada teste pode levar alguns segundos dependendo da sua CPU)\n")
    
    # Obtém o número de núcleos disponíveis (apenas para informação)
    nucleos = multiprocessing.cpu_count()
    print(f"   Núcleos disponíveis: {nucleos}")
    print("   (Os testes são executados em um único núcleo)\n")
    
    # ========== TESTE 1: PONTO FLUTUANTE (FPU) ==========
    # Este teste avalia a capacidade da Unidade de Ponto Flutuante
    # A FPU é responsável por operações com números decimais (sin, cos, sqrt)
    print("   [1/4] Teste de ponto flutuante (FPU)...")
    
    # time.perf_counter() retorna o tempo em segundos com a maior precisão disponível
    inicio_fpu = time.perf_counter()
    resultado_fpu = 0.0
    
    # Loop de 30 milhões de iterações
    # math.sin() e math.cos() são operações pesadas que exigem a FPU
    for i in range(30_000_000):
        resultado_fpu += math.sin(i) * math.cos(i) + math.sqrt(i)
    
    tempo_fpu = time.perf_counter() - inicio_fpu
    
    # ========== TESTE 2: INTEIROS (ALU) ==========
    # Este teste avalia a Unidade Lógica e Aritmética (ALU)
    # A ALU é responsável por operações com números inteiros
    print("   [2/4] Teste de inteiros (ALU)...")
