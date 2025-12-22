import math

def shannon_capacity(bandwidth, signal_power, noise_power):
    """
    Calcula a capacidade do canal usando o Teorema de Shannon-Hartley.

    Args:
        bandwidth (float): Largura de banda do canal em Hertz (Hz).
        signal_power (float): Potência média do sinal em Watts.
        noise_power (float): Potência média do ruído em Watts.

    Returns:
        float: Capacidade do canal em bits por segundo (bps).
    """
    if bandwidth <= 0:
        raise ValueError("A largura de banda deve ser um valor positivo.")
    if signal_power < 0 or noise_power < 0:
        raise ValueError("A potência do sinal e a potência do ruído devem ser valores não negativos.")
    if noise_power == 0:
        # Para ruído zero, a capacidade é teoricamente infinita. Lidamos com isso retornando um valor muito grande ou infinito.
        return float('inf')

    snr_linear = signal_power / noise_power
    capacity = bandwidth * math.log2(1 + snr_linear)
    return capacity

# Exemplo de uso:
bandwidth_example = 1000000  # 1 MHz (1,000,000 Hz)
signal_power_example = 1e-3  # 1 mW (0.001 Watts)
noise_power_example = 1e-6   # 1 µW (0.000001 Watts)

capacity = shannon_capacity(bandwidth_example, signal_power_example, noise_power_example)
print(f"Largura de Banda: {bandwidth_example / 1e6} MHz")
print(f"Potência do Sinal: {signal_power_example * 1e3} mW")
print(f"Potência do Ruído: {noise_power_example * 1e6} µW")
print(f"A capacidade do canal é: {capacity / 1e6:.2f} Mbps")

# Outro exemplo com SNR em dB
def db_to_linear(db):
    return 10**(db/10)

bandwidth_db = 3000 # 3 KHz
snr_db = 20 # 20 dB
signal_to_noise_ratio_linear = db_to_linear(snr_db)
# Para usar a fórmula de Shannon, precisamos de S/N, não S e N separadamente se já tivermos a razão.
# Se não tivermos N, podemos inferir S a partir da SNR, ou usar a SNR diretamente na fórmula.
# C = B * log2(1 + SNR_linear)

capacity_db_example = bandwidth_db * math.log2(1 + signal_to_noise_ratio_linear)
print(f"\nLargura de Banda: {bandwidth_db} Hz")
print(f"SNR: {snr_db} dB")
print(f"A capacidade do canal com SNR em dB é: {capacity_db_example / 1000:.2f} kbps")
