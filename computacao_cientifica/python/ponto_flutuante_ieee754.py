"""
Sistemas de numeração e ponto flutuante IEEE 754 em Python.

Executar: python3 ponto_flutuante_ieee754.py

Observações importantes sobre Python:
  * `float` do Python é SEMPRE um double IEEE 754 (64 bits).
  * Não existe `float` de 32 bits nativo: usamos o módulo `struct` para
    "converter" um valor para 32 bits e inspecionar seus bits.
  * `int` tem precisão arbitrária: não há overflow como em Java/C.
"""

import math
import struct
import sys
from decimal import Decimal
from fractions import Fraction


def titulo(t):
    print(f"\n==================== {t} ====================")


# =====================================================================
# PARTE 1 - SISTEMAS DE NUMERAÇÃO
# =====================================================================
def parte1_sistemas_de_numeracao():
    titulo("1. Sistemas de numeração")

    # Literais: o prefixo define a base
    decimal = 42        # base 10
    binario = 0b101010  # base 2  (prefixo 0b)
    octal = 0o52        # base 8  (prefixo 0o; "052" é erro de sintaxe em Python 3)
    hexa = 0x2A         # base 16 (prefixo 0x)
    grande = 1_000_000  # '_' é só separador visual
    print("Todos valem 42?", decimal == binario == octal == hexa)
    print("Literal com separador:", grande)

    # int -> string em outras bases
    print("42 em binário :", bin(decimal))             # 0b101010
    print("42 em octal   :", oct(decimal))             # 0o52
    print("42 em hexa    :", hex(decimal))             # 0x2a
    print("sem prefixo   :", format(decimal, "b"), format(decimal, "o"), format(decimal, "x"))
    print("com zeros     :", format(decimal, "016b"))  # 0000000000101010
    print("agrupado      :", format(0xDEADBEEF, "_b")) # separador a cada 4 bits

    # string (em uma base) -> int
    print('"101010" (base 2)  =', int("101010", 2))    # 42
    print('"ff"     (base 16) =', int("ff", 16))       # 255
    print('"zz"     (base 36) =', int("zz", 36))       # 1295
    print('"0x2A"   (base 0)  =', int("0x2A", 0))      # base 0: infere pelo prefixo

    # Conversão manual: divisões sucessivas pela base
    print("Manual 42  -> base 2 :", converter_para_base(42, 2))    # 101010
    print("Manual 255 -> base 16:", converter_para_base(255, 16))  # FF

    # Frações em binário: 0.625 = 0.101b (0.5 + 0.125)
    print("0.625 em binário:", fracao_para_binario(0.625, 10))  # 0.101
    # 0.1 é dízima periódica em binário
    print("0.1   em binário:", fracao_para_binario(0.1, 20))


def converter_para_base(n, base):
    """Divisões sucessivas: o resto é o próximo dígito (menos significativo primeiro)."""
    if n == 0:
        return "0"
    digitos = "0123456789ABCDEF"
    resultado = []
    while n > 0:
        n, resto = divmod(n, base)   # quociente e resto de uma vez
        resultado.append(digitos[resto])
    return "".join(reversed(resultado))  # inverte: último resto = dígito mais significativo


def fracao_para_binario(frac, max_bits):
    """Parte fracionária: multiplica por 2 e pega a parte inteira a cada passo."""
    bits = []
    for _ in range(max_bits):
        if frac == 0:
            break
        frac *= 2
        if frac >= 1:
            bits.append("1")
            frac -= 1
        else:
            bits.append("0")
    return "0." + "".join(bits)


