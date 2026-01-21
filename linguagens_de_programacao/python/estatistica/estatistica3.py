import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# --- 1. IDENTIFICAÇÃO E CABEÇALHO ---
print("-------------------//-------------------//-------------------")
print("Professor: George Mendes Marra")
print("Disciplina: Bioestatística e Informática Básica")
print("-------------------//-------------------//-------------------")

# --- 2. PARTE I: SIMULAÇÃO DE DADOS ---
print("\n[PARTE 1] - Geração de Números Aleatórios")
print(f"Número: {np.random.random()}")
print(f"Vetor: {np.random.random(3)}")
print(f"Matriz 3x2:\n{np.random.random((3, 2))}")

# --- 3. PARTE II: ANÁLISE DE DADOS REAIS (BIOESTATÍSTICA) ---
try:
    # Carregar o dataset (Titanic como exemplo de treino)
    dataset = pd.read_csv('train.csv')

    # Limpeza de dados: focar em Idade (Age) e Valor da Passagem (Fare)
    df_analise = dataset[['Age', 'Fare']].dropna()

    print("\n[PARTE 2] - Análise de Idade vs. Custos")
    print(f"Média da Idade: {df_analise['Age'].mean():.2f}")
    print(f"Desvio Padrão: {df_analise['Age'].std():.2f}")

    # --- Cálculos de Interdependência ---
    # Covariância (Matriz)
    cov_matrix = np.cov(df_analise['Age'], df_analise['Fare'])
    # Correlação (Coeficiente de Pearson)
    correlacao = df_analise['Age'].corr(df_analise['Fare'])

    print(f"\nCovariância: {cov_matrix[0, 1]:.2f}")
    print(f"Correlação de Pearson: {correlacao:.4f}")

    # --- 4. PARTE III: VISUALIZAÇÃO GRÁFICA ---
    
    # Criar uma figura com dois subplots (Lado a lado)
    plt.figure(figsize=(14, 5))

    # Gráfico 1: Dispersão (Scatter Plot) - Visualiza a Correlação
    plt.subplot(1, 2, 1)
    sns.regplot(x='Age', y='Fare', data=df_analise, scatter_kws={'alpha':0.3}, line_kws={'color':'red'})
    plt.title(f'Gráfico de Dispersão (Correlação: {correlacao:.2f})')
    plt.xlabel('Idade (Age)')
    plt.ylabel('Valor/Custo (Fare)')

    # Gráfico 2: Mapa de Calor (Heatmap) - Visualiza a Matriz de Correlação
    plt.subplot(1, 2, 2)
    matriz_corr = df_analise.corr()
    sns.heatmap(matriz_corr, annot=True, cmap='coolwarm', fmt=".2f")
    plt.title('Mapa de Calor da Correlação')

    # Exibir os gráficos
    plt.tight_layout()
    plt.show()

except FileNotFoundError:
    print("\nERRO: Ficheiro 'train.csv' não encontrado para análise real.")

print("\n-------------------//-------------------//-------------------")
