### 1. Windows (Prompt de Comando)

O Windows mantém um cache agressivo para acelerar a navegação. Para limpá-lo:

1. Abra o menu Iniciar e digite **cmd** (ou Terminal).
2. Clique com o botão direito e selecione **Executar como Administrador**.
3. Digite o comando abaixo e aperte Enter:
```cmd
ipconfig /flushdns

```


4. Você receberá a mensagem: *"Liberação do Cache do DNS Resolver bem-sucedida"*.

---

### 2. macOS

No Mac, o comando varia dependendo da versão do sistema (macOS 11 Big Sur em diante utiliza o `dscacheutil`).

1. Abra o **Terminal** (Command + Espaço e digite Terminal).
2. Digite o seguinte comando e aperte Enter (será solicitada a sua senha de usuário):
```bash
sudo dscacheutil -flushcache; sudo killall -HUP mDNSResponder

```



---

### 3. Linux

A maioria das distribuições Linux modernas utiliza o **systemd-resolved**.

1. Abra o terminal e execute:
```bash
sudo resolvectl flush-caches

```


2. Para verificar se o cache foi limpo, você pode usar:
```bash
sudo resolvectl statistics

```



---

### 4. Google Chrome (Cache Interno)

Às vezes, o sistema operacional está limpo, mas o próprio navegador mantém um cache DNS independente. Se o problema persistir apenas no Chrome:

1. Abra o Chrome e digite o seguinte na barra de endereços:
`chrome://net-internals/#dns`
2. Clique no botão **Clear host cache**.

---

### Por que limpar o cache é necessário?

* **Propagação de Domínio:** Quando você altera o IP de um servidor em um projeto de TCC ou Engenharia de Software, seu computador pode continuar tentando acessar o servidor antigo.
* **Segurança:** Previne o *DNS Spoofing* (quando um invasor "envenena" seu cache local para te redirecionar para um site falso).
* **Correção de Erros:** Resolve erros do tipo `404 Not Found` ou `IP Address Not Found` após migrações de hospedagem.
