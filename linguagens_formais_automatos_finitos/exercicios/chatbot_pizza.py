#!/usr/bin/env python3
# chatbot_pizza_dfa.py
import re
import unicodedata

# ===============================
# Normalização para tolerar acentos
# ===============================

def normalize_text(s: str) -> str:
    """Remove acentos e deixa lowercase para comparação."""
    nf = unicodedata.normalize("NFKD", s.strip().lower())
    return "".join(ch for ch in nf if not unicodedata.combining(ch))


# ===============================
# Função auxiliar input
# ===============================

def ask(prompt):
    return input(prompt + "\n> ").strip()


# ===============================
# Listas originais (mantidas)
# ===============================

SABORES_RAW = ["calabresa", "mussarela", "portuguesa", "frango"]
TAMANHOS_RAW = ["pequena", "média", "media", "grande"]
BEBIDAS_RAW = [
    "coca-cola", "coca", "fanta", "pepsi", "guaraná",
    "suco", "água", "agua", "cerveja"
]

# Versões normalizadas para comparação
SABORES = [normalize_text(x) for x in SABORES_RAW]
TAMANHOS = [normalize_text(x) for x in TAMANHOS_RAW]
BEBIDAS_KEYWORDS = [normalize_text(x) for x in BEBIDAS_RAW]


# ===============================
# Função para extrair palavras com/sem acento
# ===============================

def extract_keyword(text: str, options: list):
    """
    Retorna a primeira palavra da lista encontrada no texto,
    aceitando com acento, sem acento, variações etc.
    """
    ntext = normalize_text(text)
    for opt in options:
        pattern = r"\b" + re.escape(opt) + r"\b"
        if re.search(pattern, ntext):
            return opt
    return None


# ===============================
# PROGRAMA PRINCIPAL (DFA)
# ===============================

def main():
    state = "SAUDACAO"
    pedido = {
        "sabor": None,
        "tamanho": None,
        "bebida": None,
        "endereco": None,
        "pagamento": None
    }

    print("Bot: Olá! Sou o Pizzabot. Diga 'quero pedir' para começar.")

    while True:

        # ===== ESTADO 1: SAUDAÇÃO =====
        if state == "SAUDACAO":
            txt = normalize_text(ask("Você"))
            if re.search(r"\b(quero|pedir|pedido|pizza)\b", txt):
                state = "ESCOLHER_SABOR"
                print("Bot: Que sabor você quer? (calabresa, mussarela, portuguesa, frango)")
            else:
                print("Bot: Diga 'quero pedir' quando quiser fazer um pedido.")


        # ===== ESTADO 2: ESCOLHER SABOR =====
        elif state == "ESCOLHER_SABOR":
            txt = ask("Informe o sabor")
            s = extract_keyword(txt, SABORES)

            if s:
                pedido["sabor"] = s
                state = "ESCOLHER_TAMANHO"
                print(f"Bot: {s.capitalize()} escolhido. Qual o tamanho? (pequena, média, grande)")
            else:
                print("Bot: Não entendi o sabor. Tente: calabresa, mussarela, portuguesa, frango.")


        # ===== ESTADO 3: ESCOLHER TAMANHO =====
        elif state == "ESCOLHER_TAMANHO":
            txt = ask("Informe o tamanho")
            t = extract_keyword(txt, TAMANHOS)

            if t:
                pedido["tamanho"] = "média" if t in ("media", "média") else t
                state = "ESCOLHER_BEBIDA"
                print("Bot: Deseja bebida? Diga o nome da bebida ou 'sem bebida'.")
            else:
                print("Bot: Tamanhos válidos: pequena, média, grande.")


        # ===== ESTADO 4: ESCOLHER BEBIDA =====
        elif state == "ESCOLHER_BEBIDA":
            txt = ask("Bebida (ou 'sem')")
            ntxt = normalize_text(txt)

            # Usuário não quer bebida
            if "sem" in ntxt or "nao" in ntxt:
                pedido["bebida"] = "sem bebida"
                state = "INFORMAR_ENDERECO"
                print("Bot: OK, sem bebida. Qual o endereço para entrega?")
            else:
                bebida = extract_keyword(txt, BEBIDAS_KEYWORDS)
                if bebida:
                    pedido["bebida"] = bebida
                    state = "INFORMAR_ENDERECO"
                    print(f"Bot: Bebida registrada: {bebida.capitalize()}. Agora informe o endereço.")
                else:
                    print("Bot: Não reconheci a bebida. Exemplos: coca, pepsi, suco, água.")


        # ===== ESTADO 5: INFORMAR ENDEREÇO =====
        elif state == "INFORMAR_ENDERECO":
            txt = ask("Endereço e telefone")

            if len(txt) > 5:
                pedido["endereco"] = txt.strip()
                state = "CONFIRMAR"

                resumo = (
                    f"{pedido['sabor'].capitalize()} ({pedido['tamanho']}) - "
                    f"{pedido['bebida']} - "
                    f"Entrega: {pedido['endereco']}"
                )

                print("Bot: Confirme o pedido:")
                print("Bot:", resumo)
            else:
                print("Bot: Informe um endereço válido (rua, número, telefone).")


        # ===== ESTADO 6: CONFIRMAR =====
        elif state == "CONFIRMAR":
            txt = normalize_text(ask("Digite 'confirmar', 'alterar' ou 'cancelar'"))

            if "confirm" in txt:
                state = "PAGAMENTO"

            elif "alter" in txt:
                state = "ESCOLHER_SABOR"
                print("Bot: Ok, vamos alterar. Qual novo sabor?")

            elif "cancel" in txt:
                print("Bot: Pedido cancelado. Obrigado!")
                break

            else:
                print("Bot: Opções válidas: confirmar, alterar, cancelar.")


        # ===== ESTADO 7: PAGAMENTO =====
        elif state == "PAGAMENTO":
            txt = normalize_text(ask("Como deseja pagar? 'dinheiro' ou 'cartão'"))

            if "din" in txt:
                pedido["pagamento"] = "dinheiro"
                state = "FINALIZADO"

            elif "cart" in txt:
                pedido["pagamento"] = "cartão"
                state = "FINALIZADO"

            else:
                print("Bot: Escolha 'dinheiro' ou 'cartão'.")


        # ===== ESTADO 8: FINALIZADO =====
        elif state == "FINALIZADO":
            print("\nBot: Pedido confirmado! Resumo final:")
            print(pedido)
            print("Bot: Obrigado! Sua pizza chegará em aproximadamente 30 minutos.\n")
            break


if __name__ == "__main__":
    main()
