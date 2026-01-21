### 1. A Pré-História: Mainframes e Lógica Centralizada (1960 - 1980)

Nesta época, o "Back-end" era tudo. O processamento era feito em grandes computadores centrais (**Mainframes**). Os utilizadores usavam "terminais burros" que não tinham processamento próprio.

* **Conceito Chave:** Processamento em lote (*Batch Processing*).
* **O Legado:** Muitas instituições bancárias ainda utilizam sistemas escritos nesta era (em **COBOL**), devido à sua alta fiabilidade no processamento de transações volumosas.

### 2. A Revolução Cliente-Servidor e o SQL (1980 - 1990)

Com o surgimento dos PCs, o processamento foi distribuído. O Back-end especializou-se na gestão de dados. Foi aqui que os **Bancos de Dados Relacionais (RDBMS)** se tornaram o padrão, graças à linguagem **SQL**.

* **Arquitetura:** O cliente (PC) tinha a lógica da interface e o servidor (Back-end) tinha a lógica dos dados.
* **Aparecimento do Java (1995):** Introduziu o conceito de "Escreva uma vez, rode em qualquer lugar", tornando o Back-end independente do hardware do servidor.

### 3. A Era do CGI e Linguagens de Script (Meados de 1990)

Quando a Web surgiu, o servidor precisava de enviar páginas diferentes para cada utilizador (ex: um perfil pessoal). O **CGI (Common Gateway Interface)** foi a primeira tentativa de fazer isto, permitindo que o servidor web "chamasse" um programa externo para gerar HTML.

* **Evolução:** Surgiram o **PHP** e o **ASP**, que permitiam misturar código de Back-end dentro do ficheiro HTML, facilitando o desenvolvimento rápido.

### 4. O Modelo MVC e os Grandes Frameworks (2000 - 2010)

As aplicações tornaram-se demasiado complexas para scripts soltos. Surgiu a necessidade de organização. O padrão **MVC (Model-View-Controller)** separou:

1. **Model:** Os dados (Back-end).
2. **View:** A interface (Front-end).
3. **Controller:** A lógica que une os dois.

* **Frameworks Icónicos:** **Ruby on Rails**, **Django (Python)** e **Spring (Java)**. Estes frameworks automatizaram tarefas repetitivas como autenticação e segurança.

### 5. A Revolução do Node.js e Event-Loop (2009 - 2015)

Até 2009, cada nova ligação ao servidor criava uma "Thread" (processo pesado). O **Node.js** introduziu o **I/O Não Bloqueante** e o **Event Loop**.

* **Impacto:** Um único servidor passou a conseguir aguentar milhares de ligações simultâneas de forma leve. O JavaScript unificou o desenvolvimento: a mesma linguagem no Front-end e no Back-end (Full Stack).

### 6. A Era Moderna: APIs, Microserviços e Serverless (2015 - Presente)

Hoje, o Back-end raramente entrega uma página pronta. Ele entrega **Dados (JSON)** através de **APIs REST** ou **GraphQL**.

* **Microserviços:** Em vez de um grande programa (Monólito), temos dezenas de pequenos programas que comunicam entre si.
* **Serverless:** Como docente de **Computação em Nuvem**, sabe que agora nem precisamos de configurar o servidor (ex: AWS Lambda). O código corre apenas quando é necessário, escalando automaticamente de zero para milhões de utilizadores.

---

### Resumo Técnico para Sala de Aula:

| Característica | Back-end Tradicional | Back-end Moderno |
| --- | --- | --- |
| **Entrega** | Página HTML completa | Dados (JSON/XML) |
| **Estado** | *Stateful* (sessões no servidor) | *Stateless* (autenticação via Tokens/JWT) |
| **Escalabilidade** | Vertical (aumentar RAM/CPU) | Horizontal (mais instâncias/Cloud) |
| **Hardware** | Servidor Físico / Data Center | Contentores (Docker) / Serverless |
