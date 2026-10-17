# 💻 Análise Tecnológica: *O Menino da Internet – A História de Aaron Swartz*

> **Filme:** *The Internet's Own Boy* (2014), de Brian Knappenberger
> **Disciplina:** Teoria da Computação
> **Objetivo:** identificar as tecnologias, conceitos e dilemas técnicos presentes na vida de Aaron Swartz e relacioná-los com os fundamentos da computação.

---

## 🗺️ 1. Linha do tempo tecnológica

| Ano | Marco | Tecnologia / conceito |
|---|---|---|
| 1986 | Nasce Aaron Swartz (Chicago) | — |
| 1986 | Lei **CFAA** (*Computer Fraud and Abuse Act*) | Base jurídica do processo futuro |
| ~1999–2000 | Aos 14 anos, participa da especificação **RSS 1.0** | XML, RDF, sindicalização de conteúdo |
| ~2001 | Trabalha com o **Creative Commons** | Licenças com metadados legíveis por máquina |
| 2004 | Colabora na criação do **Markdown** (com John Gruber) | Linguagem de marcação leve |
| 2005 | Cria o **web.py** e a **Infogami**; fusão com o **Reddit** | Framework web em Python, agregador social |
| 2008 | Baixa documentos do **PACER**; escreve o *Guerilla Open Access Manifesto* | Scraping, dados públicos, acesso aberto |
| 2010 | Funda a **Demand Progress** | Mobilização online, petições, e-mail em massa |
| 2010–2011 | Download massivo de artigos do **JSTOR** via rede do **MIT** | Automação, redes, controle de acesso |
| 2012 | Campanha contra **SOPA/PIPA** | Ativismo digital, apagões de sites (*blackout*) |
| 2013 | Falecimento de Aaron | — |

> 📌 Os itens do Markdown, Infogami, PACER (detalhes) e manifesto vêm do contexto histórico de Aaron, e nem todos são detalhados no filme.

---

## 🧱 2. Tecnologias criadas ou influenciadas por Aaron

### 2.1 RSS 1.0 (RDF Site Summary)
**O que é:** formato para publicar a lista de novidades de um site, de modo que programas (leitores de *feeds*) possam consumi-la automaticamente.

**Como funciona:**
1. O site publica um arquivo XML com título, link e resumo de cada item.
2. O leitor de *feeds* consulta esse arquivo periodicamente (*polling*).
3. Itens novos aparecem para o usuário, sem que ele precise visitar o site.

**Conceitos envolvidos:** XML, RDF (modelo de dados em triplas sujeito–predicado–objeto), padrões abertos, interoperabilidade.

**Observação histórica:** a família RSS tem versões com filosofias diferentes (RSS 1.0 baseado em RDF, RSS 2.0 mais simples; depois surgiu o Atom). É um bom exemplo de como padrões abertos evoluem por disputa e consenso.

**Exemplo didático (arquivo RSS simplificado):**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0">
  <channel>
    <title>Blog de Computação</title>
    <link>https://exemplo.com.br</link>
    <description>Novidades da disciplina</description>
    <item>
      <title>Aula 1: Autômatos finitos</title>
      <link>https://exemplo.com.br/aula1</link>
    </item>
    <item>
      <title>Aula 2: Máquinas de Turing</title>
      <link>https://exemplo.com.br/aula2</link>
    </item>
  </channel>
</rss>
```

**Exemplo didático (leitura em Python):**

```python
import xml.etree.ElementTree as ET

arquivo = ET.parse("feed.xml")
for item in arquivo.getroot().iter("item"):
    titulo = item.findtext("title")
    link = item.findtext("link")
    print(f"{titulo} -> {link}")
```

### 2.2 web.py
- Framework web **minimalista** em Python, criado em 2005.
- Seguia a ideia de "a menor quantidade de código para expor uma URL".
- Foi usado nas primeiras versões do **Reddit** (após a migração do código original para Python).
- Conceitos: roteamento de URLs, mapeamento URL → classe, padrão MVC simplificado.

```python
import web

