import psutil
import datetime

# --- 1. Informações de CPU ---
print(f"Uso da CPU (último segundo): {psutil.cpu_percent(interval=1)}%")

# --- 2. Informações de Memória ---
mem = psutil.virtual_memory()
print(f"Memória Total: {mem.total / (1024**3):.2f} GB")
print(f"Memória Disponível: {mem.available / (1024**3):.2f} GB")
print(f"Uso da Memória: {mem.percent}%")

# --- 3. Tempo de Funcionamento (Boot Time) ---
boot_time = datetime.datetime.fromtimestamp(psutil.boot_time())
print(f"Sistema ligado desde: {boot_time.strftime('%Y-%m-%d %H:%M:%S')}")