# =====================================================================
# PARTE 2 - INTEIROS COM SINAL: COMPLEMENTO DE DOIS
# =====================================================================
def parte2_complemento_de_dois():
    titulo("2. Complemento de dois (simulando 32 bits)")

    MASCARA32 = 0xFFFFFFFF  # 32 bits em 1

    def bin32(v):
        # Python tem int ilimitado; a máscara "recorta" para 32 bits
        return format(v & MASCARA32, "032b")

    n = 5
    # -n = ~n + 1 (inverte todos os bits e soma 1)
    print(" 5 =", bin32(n))
    print("~5 =", bin32(~n))
    print("-5 =", bin32(-n), f"(== ~5 + 1: {-n == ~n + 1})")

    def para_int32(v):
        """Interpreta os 32 bits menos significativos como int32 com sinal."""
        v &= MASCARA32
        return v - (1 << 32) if v & 0x80000000 else v

    # Faixa de um int32: -2^31 até 2^31 - 1
    int_max, int_min = 2**31 - 1, -(2**31)
    print("int32 máx =", int_max, "| mín =", int_min)

    # Python NÃO tem overflow; para imitar o comportamento de Java/C:
    print("MAX + 1 (Python)     =", int_max + 1)               # 2147483648
    print("MAX + 1 (int32 wrap) =", para_int32(int_max + 1))   # -2147483648
    print("2**100 =", 2**100)  # inteiros gigantes funcionam normalmente

    # >> em Python é sempre aritmético (mantém o sinal); não existe >>>
    print("-8 >> 1 =", -8 >> 1)                       # -4
    print("-8 >>> 28 (simulado) =", (-8 & MASCARA32) >> 28)  # 15


# =====================================================================
# PARTE 3 - ESTRUTURA DO IEEE 754: float32 e float64
# =====================================================================
#
#  float32: [ 1 sinal | 8 expoente (bias 127)  | 23 mantissa ]
#  float64: [ 1 sinal | 11 expoente (bias 1023)| 52 mantissa ]
#
#  Valor (normalizados):  (-1)^sinal x 1.mantissa x 2^(expoente - bias)
#  O "1." inicial é implícito (bit escondido).

def bits_float32(x):
    """Padrão de bits (int) de x como float32. '>f' = big-endian, 32 bits."""
    return struct.unpack(">I", struct.pack(">f", x))[0]


def bits_float64(x):
    """Padrão de bits (int) de x como float64. '>d' = big-endian, 64 bits."""
    return struct.unpack(">Q", struct.pack(">d", x))[0]


def parte3_estrutura_float():
    titulo("3. Estrutura de float32 e float64")

    b32 = format(bits_float32(6.5), "032b")
    # Esperado: 0 | 10000001 | 10100000000000000000000
    # expoente = 129 -> 129 - 127 = 2 / mantissa .101 -> 1.101b x 2^2 = 6.5
    print("float32 6.5  =", b32[0], "|", b32[1:9], "|", b32[9:])

    b64 = format(bits_float64(-6.5), "064b")
    print("float64 -6.5 =", b64[0], "|", b64[1:12], "|", b64[12:])

    print("float32 6.5 em hexa :", hex(bits_float32(6.5)))    # 0x40d00000
    print("float64 -6.5 em hexa:", hex(bits_float64(-6.5)))   # 0xc01a000000000000

    # float.hex() mostra a notação hexadecimal da mantissa e expoente
    print("(6.5).hex() =", (6.5).hex())                       # 0x1.a000000000000p+2
    print("float.fromhex('0x1.ap2') =", float.fromhex("0x1.ap2"))  # 6.5

    # Perda de precisão ao "caber" um valor em 32 bits
    x32 = struct.unpack(">f", struct.pack(">f", 0.1))[0]
    print("0.1 como float32 volta como:", x32)   # 0.10000000149011612

    # Constantes do float64 em sys.float_info
    fi = sys.float_info
    print("float64 máx         =", fi.max)       # 1.7976931348623157e+308
    print("float64 mín normal  =", fi.min)       # 2.2250738585072014e-308
    print("menor subnormal     =", 5e-324)       # 5e-324
    print("epsilon da máquina  =", fi.epsilon)   # 2.220446049250313e-16 (= 2^-52)
    print("dígitos decimais    =", fi.dig)       # 15 (garantidos)
    print("bits de mantissa    =", fi.mant_dig)  # 53 (52 + bit escondido)


