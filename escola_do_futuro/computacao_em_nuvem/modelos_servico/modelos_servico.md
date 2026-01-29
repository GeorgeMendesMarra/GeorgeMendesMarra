# ☁️ Modelos de Serviço em Computação em Nuvem

A computação em nuvem oferece recursos de TI sob demanda pela internet, com pagamento conforme o uso. Os **modelos de serviço** definem **o nível de abstração** e **a divisão de responsabilidades** entre o provedor de nuvem e o usuário.

Os três modelos clássicos são: **IaaS, PaaS e SaaS**.

---

## ☁️ IaaS – *Infrastructure as a Service*

### Infraestrutura como Serviço

É o **modelo mais básico** da computação em nuvem.

### 🔧 O que o provedor oferece:

* Servidores físicos e virtuais
* Armazenamento (discos, SSD, backups)
* Redes (IP, balanceadores, firewalls)
* Data centers
* Virtualização

### 👤 O que o usuário gerencia:

* Sistema operacional (Linux, Windows)
* Atualizações e patches
* Aplicações
* Banco de dados
* Segurança do SO
* Configurações de rede

### 📌 Exemplos de IaaS:

* **Amazon EC2 (AWS)**
* **Google Compute Engine**
* **Microsoft Azure Virtual Machines**
* **DigitalOcean**

### 🎯 Vantagens:

* Alto nível de controle
* Flexibilidade total
* Ideal para arquiteturas complexas

### ⚠️ Desvantagens:

* Exige conhecimento técnico
* Maior responsabilidade com segurança
* Manutenção é do usuário

### 📚 Uso típico:

* Hospedagem de servidores web
* Ambientes de teste e produção
* Sistemas corporativos
* Laboratórios educacionais

---

## ☁️ PaaS – *Platform as a Service*

### Plataforma como Serviço

Neste modelo, o provedor entrega **toda a plataforma pronta para desenvolvimento**.

### 🔧 O que o provedor oferece:

* Infraestrutura
* Sistema operacional
* Servidor de aplicação
* Runtime (Java, Node.js, Python, etc.)
* Escalabilidade automática
* Monitoramento

### 👤 O que o usuário gerencia:

* Código da aplicação
* Regras de negócio
* Dados

### 📌 Exemplos de PaaS:

* **Heroku**
* **Google App Engine**
* **Azure App Service**
* **AWS Elastic Beanstalk**

### 🎯 Vantagens:

* Foco total no desenvolvimento
* Menos configuração
* Alta produtividade
* Escala automática

### ⚠️ Desvantagens:

* Menor controle do ambiente
* Dependência do provedor (*vendor lock-in*)
* Limitações de configuração

### 📚 Uso típico:

* Aplicações web
* APIs REST
* Sistemas mobile
* Projetos acadêmicos

---

## ☁️ SaaS – *Software as a Service*

### Software como Serviço

É o modelo **mais comum para o usuário final**.

### 🔧 O que o provedor oferece:

* Aplicação completa
* Infraestrutura
* Segurança
* Backup
* Atualizações automáticas

### 👤 O que o usuário faz:

* Apenas usa o software via navegador ou app

### 📌 Exemplos de SaaS:

* **Google Workspace (Gmail, Drive)**
* **Microsoft 365**
* **Dropbox**
* **Trello**
* **Zoom**
* **Salesforce**

### 🎯 Vantagens:

* Nenhuma instalação
* Uso imediato
* Baixo custo inicial
* Acesso de qualquer lugar

### ⚠️ Desvantagens:

* Pouca personalização
* Dependência da internet
* Dados sob controle do provedor

---

## 🔐 Responsabilidade Compartilhada

| Camada              | IaaS     | PaaS     | SaaS     |
| ------------------- | -------- | -------- | -------- |
| Aplicação           | Usuário  | Usuário  | Provedor |
| Dados               | Usuário  | Usuário  | Provedor |
| Sistema Operacional | Usuário  | Provedor | Provedor |
| Infraestrutura      | Provedor | Provedor | Provedor |

📌 **Quanto mais alto o modelo, menos responsabilidade do usuário.**

---

## 📊 Comparação geral

| Critério          | IaaS  | PaaS  | SaaS  |
| ----------------- | ----- | ----- | ----- |
| Controle          | Alto  | Médio | Baixo |
| Complexidade      | Alta  | Média | Baixa |
| Flexibilidade     | Alta  | Média | Baixa |
| Facilidade de uso | Baixa | Média | Alta  |

---

## 🧠 Resumo para prova

✔ **IaaS** → Você monta o servidor
✔ **PaaS** → Você cria a aplicação
✔ **SaaS** → Você usa o sistema

---
