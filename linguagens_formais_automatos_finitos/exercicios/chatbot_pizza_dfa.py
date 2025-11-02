#!/usr/bin/env python3
# chatbot_pizza_dfa.py
import re

# Função auxiliar para obter entrada do usuário com um prompt formatado
def ask(prompt):
    # Converte a entrada para minúsculas e remove espaços iniciais/finais
    return input(prompt + "\n> ").strip().lower()

# Listas de palavras-chave para validação
SABORES = ["calabresa","mussarela","portuguesa","frango"]
TAMANHOS = ["pequena","média","media","grande"]
BEBIDAS_KEYWORDS = ["coca-cola", "coca", "fanta", "pepsi", "guaraná", "suco", "água", "agua", "cerveja"] 

# Função para extrair a primeira palavra-chave encontrada no texto
def extract_keyword(text, options):
    for opt in options:
        if opt in text:
            return opt
    return None

def main():
    # Inicialização do DFA no estado SAUDACAO
    state = "SAUDACAO"
    # Dicionário para armazenar o pedido
    pedido = {"sabor": None, "tamanho": None, "bebida": None, "endereco": None, "pagamento": None}
    
    print("Bot: Olá! Sou o Pizzabot. Diga 'quero pedir' para começar.")
    
    # Loop principal do DFA
    while True:
        
        # === ESTADO 1: SAUDACAO ===
        if state == "SAUDACAO":
            txt = ask("Você")
            # Verifica se a entrada contém palavras-chave para iniciar o pedido
            if re.search(r"\b(quero|pedir|pedido|pizza)\b", txt):
                state = "ESCOLHER_SABOR"
                print("Bot: Que sabor você quer? (calabresa, mussarela, portuguesa, frango)")
            else:
                print("Bot: Diga 'quero pedir' quando quiser fazer um pedido.")
        
        # === ESTADO 2: ESCOLHER_SABOR ===
        elif state == "ESCOLHER_SABOR":
            txt = ask("Informe o sabor")
            s = extract_keyword(txt, SABORES)
            
            if s:
                pedido["sabor"] = s
                state = "ESCOLHER_TAMANHO" # Transiciona para o próximo estado
                print(f"Bot: {s.capitalize()} escolhido. Qual o tamanho? (pequena/média/grande)")
            else:
                # Permanece no estado ESCOLHER_SABOR
                print("Bot: Não entendi o sabor. Opções: calabresa, mussarela, portuguesa, frango.")
        
        # === ESTADO 3: ESCOLHER_TAMANHO ===
        elif state == "ESCOLHER_TAMANHO":
            txt = ask("Informe o tamanho")
            t = extract_keyword(txt, TAMANHOS)
            
            if t:
                # Normaliza 'media' para 'média'
                pedido["tamanho"] = "média" if t in ("media","média") else t
                state = "ESCOLHER_BEBIDA" # Transiciona para o próximo estado
                print("Bot: Deseja bebida? Diga o nome da bebida ou 'sem bebida'.")
            else:
                # Permanece no estado ESCOLHER_TAMANHO
                print("Bot: Tamanhos válidos: pequena, média, grande.")
        
        # === ESTADO 4: ESCOLHER_BEBIDA (Corrigido para validação e novo prompt) ===
        elif state == "ESCOLHER_BEBIDA":
            txt = ask("Bebida (ou 'sem')").lower()
            
            if "sem" in txt:
                pedido["bebida"] = "sem bebida"
                state = "INFORMAR_ENDERECO" # Transiciona após a escolha
                print("Bot: OK, sem bebida. Qual o endereço para entrega?")
                
            else:
                bebida_escolhida = extract_keyword(txt, BEBIDAS_KEYWORDS)
                
                if bebida_escolhida:
                    pedido["bebida"] = bebida_escolhida.capitalize()
                    state = "INFORMAR_ENDERECO" # Transiciona após a escolha válida
                    print(f"Bot: Entendi, {bebida_escolhida.capitalize()}. Agora me diga o endereço e telefone.")
                else:
                    # Permanece no estado ESCOLHER_BEBIDA até que o usuário informe uma bebida ou diga 'sem'
                    print("Bot: Não entendi. Qual bebida você gostaria? (Ex: Coca, Suco, Água, etc.)")
                    
        # === ESTADO 5: INFORMAR_ENDERECO ===
        elif state == "INFORMAR_ENDERECO":
            txt = ask("Endereço e telefone")
            
            if len(txt) > 5:
                pedido["endereco"] = txt
                state = "CONFIRMAR" # Transiciona
                
                resumo = (f"{pedido['sabor'].capitalize()} ({pedido['tamanho']}) - "
                          f"{pedido['bebida']} - Entrega: {pedido['endereco']}")
                print("Bot: Confirme o pedido:")
                print("Bot:", resumo)
                
            else:
                # Permanece no estado INFORMAR_ENDERECO
                print("Bot: Informe um endereço válido (rua, número, telefone).")
        
        # === ESTADO 6: CONFIRMAR ===
        elif state == "CONFIRMAR":
            txt = ask("Digite 'confirmar', 'alterar' ou 'cancelar'")
            
            if "confirm" in txt or "confirmar" in txt:
                state = "PAGAMENTO" # Transiciona
            elif "alter" in txt or "alterar" in txt:
                state = "ESCOLHER_SABOR" # Volta ao início do pedido para alteração
                print("Bot: Ok, vamos alterar. Qual novo sabor?")
            elif "cancel" in txt or "cancelar" in txt:
                print("Bot: Pedido cancelado. Obrigado!")
                break # Sai do loop principal, encerrando o programa
            else:
                # Permanece no estado CONFIRMAR
                print("Bot: Não entendi. 'confirmar', 'alterar' ou 'cancelar'.")
        
        # === ESTADO 7: PAGAMENTO ===
        elif state == "PAGAMENTO":
            txt = ask("Como deseja pagar? 'dinheiro' ou 'cartão'")
            
            if "din" in txt:
                pedido["pagamento"] = "dinheiro"
                state = "FINALIZADO" # Transiciona
            elif "cart" in txt:
                pedido["pagamento"] = "cartão"
                state = "FINALIZADO" # Transiciona
            else:
                # Permanece no estado PAGAMENTO
                print("Bot: Escolha 'dinheiro' ou 'cartão'.")
        
        # === ESTADO 8: FINALIZADO ===
        elif state == "FINALIZADO":
            print("Bot: Pedido confirmado! Resumo final:")
            print(pedido)
            print("Bot: Obrigado! Sua pizza chegará em aproximadamente 30 minutos.")
            break # Sai do loop, encerrando o programa

if __name__ == "__main__":
    main()
