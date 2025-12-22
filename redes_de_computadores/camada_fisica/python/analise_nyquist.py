import numpy as np
import matplotlib.pyplot as plt

# 1. Parâmetros do sinal original
f_signal = 10  # Frequência do sinal em Hertz (Hz)
duration = 1   # Duração do sinal em segundos

# Cria um eixo de tempo para o sinal contínuo com alta resolução para simular um sinal analógico.
# np.linspace(start, stop, num_points, endpoint) gera amostras espaçadas uniformemente.
t_continuous = np.linspace(0, duration, 1000, endpoint=False)
# Gera o sinal senoidal contínuo usando a frequência f_signal.
signal_continuous = np.sin(2 * np.pi * f_signal * t_continuous)

# 2. Amostragem com taxa > Taxa de Nyquist (f_s >= 2 * f_signal)
# A Taxa de Nyquist para um sinal de 10 Hz é 2 * 10 = 20 Hz.
# Escolhemos uma frequência de amostragem adequada (ex: 2.5 * 10 Hz = 25 Hz) que é maior que a Taxa de Nyquist.
fs_adequate = 2.5 * f_signal # Frequência de amostragem adequada (ex: 25 Hz)
# Cria um eixo de tempo para as amostras adequadas.
t_adequate = np.linspace(0, duration, int(fs_adequate * duration), endpoint=False)
# Amostra o sinal contínuo nos pontos de tempo definidos por t_adequate.
signal_adequate_sampled = np.sin(2 * np.pi * f_signal * t_adequate)

# 3. Amostragem com taxa < Taxa de Nyquist (f_s < 2 * f_signal) - Aliasing
# Escolhemos uma frequência de amostragem inadequada (ex: 1.5 * 10 Hz = 15 Hz) que é menor que a Taxa de Nyquist.
fs_inadequate = 1.5 * f_signal # Frequência de amostragem inadequada (ex: 15 Hz)
# Cria um eixo de tempo para as amostras inadequadas.
t_inadequate = np.linspace(0, duration, int(fs_inadequate * duration), endpoint=False)
# Amostra o sinal contínuo nos pontos de tempo definidos por t_inadequate.
signal_inadequate_sampled = np.sin(2 * np.pi * f_signal * t_inadequate)

# 4. Plotagem para demonstrar o teorema
plt.figure(figsize=(15, 10)) # Define o tamanho da figura para os gráficos.

# Gráfico 1: Sinal original e amostragem adequada
plt.subplot(2, 1, 1)         # Define o primeiro subplot (2 linhas, 1 coluna, primeira posição).
plt.plot(t_continuous, signal_continuous, label='Sinal Original (10 Hz)', color='blue') # Plota o sinal contínuo.
# Plota as amostras adequadas como "hastes" para mostrar os pontos discretos.
plt.stem(t_adequate, signal_adequate_sampled, linefmt='red', markerfmt='ro', basefmt=' ', label=f'Amostrado a {fs_adequate} Hz (fs > 2*f_signal)')
plt.title('Amostragem Adequada (fs >= Taxa de Nyquist)') # Título do gráfico.
plt.xlabel('Tempo (s)') # Rótulo do eixo X.
plt.ylabel('Amplitude') # Rótulo do eixo Y.
plt.legend() # Exibe a legenda.
plt.grid(True) # Adiciona uma grade ao gráfico.

# Gráfico 2: Sinal original e amostragem inadequada (Aliasing)
plt.subplot(2, 1, 2)         # Define o segundo subplot (2 linhas, 1 coluna, segunda posição).
plt.plot(t_continuous, signal_continuous, label='Sinal Original (10 Hz)', color='blue') # Plota o sinal contínuo.
# Plota as amostras inadequadas como "hastes".
plt.stem(t_inadequate, signal_inadequate_sampled, linefmt='green', markerfmt='go', basefmt=' ', label=f'Amostrado a {fs_inadequate} Hz (fs < 2*f_signal)')
# Para ilustrar o aliasing, podemos plotar uma onda de frequência mais baixa que "aparece" devido à subamostragem.
# A frequência aparente (aliased) pode ser calculada como |f_signal - n*fs_inadequate| onde n é um inteiro.
# Neste caso, 10 Hz amostrado a 15 Hz resultaria em um sinal aparente de |10 - 1*15| = 5 Hz.
plt.plot(t_continuous, np.sin(2 * np.pi * (fs_inadequate - f_signal) * t_continuous), '--', color='purple', label='Sinal Aparente (Aliased ~ 5 Hz)')
plt.title('Amostragem Inadequada (Aliasing)') # Título do gráfico.
plt.xlabel('Tempo (s)') # Rótulo do eixo X.
plt.ylabel('Amplitude') # Rótulo do eixo Y.
plt.legend() # Exibe a legenda.
plt.grid(True) # Adiciona uma grade ao gráfico.

plt.tight_layout() # Ajusta automaticamente os espaçamentos entre os subplots.
plt.show() # Exibe os gráficos.