# =====================================================================
# PARTE 4 - DECODIFICAÇÃO MANUAL DE UM float32
# =====================================================================
def decodificar32(x):
    bits = bits_float32(x)

    sinal = bits >> 31               # 1 bit mais significativo
    expoente = (bits >> 23) & 0xFF   # próximos 8 bits
    mantissa = bits & 0x7FFFFF       # últimos 23 bits
    fracao = mantissa / (1 << 23)    # mantissa como fração em [0, 1)

    if expoente == 0xFF:             # expoente todo 1
        tipo = "Infinito" if mantissa == 0 else "NaN"
        valor = (math.inf if sinal == 0 else -math.inf) if mantissa == 0 else math.nan
    elif expoente == 0:              # zero ou subnormal
        tipo = "Zero" if mantissa == 0 else "Subnormal"
        # Subnormais: SEM bit escondido, expoente fixo em 1 - 127 = -126
        valor = 2.0**-126 * fracao
        valor = -valor if sinal else valor
    else:                            # normal: bit escondido = 1
        tipo = "Normal"
        valor = 2.0 ** (expoente - 127) * (1.0 + fracao)
        valor = -valor if sinal else valor

    print(f"{x!s:<14} sinal={sinal} expoente={expoente:3d} (real={expoente - 127:4d}) "
          f"mantissa={mantissa:023b} [{tipo}]")
    # Decimal(valor) mostra o valor EXATO armazenado
    print(f"{'':14} valor reconstruído = {Decimal(valor) if math.isfinite(valor) else valor}")


def parte4_decodificacao_manual():
    titulo("4. Decodificando um float32 manualmente")
    decodificar32(6.5)
    decodificar32(-0.15625)   # -1.01b x 2^-3
    decodificar32(0.1)        # não é exato!
    decodificar32(1.4e-45)    # menor subnormal do float32


# =====================================================================
# PARTE 5 - VALORES ESPECIAIS
# =====================================================================
def parte5_valores_especiais():
    titulo("5. Valores especiais")

    # CUIDADO: em Python, 1.0 / 0.0 LANÇA ZeroDivisionError (diferente de Java/C)
    try:
        print(1.0 / 0.0)
    except ZeroDivisionError as e:
        print("1.0 / 0.0 ->", type(e).__name__ + ":", e)

    # Para obter os valores especiais, use math ou float()
    pos_inf = math.inf        # ou float("inf")
    neg_inf = -math.inf
    nan = math.nan            # ou float("nan")
    print("inf =", pos_inf, "| -inf =", neg_inf, "| nan =", nan)
    print("inf - inf =", pos_inf - pos_inf)    # nan
    print("inf * 0   =", pos_inf * 0)          # nan

    # NaN nunca é igual a nada, nem a ele mesmo!
    print("nan == nan      :", nan == nan)         # False
    print("math.isnan(nan) :", math.isnan(nan))    # True
    print("math.isinf(inf) :", math.isinf(pos_inf))
    print("math.isfinite(1):", math.isfinite(1.0))

    # Zero com sinal: bits diferentes, mas == diz que são iguais
    zero_pos, zero_neg = 0.0, -0.0
    print("0.0 == -0.0 :", zero_pos == zero_neg)   # True
    print("bits +0.0   :", format(bits_float64(zero_pos), "064b"))
    print("bits -0.0   :", format(bits_float64(zero_neg), "064b"))
    print("copysign(1, -0.0) =", math.copysign(1, zero_neg))  # -1.0 (detecta o sinal)

    # Overflow e underflow
    print("máx * 2   =", sys.float_info.max * 2)   # inf (sem exceção em multiplicação)
    print("5e-324 / 2 =", 5e-324 / 2)              # 0.0 (underflow)

    # Funções matemáticas podem lançar exceção em vez de retornar NaN
    try:
        math.sqrt(-1)
    except ValueError as e:
        print("math.sqrt(-1) -> ValueError:", e)
    try:
        math.exp(1000)
    except OverflowError as e:
        print("math.exp(1000) -> OverflowError:", e)


