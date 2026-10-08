# Tutorial de WebServices: SOAP, WSDL, SaaS, IaaS e PaaS

> Guia introdutório sobre serviços web: o que são, como o SOAP e o WSDL funcionam, como consumir um serviço SOAP e como os modelos de computação em nuvem (SaaS, IaaS e PaaS) se relacionam com tudo isso.

## Sumário

1. [O que é um WebService](#1-o-que-é-um-webservice)
2. [XML: a base do SOAP](#2-xml-a-base-do-soap)
3. [SOAP](#3-soap)
4. [WSDL](#4-wsdl)
5. [Consumindo um serviço SOAP](#5-consumindo-um-serviço-soap)
6. [Criando um serviço SOAP em Java (visão geral)](#6-criando-um-serviço-soap-em-java-visão-geral)
7. [SOAP × REST](#7-soap--rest)
8. [Computação em nuvem: SaaS, IaaS e PaaS](#8-computação-em-nuvem-saas-iaas-e-paas)
9. [Boas práticas e segurança](#9-boas-práticas-e-segurança)
10. [Exercícios propostos](#10-exercícios-propostos)

---

## 1. O que é um WebService

Um **WebService** (serviço web) é uma funcionalidade disponibilizada por uma aplicação para ser **consumida por outras aplicações** através da rede, usando protocolos padronizados (normalmente HTTP) e formatos de dados independentes de linguagem (XML ou JSON).

> **Analogia:** um WebService é como o garçom de um restaurante. Você (cliente) não entra na cozinha: faz o pedido em um formato combinado e recebe o prato pronto.

Características principais:

- **Interoperabilidade:** um sistema em Java pode conversar com outro em PHP, C# ou Python.
- **Baixo acoplamento:** quem consome só precisa conhecer o **contrato** do serviço.
- **Independência de plataforma:** funciona sobre a infraestrutura da web.

Os dois estilos mais conhecidos são:

| Estilo | Formato típico | Contrato | Uso comum |
|---|---|---|---|
| **SOAP** | XML | WSDL | Sistemas corporativos, bancos, governo, legado |
| **REST** | JSON (ou XML) | OpenAPI (opcional) | APIs públicas, mobile, front-end moderno |

---

## 2. XML: a base do SOAP

```xml
<?xml version="1.0" encoding="UTF-8"?>
<aluno matricula="2026001">
  <nome>Maria Silva</nome>
  <curso>ADS</curso>
  <notas>
    <nota disciplina="Programação Web II">9.5</nota>
  </notas>
</aluno>
```

Regras essenciais: um único elemento raiz, tags sempre fechadas, atributos entre aspas e distinção entre maiúsculas e minúsculas. **Namespaces** (`xmlns`) evitam conflito de nomes entre vocabulários diferentes.

---

## 3. SOAP

**SOAP** (*Simple Object Access Protocol*) é um protocolo para troca de mensagens estruturadas em XML. Costuma trafegar sobre HTTP (também pode usar SMTP e outros).

### Estrutura de uma mensagem SOAP

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Header>
    <!-- Opcional: autenticação, token, controle de transação -->
  </soap:Header>
  <soap:Body>
    <!-- Obrigatório: a chamada (ou a resposta) -->
  </soap:Body>
</soap:Envelope>
```

| Elemento | Obrigatório | Função |
|---|---|---|
| `Envelope` | Sim | Raiz da mensagem |
| `Header` | Não | Metadados (segurança, roteamento) |
| `Body` | Sim | Conteúdo da requisição/resposta |
| `Fault` | Não | Descreve erros (dentro do `Body`) |

### Exemplo de requisição

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:cot="http://exemplo.com/cotacao">
  <soap:Body>
    <cot:ObterCotacao>
      <cot:moeda>USD</cot:moeda>
    </cot:ObterCotacao>
  </soap:Body>
</soap:Envelope>
```

### Exemplo de resposta

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:cot="http://exemplo.com/cotacao">
  <soap:Body>
    <cot:ObterCotacaoResponse>
      <cot:valor>5.12</cot:valor>
    </cot:ObterCotacaoResponse>
  </soap:Body>
</soap:Envelope>
```

### Exemplo de erro (Fault)

```xml
<soap:Body>
  <soap:Fault>
    <faultcode>soap:Client</faultcode>
    <faultstring>Moeda não suportada</faultstring>
  </soap:Fault>
</soap:Body>
```

### Características do SOAP

- Protocolo **formal e fortemente tipado**, com contrato obrigatório (WSDL).
- Padrões **WS-*** (WS-Security, WS-ReliableMessaging, WS-AtomicTransaction) para cenários corporativos.
- Mais **verboso** e pesado que JSON, porém com mais garantias de segurança e transação.

---

## 4. WSDL

**WSDL** (*Web Services Description Language*) é o **contrato** do serviço SOAP: um documento XML que descreve **o que** o serviço oferece, **como** chamá-lo e **onde** ele está.

### Estrutura de um WSDL

| Elemento | O que descreve |
|---|---|
| `<types>` | Tipos de dados (geralmente XSD) |
| `<message>` | Mensagens de entrada e saída |
| `<portType>` | Operações disponíveis (a "interface") |
| `<binding>` | Protocolo e formato (SOAP sobre HTTP) |
| `<service>` / `<port>` | Endereço (URL) do serviço |

### Exemplo simplificado

```xml
<definitions name="CotacaoService"
             targetNamespace="http://exemplo.com/cotacao"
             xmlns="http://schemas.xmlsoap.org/wsdl/"
             xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
             xmlns:tns="http://exemplo.com/cotacao"
             xmlns:xsd="http://www.w3.org/2001/XMLSchema">

  <message name="ObterCotacaoRequest">
    <part name="moeda" type="xsd:string"/>
  </message>
  <message name="ObterCotacaoResponse">
    <part name="valor" type="xsd:decimal"/>
  </message>

  <portType name="CotacaoPortType">
    <operation name="ObterCotacao">
      <input message="tns:ObterCotacaoRequest"/>
      <output message="tns:ObterCotacaoResponse"/>
    </operation>
  </portType>

  <binding name="CotacaoBinding" type="tns:CotacaoPortType">
    <soap:binding style="rpc" transport="http://schemas.xmlsoap.org/soap/http"/>
    <operation name="ObterCotacao">
      <soap:operation soapAction="ObterCotacao"/>
    </operation>
  </binding>

  <service name="CotacaoService">
    <port name="CotacaoPort" binding="tns:CotacaoBinding">
      <soap:address location="http://exemplo.com/ws/cotacao"/>
    </port>
  </service>
</definitions>
```

> Por convenção, o WSDL de um serviço fica disponível em uma URL terminada em `?wsdl`.

### Para que serve na prática

- **Geração automática de código cliente** (stubs) em diversas linguagens.
- **Documentação oficial** do serviço.
- **Teste** em ferramentas como SoapUI e Postman.

---

## 5. Consumindo um serviço SOAP

### 5.1 Testando com `curl`

```bash
curl -X POST http://exemplo.com/ws/cotacao \
  -H "Content-Type: text/xml; charset=utf-8" \
  -H "SOAPAction: ObterCotacao" \
  -d '<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
                     xmlns:cot="http://exemplo.com/cotacao">
        <soap:Body><cot:ObterCotacao><cot:moeda>USD</cot:moeda></cot:ObterCotacao></soap:Body>
      </soap:Envelope>'
```

### 5.2 Gerando o cliente Java a partir do WSDL

Com o plugin `jaxws-maven-plugin` (Jakarta XML Web Services), o Maven gera as classes cliente a partir do WSDL:

```xml
<plugin>
  <groupId>com.sun.xml.ws</groupId>
  <artifactId>jaxws-maven-plugin</artifactId>
  <version>4.0.2</version>
  <executions>
    <execution>
      <goals><goal>wsimport</goal></goals>
      <configuration>
        <wsdlUrls>
          <wsdlUrl>http://exemplo.com/ws/cotacao?wsdl</wsdlUrl>
        </wsdlUrls>
        <packageName>br.edu.unialfa.cotacao.client</packageName>
      </configuration>
    </execution>
  </executions>
</plugin>
```

Uso das classes geradas:

```java
CotacaoService servico = new CotacaoService();
CotacaoPortType porta = servico.getCotacaoPort();
BigDecimal valor = porta.obterCotacao("USD");
System.out.println("Cotação: " + valor);
```

> Os nomes de pacote e versões acima são ilustrativos: confira sempre a documentação atual do plugin e do serviço consumido.

### 5.3 Exemplo real: serviços públicos SOAP

Vários órgãos públicos e empresas ainda disponibilizam serviços SOAP (ex.: consulta de NF-e, serviços do Banco Central e dos Correios em versões legadas). Pesquise o WSDL oficial do serviço antes de integrar.

---

## 6. Criando um serviço SOAP em Java (visão geral)

Com **Spring Web Services** o fluxo é *contract-first*: primeiro o contrato (XSD), depois o código.

```
1. Escrever o esquema XSD (tipos de requisição/resposta)
2. Gerar as classes Java a partir do XSD (JAXB)
3. Criar o @Endpoint com @PayloadRoot
4. Expor o WSDL automaticamente (DefaultWsdl11Definition)
```

```java
@Endpoint
public class CotacaoEndpoint {

    private static final String NAMESPACE = "http://exemplo.com/cotacao";

    @PayloadRoot(namespace = NAMESPACE, localPart = "ObterCotacaoRequest")
    @ResponsePayload
    public ObterCotacaoResponse obterCotacao(@RequestPayload ObterCotacaoRequest req) {
        ObterCotacaoResponse resp = new ObterCotacaoResponse();
        resp.setValor(new BigDecimal("5.12")); // exemplo fixo
        return resp;
    }
}
```

---

## 7. SOAP × REST

| Critério | SOAP | REST |
|---|---|---|
| Natureza | Protocolo | Estilo arquitetural |
| Formato | XML | JSON, XML, texto, etc. |
| Contrato | WSDL (obrigatório) | OpenAPI (opcional) |
| Operações | Definidas no contrato (RPC) | Verbos HTTP sobre recursos |
| Peso das mensagens | Maior | Menor |
| Cache HTTP | Limitado | Nativo |
| Segurança | WS-Security | HTTPS, OAuth2, JWT |
| Transações | WS-AtomicTransaction | Não padronizado |
| Uso típico | Sistemas corporativos e legados | Web, mobile, microsserviços |

> **Regra prática:** para projetos novos, REST (ou GraphQL) costuma ser a escolha; SOAP continua relevante para **integrar com sistemas legados e governamentais**.

---

## 8. Computação em nuvem: SaaS, IaaS e PaaS

Os WebServices são a "cola" da computação em nuvem. Os três modelos de serviço diferem em **quanto da pilha tecnológica o provedor gerencia**.

### Visão geral

| Camada | On-premises | IaaS | PaaS | SaaS |
|---|---|---|---|---|
| Aplicação | Você | Você | Você | Provedor |
| Dados | Você | Você | Você | Provedor |
| Runtime / Middleware | Você | Você | Provedor | Provedor |
| Sistema operacional | Você | Você | Provedor | Provedor |
| Virtualização | Você | Provedor | Provedor | Provedor |
| Servidores / Rede / Armazenamento | Você | Provedor | Provedor | Provedor |

### 8.1 IaaS — Infraestrutura como Serviço

Você aluga **máquinas virtuais, rede e armazenamento** e administra tudo acima do hipervisor.

- **Exemplos:** Amazon EC2, Google Compute Engine, Azure Virtual Machines, DigitalOcean Droplets.
- **Quando usar:** controle total do ambiente, migração de servidores legados, cargas especiais.
- **Desvantagem:** você cuida de atualizações, segurança do SO e escalonamento.

### 8.2 PaaS — Plataforma como Serviço

Você envia o **código** e o provedor cuida de servidor, SO, runtime e escala.

- **Exemplos:** Heroku, Render, Railway, Google App Engine, Azure App Service, AWS Elastic Beanstalk.
- **Quando usar:** publicar APIs e sites rapidamente, focando no desenvolvimento.
- **Desvantagem:** menos controle e possível dependência do fornecedor (*vendor lock-in*).

### 8.3 SaaS — Software como Serviço

Você usa o **software pronto**, normalmente pelo navegador, sem instalar nada.

- **Exemplos:** Gmail, Google Workspace, Microsoft 365, Slack, Trello, Moodle hospedado, ERPs em nuvem.
- **Quando usar:** quando a necessidade já é atendida por um produto existente.
- **Relação com WebServices:** muitos SaaS oferecem **APIs REST** para integração (ex.: API do Google Calendar, do GitHub, do Stripe).

### 8.4 Resumo com a analogia da pizza

| Modelo | Analogia |
|---|---|
| On-premises | Fazer a pizza em casa, do zero |
| IaaS | Comprar a cozinha equipada e fazer a pizza |
| PaaS | Receber a massa e os ingredientes prontos, só montar e assar |
| SaaS | Pedir a pizza pronta no restaurante |

### 8.5 Outros modelos citados

- **FaaS / Serverless:** executa funções sob demanda (AWS Lambda, Cloud Functions).
- **BaaS:** back-end pronto (Firebase, Supabase).
- **CaaS:** contêineres como serviço (Amazon ECS/EKS, Google GKE) — veja o tutorial de Docker.

---

## 9. Boas práticas e segurança

- Use sempre **HTTPS** para transportar mensagens.
- Valide e **sanitize** entradas; desabilite resolução de entidades externas em parsers XML (previne **XXE**).
- Autentique chamadas (WS-Security, tokens, API keys) e aplique **limite de requisições**.
- **Versione** contratos e evite quebrar clientes existentes.
- Trate `Fault` de forma explícita e registre logs sem expor dados sensíveis.
- Defina **timeouts** e política de repetição ao consumir serviços de terceiros.
- Em nuvem, aplique o **princípio do menor privilégio** e mantenha segredos fora do código.

---

## 10. Exercícios propostos

1. Escreva um XML representando um pedido com 3 itens e um cliente.
2. Monte à mão uma mensagem SOAP de requisição e outra de resposta para uma operação `SomarNumeros(a, b)`.
3. Abra o WSDL de um serviço público e identifique `types`, `message`, `portType`, `binding` e `service`.
4. Importe um WSDL no SoapUI (ou Postman) e execute uma operação.
5. Gere um cliente Java a partir de um WSDL e exiba o resultado no console.
6. Monte um quadro comparando SOAP e REST para um sistema bancário e para um aplicativo de delivery.
7. Classifique como IaaS, PaaS ou SaaS: Google Docs, AWS EC2, Render, Dropbox, Azure App Service, Zoom.
8. Pesquise o plano gratuito de um provedor PaaS e descreva os passos para publicar uma API Java.

---

## Referências

- W3C — SOAP e WSDL: [w3.org/TR/soap](https://www.w3.org/TR/soap/) · [w3.org/TR/wsdl](https://www.w3.org/TR/wsdl/)
- Spring Web Services: [spring.io/projects/spring-ws](https://spring.io/projects/spring-ws)
- NIST SP 800-145 — definição de computação em nuvem
- SoapUI: [soapui.org](https://www.soapui.org)