urls = ("/", "Index")

class Index:
    def GET(self):
        return "Olá, web!"

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()
```

### 2.3 Creative Commons
- Não é um software, mas uma **camada jurídica + técnica**: cada licença (CC BY, CC BY-SA, CC BY-NC etc.) pode ser descrita em **metadados legíveis por máquina**, permitindo que mecanismos de busca filtrem conteúdo por permissão de uso.
- Conceitos: licenciamento, metadados, web semântica.

### 2.4 Reddit (e o modelo de agregador social)
- Sistema de **submissão de links + votação + comentários em árvore**.
- Conceitos: algoritmos de ranqueamento (*hot*, *top*), estruturas de dados em árvore para comentários, moderação distribuída.

### 2.5 Markdown
- Linguagem de marcação leve que converte texto simples em HTML.
- Exemplo de **linguagem formal** com gramática relativamente simples, pensada para legibilidade humana. É a mesma linguagem usada neste arquivo.

---

## 🕸️ 3. O caso PACER e o JSTOR sob a ótica técnica

### 3.1 PACER (2008)
- **PACER** é o sistema de acesso a documentos de tribunais federais dos EUA, que cobrava por página.
- Em 2008, havia um programa-piloto de acesso gratuito em bibliotecas. Aaron automatizou a coleta de grande volume de documentos, que depois foram disponibilizados publicamente.
- Houve investigação do FBI, e **nenhuma acusação** foi apresentada.
- **Lição técnica:** documentos públicos também podem estar atrás de barreiras de cobrança e usabilidade.

### 3.2 JSTOR e MIT (2010–2011)

| Camada | Elemento | Observação |
|---|---|---|
| **Aplicação** | Repositório JSTOR | Controle de acesso por instituição assinante |
| **Rede** | Rede do MIT, com acesso amplo | O MIT era assinante, e a rede permitia acesso a convidados |
| **Automação** | Script de download em lote | Equivalente a um *crawler/scraper* |
| **Defesa** | Bloqueios por endereço de rede | JSTOR e MIT tentaram limitar o tráfego |
| **Armazenamento** | Disco externo | Volume de milhões de artigos |

**Conceitos técnicos relacionados:**
- **Autenticação vs. autorização:** quem é você vs. o que você pode fazer.
- **Controle de acesso por IP/rede:** simples de implementar, fácil de contornar e, por isso, frágil como mecanismo de segurança.
- **Limitação de taxa (*rate limiting*):** a defesa técnica padrão contra download em massa.
- **Termos de uso vs. barreira técnica:** a violação contratual é diferente de invasão técnica, e essa diferença é o coração do debate jurídico.

> ⚠️ **Nota didática:** este material descreve o caso em nível conceitual, para fins de análise ética e jurídica. Não é um guia para contornar mecanismos de proteção de sistemas.

---

## ⚖️ 4. A CFAA e a tecnologia

A **Computer Fraud and Abuse Act (1986)** criminaliza o acesso "não autorizado" ou que "exceda a autorização" em computadores protegidos.

**Problemas técnicos apontados por críticos:**
1. **"Autorização" é um conceito vago**, e os sistemas variam muito.
2. A lei é anterior à web, às redes sociais e à computação em nuvem.
3. Violar termos de serviço poderia, em tese, ser enquadrado como crime.
4. As penas se acumulam por contagem de acusações (*counts*), o que gera sentenças potenciais enormes.

**Pergunta para a computação:** como especificar formalmente "acesso autorizado" em um sistema? Em segurança da informação, isso se traduz em **políticas de controle de acesso** (ACLs, RBAC, MAC). A lei, porém, não define o conceito com esse rigor.

---

## 📢 5. Ativismo digital e SOPA/PIPA

**SOPA** (*Stop Online Piracy Act*) e **PIPA** (*Protect IP Act*) eram projetos de lei que previam bloquear sites acusados de violar direitos autorais.

**Pontos técnicos criticados:**
- **Bloqueio por DNS:** altera a resolução de nomes, o que pode afetar a segurança e a integridade do sistema de nomes (DNSSEC).
- **Filtragem em larga escala:** difícil de implementar sem afetar conteúdo legítimo.
- **Responsabilidade dos intermediários:** plataformas teriam de monitorar o conteúdo de usuários.

**Ferramentas do ativismo:** petições online, e-mails em massa, campanhas em redes sociais e o *blackout* de sites em protesto (janeiro de 2012). O projeto foi retirado de pauta depois da mobilização.

---

## 🧮 6. Conexões com a Teoria da Computação

| Conceito da disciplina | Relação com o filme |
|---|---|
| **Linguagens formais e gramáticas** | XML, RSS e Markdown são linguagens com sintaxe definida, processadas por *parsers* |
| **Autômatos e expressões regulares** | Extração de dados de textos; contudo, HTML aninhado não é uma linguagem regular, por isso *parsers* de verdade são necessários |
| **Estruturas de dados** | Árvores de comentários (Reddit), grafos de links (web) |
| **Complexidade e escala** | O custo de copiar informação cai a quase zero; o que era inviável manualmente vira tarefa de horas |
| **Algoritmos de ranqueamento** | Votação e ordenação de conteúdo no Reddit |
| **Computabilidade e limites** | O que é possível automatizar não é necessariamente permitido |
| **Segurança da informação** | Controle de acesso, autenticação, auditoria e registros (*logs*) |
| **Ética em computação** | Responsabilidade de quem constrói e de quem regulamenta a tecnologia |

---

## 🔍 7. Análise crítica

**Pontos fortes do filme do ponto de vista tecnológico:**
- Mostra a computação como **ferramenta social**, e não apenas técnica.
- Apresenta padrões abertos (RSS, Creative Commons) como infraestrutura de bem comum.
- Evidencia o descompasso entre **velocidade tecnológica** e **velocidade legislativa**.

**Limitações:**
- Pouco detalhamento técnico de como os sistemas foram construídos ou de como a coleta foi realizada, pois o foco é humano e político.
- Visão predominantemente favorável a Aaron, o que exige leitura crítica do espectador.

---

## 🗣️ 8. Questões para discussão

1. Por que o **RSS**, tão importante nos anos 2000, perdeu espaço para as redes sociais? O que isso diz sobre padrões abertos vs. plataformas fechadas?
2. Controle de acesso por **rede/IP** é um bom mecanismo de segurança? Quais seriam alternativas?
3. Como definiria formalmente "acesso não autorizado" em um sistema de computação?
4. A automação em larga escala (*scraping*) deve ter limites legais, técnicos ou éticos? Quais?
5. Que responsabilidade têm os desenvolvedores quando suas ferramentas são usadas em contextos polêmicos?
6. Quais as consequências técnicas de bloquear sites por DNS (caso SOPA)?

---

## ✅ 9. Atividade sugerida

1. Escolher um site com *feed* RSS e escrever um programa (em Java ou Python) que leia e exiba os títulos.
2. Pesquisar as diferenças entre **RSS 1.0, RSS 2.0 e Atom** e montar uma tabela comparativa.
3. Ler os termos de uso de um serviço conhecido e identificar o que é permitido ou proibido em relação à coleta automatizada de dados (*robots.txt* também conta).
4. Redigir um breve parecer: "Até onde vai o limite entre acesso a dados e invasão de sistemas?"

---

## 🔗 10. Referências sugeridas

- Documentário *The Internet's Own Boy* (2014): <https://www.youtube.com/watch?v=qm4NRSa9T2Q>
- Site do **Creative Commons**: <https://creativecommons.org>
- **Electronic Frontier Foundation (EFF)**: <https://www.eff.org>
- Textos e blog do próprio Aaron Swartz (aaronsw.com, arquivados online)

> 🔎 Detalhes históricos (datas, números de documentos, contagens de acusações) devem ser conferidos nas fontes primárias antes de uso em avaliações.
