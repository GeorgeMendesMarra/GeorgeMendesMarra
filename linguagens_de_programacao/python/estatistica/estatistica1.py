import statistics
import matplotlib.pyplot as plt

# --- Dados de Exemplo ---
# Lista de anos (eixo X)
anos = [2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018]
# Lista de ocorrências/casos (eixo Y)
casos = [1, 15, 8, 12, 2, 18, 22, 25, 3, 28, 35, 32, 4, 38, 45, 42, 5, 48, 55]

# 2.1) Média Aritmética: Soma de todos os valores dividida pela quantidade de elementos
media = statistics.mean(casos)
print("Média:", media)

# 2.2) Mediana: Valor central que divide o conjunto de dados ordenado ao meio
mediana = statistics.median(casos)
print("Mediana:", mediana)

# 2.3) Quantis: Valores que dividem os dados em 4 partes iguais (Quartis)
# n=4 divide em 25%, 50% (mediana) e 75%
quartis = statistics.quantiles(casos, n=4)
print(f"Quantil (25% - Q1): {quartis[0]}")
print(f"Quantil (50% - Q2/Mediana): {quartis[1]}")
print(f"Quantil (75% - Q3): {quartis[2]}")

# 2.4) Moda: O valor (ou valores) que mais se repete no conjunto de dados
moda = statistics.mode(casos)
print("Moda:", moda)

# 2.5) Amplitude: Diferença entre o valor máximo e o valor mínimo (mostra a dispersão total)
amplitude = max(casos) - min(casos)
print("Amplitude:", amplitude)

# 2.6) Variância: Mede o quão longe os valores estão da média (em unidades quadradas)
variancia = statistics.variance(casos)
print("Variância:", variancia)

# 2.7) Desvio Padrão: Raiz quadrada da variância; indica a dispersão na mesma unidade dos dados
desvio_padrao = statistics.stdev(casos)
print("Desvio padrão:", desvio_padrao)

# 2.8) Desvio Médio Absoluto: Média das distâncias absolutas de cada ponto em relação à média
desvio_absoluto = statistics.mean([abs(x - media) for x in casos])
print("Desvio absoluto:", desvio_absoluto)

# --- Análise de duas variáveis (Relações) ---
# Criando uma segunda lista para comparar correlação (Ex: Investimento em saúde no mesmo período)
dados_2 = [5, 12, 9, 6, 3, 20, 15, 18, 2, 30, 25, 27, 4, 35, 40, 38, 8, 45, 50]

# 2.9) Covariância: Indica a direção da relação linear entre duas variáveis
covariancia = statistics.covariance(casos, dados_2)
print("Covariância:", covariancia)

# 2.10) Correlação de Pearson: Mede a força e a direção da relação (de -1 a +1)
correlacao = statistics.correlation(casos, dados_2)
print("Correlação:", correlacao)
