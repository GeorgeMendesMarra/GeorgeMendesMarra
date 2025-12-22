# =====================================================
# Importação das bibliotecas necessárias
# =====================================================

import numpy as np              # Biblioteca para cálculo numérico
import matplotlib.pyplot as plt # Biblioteca para geração de gráficos

# =====================================================
# 1. Definição dos parâmetros do sinal
# =====================================================

fs = 1000        # Frequência de amostragem em Hertz (Hz)
duracao = 1.0   # Duração do sinal em segundos
f1 = 50          # Frequência do sinal senoidal (Hz)

# =====================================================
# 2. Criação do eixo do tempo
# =====================================================
# np.linspace cria um vetor de tempo igualmente espaçado
# endpoint=False evita repetir o último ponto

t = np.linspace(0, duracao, int(fs * duracao), endpoint=False)

# =====================================================
# 3. Geração do sinal no domínio do tempo
# =====================================================
# Sinal senoidal: sin(2πft)

signal = np.sin(2 * np.pi * f1 * t)

# =====================================================
# 4. Aplicação da Transformada Rápida de Fourier (FFT)
# =====================================================
# np.fft.fft calcula a FFT do sinal no tempo
# O resultado é um vetor de números complexos

fft_signal = np.fft.fft(signal)

# =====================================================
# 5. Cálculo das frequências associadas à FFT
# =====================================================
# np.fft.fftfreq gera o eixo de frequências
# O parâmetro 1/fs representa o intervalo entre amostras

frequencias = np.fft.fftfreq(len(fft_signal), 1/fs)

# =====================================================
# 6. Cálculo da magnitude do espectro
# =====================================================
# O valor absoluto de um número complexo representa sua magnitude

magnitude = np.abs(fft_signal)

# =====================================================
# 7. Criação dos gráficos
# =====================================================

plt.figure(figsize=(12, 5))  # Define o tamanho da figura

# -------------------------------
# Gráfico do sinal no tempo
# -------------------------------
plt.subplot(1, 2, 1)         # 1 linha, 2 colunas, posição 1
plt.plot(t, signal)
plt.xlabel("Tempo (s)")
plt.ylabel("Amplitude")
plt.title("Sinal no Domínio do Tempo")

# -------------------------------
# Gráfico do espectro de frequência
# -------------------------------
plt.subplot(1, 2, 2)         # 1 linha, 2 colunas, posição 2
plt.plot(frequencias, magnitude)
plt.xlim(0, fs / 2)          # Mostra apenas frequências positivas
plt.xlabel("Frequência (Hz)")
plt.ylabel("Magnitude")
plt.title("Análise de Fourier (Domínio da Frequência)")

# Ajusta automaticamente os espaçamentos
plt.tight_layout()

# Exibe os gráficos na tela
plt.show()