# =====================================================================
# PARTE 6 - PROBLEMAS DE PRECISÃO
# =====================================================================
def parte6_problemas_de_precisao():
    titulo("6. Problemas de precisão")

    # 0.1 e 0.2 são dízimas em binário -> são arredondados
    print("0.1 + 0.2 =", 0.1 + 0.2)               # 0.30000000000000004
    print("0.1 + 0.2 == 0.3 ?", 0.1 + 0.2 == 0.3)  # False

    # Valor EXATO que o float 0.1 guarda
    print("0.1 exato =", Decimal(0.1))
    # 0.1000000000000000055511151231257827021181583404541015625
    print("0.1 como fração =", Fraction(0.1))      # 3602879701896397/36028797018963968

    # repr usa a MENOR string que "volta" ao mesmo double (round-trip)
    print("repr(0.1) =", repr(0.1), "| 17 casas =", f"{0.1:.17f}")

    # float64 só representa inteiros exatamente até 2^53
    f = 2.0**53
    print("2^53 + 1 =", f + 1, "(o +1 se perdeu!)")  # 9007199254740992.0
    print("int 2^53 + 1 =", 2**53 + 1, "(int não perde)")

    # Absorção
    print("1e16 + 1 =", 1e16 + 1)                  # 1e+16

    # ULP = distância até o próximo float
    print("ulp(1.0)  =", math.ulp(1.0))            # 2.220446049250313e-16
    print("ulp(1e16) =", math.ulp(1e16))           # 2.0
    print("nextafter(1.0, 2.0) =", math.nextafter(1.0, 2.0))  # 1.0000000000000002

    # Soma não é associativa
    a, b, c = 1e16, -1e16, 1.0
    print("(a+b)+c =", (a + b) + c)  # 1.0
    print("a+(b+c) =", a + (b + c))  # 0.0

    # Acumulando erro
    soma = 0.0
    for _ in range(10):
        soma += 0.1
    print("10 x 0.1 =", soma, "(esperado 1.0)")   # 0.9999999999999999
    # math.fsum usa soma exata intermediária e corrige o erro
    print("fsum 10 x 0.1 =", math.fsum([0.1] * 10))  # 1.0

    # Dinheiro: Decimal a partir de STRING (nunca de float!)
    print("Decimal('0.1') + Decimal('0.2') =", Decimal("0.1") + Decimal("0.2"))  # 0.3
    print("Fraction(1, 10) + Fraction(2, 10) =", Fraction(1, 10) + Fraction(2, 10))  # 3/10

    # round() usa "arredonda para par" e sofre com a representação
    print("round(0.5) =", round(0.5), "| round(1.5) =", round(1.5), "| round(2.5) =", round(2.5))
    print("round(2.675, 2) =", round(2.675, 2), "(2.675 é na verdade 2.67499999...)")


# =====================================================================
# PARTE 7 - COMPARAÇÃO SEGURA
# =====================================================================
def parte7_comparacao_segura():
    titulo("7. Comparando números de ponto flutuante")

    a, b = 0.1 + 0.2, 0.3

    # Tolerância absoluta (boa perto de 0)
    print("abs(a - b) < 1e-9 :", abs(a - b) < 1e-9)

    # math.isclose: tolerância relativa (rel_tol padrão = 1e-9)
    print("math.isclose(a, b) :", math.isclose(a, b))
    print("isclose(1e20, 1e20+1e5):", math.isclose(1e20, 1e20 + 1e5))

    # Perto de zero, rel_tol não basta: precisa de abs_tol
    print("isclose(1e-12, 0)              :", math.isclose(1e-12, 0))                 # False
    print("isclose(1e-12, 0, abs_tol=1e-9):", math.isclose(1e-12, 0, abs_tol=1e-9))   # True

    # sorted com NaN é imprevisível, pois NaN quebra a ordem total
    print("sorted([3, nan, 1, 2]) =", sorted([3, math.nan, 1, 2]))


if __name__ == "__main__":
    parte1_sistemas_de_numeracao()
    parte2_complemento_de_dois()
    parte3_estrutura_float()
    parte4_decodificacao_manual()
    parte5_valores_especiais()
    parte6_problemas_de_precisao()
    parte7_comparacao_segura()
