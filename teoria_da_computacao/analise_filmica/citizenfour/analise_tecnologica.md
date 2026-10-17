# Análise Tecnológica do Documentário *Citizenfour* (2014)

**Disciplina:** Teoria da Computação · **Atividade:** Análise fílmica

> Este arquivo trata dos **conceitos técnicos** por trás do documentário: vigilância de comunicações, metadados, criptografia, segurança operacional e ameaça interna. A leitura do filme como obra de cinema está em [`analise_filmica.md`](analise_filmica.md). Para a história da criptografia mecânica, veja [`../enigma/analise_tecnologica.md`](../enigma/analise_tecnologica.md).
>
> **Nota:** este é um material educativo, com exemplos simplificados e dados fictícios. Ele não é um guia de segurança operacional.

## Objetivos de aprendizagem

Ao final desta análise, o estudante deverá ser capaz de:

1. descrever o **ciclo técnico** de um sistema de vigilância de comunicações (coleta, armazenamento, análise);
2. explicar a diferença entre **conteúdo** e **metadados** e por que metadados em grande volume são reveladores;
3. relacionar a análise de metadados com **grafos** e **busca em largura**;
4. distinguir **criptografia simétrica**, **assimétrica** e **híbrida**, e explicar os limites da criptografia;
5. identificar riscos de **ameaça interna** e princípios de **controle de acesso**;
6. relacionar o tema com **Teoria da Computação** (complexidade, funções de mão única, P × NP);
7. discutir **ética profissional** e legislação aplicável à proteção de dados.

## Sumário

1. [Introdução](#1-introdução)
2. [O ciclo técnico da vigilância](#2-o-ciclo-técnico-da-vigilância)
3. [Programas e bases legais citados nas revelações](#3-programas-e-bases-legais-citados-nas-revelações)
4. [Metadados](#4-metadados)
5. [Grafos e os "saltos" (*hops*)](#5-grafos-e-os-saltos-hops)
6. [Criptografia](#6-criptografia)
7. [Segurança operacional e modelagem de ameaças](#7-segurança-operacional-e-modelagem-de-ameaças)
8. [Ameaça interna e controle de acesso](#8-ameaça-interna-e-controle-de-acesso)
9. [Efeitos técnicos e o debate sobre *backdoors*](#9-efeitos-técnicos-e-o-debate-sobre-backdoors)
10. [Conexões com a Teoria da Computação](#10-conexões-com-a-teoria-da-computação)
11. [Comparações históricas](#11-comparações-históricas)
12. [Ética profissional e legislação](#12-ética-profissional-e-legislação)
13. [Atividades](#13-atividades)
14. [Glossário](#14-glossário)
15. [Considerações finais](#15-considerações-finais)
16. [Referências](#referências)

---

## 1. Introdução

*Citizenfour* mostra, em tempo quase real, como informações sobre programas de vigilância chegaram à imprensa. Para a Computação, o filme é interessante porque quase todo o enredo depende de **tecnologia da informação**: comunicações criptografadas entre uma fonte e uma cineasta, programas que coletam registros de comunicação, e um funcionário com acesso privilegiado a sistemas.

O documentário não é um tutorial técnico. Ele mostra pessoas explicando e se protegendo, mas pouco detalha **como** os sistemas funcionam. Esta análise preenche essa lacuna com conceitos de base.

> **Importante:** o filme e as revelações a que ele se refere continuam sendo objeto de debate político e jurídico. Esta análise descreve os **conceitos técnicos** e o que foi documentado publicamente, sem tomar posição sobre a decisão de Edward Snowden.

---

## 2. O ciclo técnico da vigilância

Qualquer sistema que monitora comunicações em larga escala pode ser descrito por um ciclo de etapas:

```text
Coleta  →  Armazenamento  →  Indexação  →  Consulta  →  Análise  →  Ação
```

| Etapa | Pergunta técnica | Exemplo de conceito de Computação |
| --- | --- | --- |
| **Coleta** | De onde vêm os dados? | Redes, interceptação, integração com provedores |
| **Armazenamento** | Como guardar enormes volumes? | Bancos de dados, sistemas distribuídos, retenção |
| **Indexação** | Como encontrar algo rapidamente? | Índices, estruturas de dados, busca |
| **Consulta** | Quem pode perguntar o quê? | Controle de acesso, autorização, auditoria |
| **Análise** | O que os dados revelam? | Grafos, estatística, mineração de dados, IA |
| **Ação** | O que se faz com o resultado? | Decisão humana, processos legais |

Observe que a etapa de **consulta** é onde entram as garantias de privacidade: quem autoriza, quem registra e quem audita cada pergunta. A discussão pública sobre vigilância costuma girar em torno dessa etapa, e não apenas da coleta.

---

## 3. Programas e bases legais citados nas revelações

A tabela resume o que foi **tornado público** sobre os principais programas. Ela mistura o que aparece no filme com o que foi documentado em outras reportagens e relatórios oficiais.

| Programa / base | Descrição resumida | Ponto técnico relevante |
| --- | --- | --- |
| **Seção 215** (Lei Patriota) | Coleta, em volume, de registros de ligações telefônicas (os chamados *telephony metadata*) obtidos de operadoras por ordem judicial secreta | Registros incluem números das duas pontas, identificadores, horário e duração da chamada, mas não o conteúdo |
| **PRISM** (Seção 702 da FISA) | Obtenção de comunicações de provedores de serviços de internet, a partir de seletores relacionados a alvos | As empresas contestaram a ideia de "acesso direto irrestrito" aos seus servidores; o debate sobre o alcance exato continuou |
| **Coleta *upstream*** (Seção 702) | Coleta de comunicações em pontos da infraestrutura da internet | Envolve tráfego de rede em larga escala |
| **Tribunal FISC/FISA** | Corte que autoriza ordens de vigilância | Atuava de forma sigilosa |

> **Observação:** a Seção 215 teve sua coleta em massa limitada por reformas: em 2014, o número de "saltos" permitidos em consultas foi reduzido de três para dois, e a **USA Freedom Act (2015)** proibiu a coleta em massa nesse formato, passando a exigir consultas direcionadas. Os desdobramentos continuam mudando, então **confira fontes atualizadas** antes de usar em aula.

---

## 4. Metadados

### 4.1 Conteúdo × metadados

| | Conteúdo | Metadados |
| --- | --- | --- |
| Ligação telefônica | O que foi dito | Quem ligou, para quem, quando, por quanto tempo |
| Mensagem | O texto escrito | Remetente, destinatário, horário, tamanho |
| E-mail | Corpo da mensagem | Cabeçalhos (remetente, destinatário, assunto, data) |
| Navegação | Páginas lidas | Endereços acessados, horários, volume |

### 4.2 Por que metadados são reveladores

Um único registro diz pouco. Milhões de registros, cruzados, permitem **inferir**:

- relações próximas (quem fala com quem, e com que frequência);
- rotina (horários de sono, deslocamentos);
- interesses e situações pessoais (por exemplo, contatos recorrentes com determinados serviços);
- estrutura de grupos (quem é central em uma rede).

### 4.3 Demonstração com dados fictícios

O código abaixo analisa registros **inventados** para mostrar que inferências são possíveis **sem acessar nenhum conteúdo**:

```python
# Registros de chamadas FICTÍCIOS: (origem, destino, hora, duração em minutos)
from collections import Counter, defaultdict

registros = [
    ("Ana",   "Bruno",     "08:10", 2),
    ("Ana",   "Bruno",     "22:45", 35),
    ("Ana",   "Clínica X", "09:00", 3),
    ("Ana",   "Clínica X", "09:05", 1),
    ("Bruno", "Ana",       "23:10", 40),
    ("Bruno", "Carla",     "14:00", 5),
    ("Carla", "Diego",     "14:30", 7),
    ("Carla", "Clínica X", "10:00", 4),
]

# 1) Quem fala com quem, e com que frequência
pares = Counter((o, d) for o, d, _, _ in registros)
print("Pares mais frequentes:", pares.most_common(3))

# 2) Ligações noturnas longas (possível relação próxima)
noturnas = [(o, d, dur) for o, d, h, dur in registros if h >= "22:00" and dur >= 30]
print("Noturnas longas:", noturnas)

# 3) Quem contata o mesmo serviço
contatos = sorted({o for o, d, _, _ in registros if d == "Clínica X"})
print("Contataram 'Clínica X':", contatos)
```

Saída esperada:

```text
Pares mais frequentes: [(('Ana', 'Bruno'), 2), (('Ana', 'Clínica X'), 2), (('Bruno', 'Ana'), 1)]
Noturnas longas: [('Ana', 'Bruno', 35), ('Bruno', 'Ana', 40)]
Contataram 'Clínica X': ['Ana', 'Carla']
```

Mesmo com oito registros, já é possível levantar hipóteses sobre relacionamentos e hábitos. Com milhões de registros e análise automatizada, o poder inferencial cresce muito.

---

## 5. Grafos e os "saltos" (*hops*)

Registros de comunicação formam naturalmente um **grafo**: cada pessoa (ou número) é um **vértice**, e cada contato é uma **aresta**.

Nas discussões sobre a Seção 215, falava-se em **saltos** (*hops*): o primeiro salto são os contatos diretos de um número de interesse; o segundo, os contatos desses contatos; e assim por diante. Isso é exatamente a **busca em largura (BFS)** em um grafo, limitada a uma profundidade máxima.

```python
from collections import defaultdict

grafo = defaultdict(set)
for o, d, _, _ in registros:
    grafo[o].add(d)
    grafo[d].add(o)

def saltos(inicio, max_saltos):
    visto = {inicio}
    fronteira = {inicio}
    for s in range(1, max_saltos + 1):
        proximos = set()
        for x in fronteira:
            proximos |= grafo[x] - visto
        visto |= proximos
        fronteira = proximos
        print(f"  salto {s}:", sorted(proximos))

print("Contatos de Ana até 3 saltos:")
saltos("Ana", 3)
```

Saída esperada (com os registros fictícios da seção 4):

```text
Contatos de Ana até 3 saltos:
  salto 1: ['Bruno', 'Clínica X']
  salto 2: ['Carla']
  salto 3: ['Diego']
```

### 5.1 Por que o número de saltos importa

O alcance cresce **rapidamente** a cada salto. Se cada pessoa tiver, em média, 100 contatos, o terceiro salto pode alcançar da ordem de 100³ = 1.000.000 de pessoas a partir de um único ponto de partida. Por isso, a decisão de reduzir o limite de três para dois saltos, em 2014, foi um ponto técnico importante: ela reduz o número de pessoas atingidas por cada consulta.

> **Conexão com a disciplina:** grafos, busca em largura e crescimento exponencial do alcance são conteúdos clássicos de Estruturas de Dados e Algoritmos, aplicados aqui a um problema social relevante.

---

## 6. Criptografia

### 6.1 Por que ela aparece no filme

A comunicação inicial entre a cineasta e a fonte só foi possível com **e-mail criptografado**. Sem isso, a mensagem poderia ser lida por quem a interceptasse. O filme mostra a criptografia como **condição** para que a denúncia chegasse à imprensa.

### 6.2 Tipos de criptografia

| Tipo | Como funciona | Vantagem | Limitação |
| --- | --- | --- | --- |
| **Simétrica** | A mesma chave cifra e decifra (exemplo: AES) | Rápida | Exige combinar a chave por um canal seguro |
| **Assimétrica** | Um par de chaves: **pública** (cifra ou verifica) e **privada** (decifra ou assina). Exemplos: RSA, curvas elípticas | Não exige compartilhar segredo prévio | Mais lenta |
| **Híbrida** | Usa a assimétrica para trocar uma chave de sessão e a simétrica para cifrar o conteúdo | Combina segurança e velocidade | Complexidade maior |

Ferramentas como o **PGP/GPG** usam o modelo **híbrido**: o texto é cifrado com uma chave simétrica de sessão, e essa chave é cifrada com a chave pública do destinatário.

### 6.3 Assinatura digital e verificação de identidade

Além de cifrar, a criptografia assimétrica permite **assinar**: o remetente usa sua chave privada, e qualquer pessoa pode verificar com a chave pública. Isso responde a uma pergunta essencial no filme: **como saber que o interlocutor é quem diz ser?** A verificação de **impressões digitais (*fingerprints*) de chaves** por um canal independente é uma resposta técnica a esse problema.

### 6.4 Exemplo didático: RSA de brinquedo

O código abaixo usa números **minúsculos**, apenas para mostrar a ideia. **Nunca** use algo assim para proteger dados reais.

```python
# RSA de brinquedo (números pequenos, apenas didático)
p, q = 61, 53
n = p * q                 # 3233
phi = (p - 1) * (q - 1)   # 3120
e = 17                    # expoente público
d = pow(e, -1, phi)       # expoente privado (2753)

mensagem = 65
cifrada = pow(mensagem, e, n)
decifrada = pow(cifrada, d, n)
print("n =", n, "| e =", e, "| d =", d)
print("original:", mensagem, "-> cifrada:", cifrada, "-> decifrada:", decifrada)
```

Saída esperada:

```text
n = 3233 | e = 17 | d = 2753
original: 65 -> cifrada: 2790 -> decifrada: 65
```

A chave pública é o par (n, e) e a privada é d. A segurança real do RSA vem de usar números com centenas de dígitos, cuja fatoração é considerada computacionalmente inviável com os algoritmos clássicos conhecidos (ver [seção 10](#10-conexões-com-a-teoria-da-computação)).

### 6.5 O que a criptografia **não** protege

Entender os limites é tão importante quanto entender a técnica:

| Limite | Explicação |
| --- | --- |
| **Metadados** | No e-mail com PGP, o corpo é cifrado, mas cabeçalhos como remetente, destinatário, data e assunto, em geral, ficam visíveis |
| **O dispositivo** | A cifragem protege a mensagem em trânsito; se o computador ou o celular estiverem comprometidos, o conteúdo pode ser lido antes de cifrar ou depois de decifrar |
| **Fator humano** | Senhas fracas, engenharia social e erros operacionais contornam a criptografia |
| **Sigilo futuro** | Sistemas sem *forward secrecy* (como o PGP por e-mail) podem ter mensagens antigas expostas se a chave privada vazar no futuro |
| **Anonimato** | Cifrar o conteúdo não esconde **quem** se comunica com **quem**; para isso existem ferramentas de anonimato |

Segundo relatos sobre o caso, ferramentas voltadas à proteção de fontes, como o **Tor** (rede de anonimato) e o sistema **Tails**, fazem parte do ecossistema de proteção usado por jornalistas. O Tor reduz a exposição de **metadados de rede**; o PGP protege o **conteúdo**. Cada ferramenta cobre uma parte do problema.

---

## 7. Segurança operacional e modelagem de ameaças

### 7.1 O que é modelagem de ameaças

Antes de escolher ferramentas, é preciso responder a quatro perguntas:

1. **O que** estou protegendo?
2. **De quem** estou protegendo?
3. **Qual a probabilidade** e **o custo** de uma falha?
4. **Quanto esforço** estou disposto a fazer para evitá-la?

A resposta muda conforme o contexto: proteger fotos pessoais de um colega é diferente de proteger uma fonte jornalística de um Estado.

### 7.2 Princípios ilustrados pelo filme

O filme mostra, de forma visual, a ideia de que **segurança é um conjunto de hábitos**, e não só de ferramentas:

| Princípio | Ideia |
| --- | --- |
| **Defesa em profundidade** | Várias camadas de proteção, para que uma falha isolada não exponha tudo |
| **Minimização de dados** | Quanto menos dados existem, menos há para vazar |
| **Separação de canais** | Não misturar identidades e meios de comunicação |
| **Verificação de identidade** | Confirmar quem está do outro lado antes de confiar |
| **Cuidado com o ambiente físico** | Gestos como cobrir-se ao digitar uma senha lembram que a segurança digital tem uma dimensão física |

---

## 8. Ameaça interna e controle de acesso

### 8.1 O problema da ameaça interna

Boa parte da discussão técnica sobre o caso gira em torno de uma pergunta: **como alguém com acesso legítimo conseguiu levar tanta informação?** Segundo relatos, Snowden atuava como contratado com perfil técnico de administração de sistemas, o que lhe dava acesso amplo a repositórios.

Isso ilustra um problema clássico de segurança: **quem administra um sistema costuma ter poder sobre ele**. Seja por motivos éticos, financeiros ou acidentais, uma pessoa com privilégios amplos representa um **risco de ameaça interna** (*insider threat*).

### 8.2 Princípios de defesa

| Princípio | Como ajuda |
| --- | --- |
| **Menor privilégio** | Cada pessoa tem só o acesso de que precisa |
| **Separação de funções** | Nenhuma pessoa controla sozinha um processo crítico |
| **Registro e auditoria** | Toda consulta e cópia deixa rastro revisável |
| **Segmentação** | Dados sensíveis divididos em compartimentos (*need to know*) |
| **Regra das duas pessoas** | Ações de alto risco exigem duas autorizações |

Depois das revelações, agências de inteligência anunciaram o reforço de seus controles internos de segurança da informação.

### 8.3 Duas leituras da mesma questão

- **Ponto de vista da segurança:** controles internos fracos permitiram a saída de grandes volumes de dados sigilosos.
- **Ponto de vista da transparência:** sem mecanismos legítimos e eficazes de denúncia interna, quem identifica abusos pode ver no vazamento a única saída.

A discussão técnica sobre controle de acesso se mistura, portanto, com uma discussão institucional sobre **canais de denúncia protegidos**.

---

## 9. Efeitos técnicos e o debate sobre *backdoors*

### 9.1 Efeitos frequentemente apontados

Muitos analistas e profissionais de segurança apontam que as revelações aceleraram a adoção de proteções técnicas, por exemplo:

- maior uso de **HTTPS** em sites e serviços;
- difusão da **criptografia ponta a ponta** em aplicativos de mensagens;
- criptografia de dispositivos ativada por padrão em celulares;
- mais atenção à criptografia no tráfego **entre** data centers das empresas.

> **Atenção:** a atribuição de causa e efeito é discutida. Várias dessas tendências já existiam antes de 2013 e foram aceleradas por outros fatores.

### 9.2 O debate sobre *backdoors*

Uma pergunta técnica e política recorrente é se sistemas criptográficos deveriam ter **acesso excepcional** para autoridades (um *backdoor*).

| Argumento a favor | Argumento contra |
| --- | --- |
| Permite investigar crimes graves e terrorismo mesmo quando as comunicações estão cifradas | Uma brecha criada para "os bons" pode ser descoberta e explorada por atacantes |
| Autoridades atuariam com autorização judicial | Enfraquecer a criptografia reduz a segurança de **todos** os usuários |
| Evita "zonas sem lei" na comunicação digital | Existem alternativas de investigação que não dependem de enfraquecer a criptografia |

> **Para debate:** é possível desenhar um acesso excepcional que seja **seguro** e, ao mesmo tempo, **limitado** a casos legítimos?

---

## 10. Conexões com a Teoria da Computação

### 10.1 Criptografia e complexidade

A segurança da criptografia moderna se apoia em **problemas considerados difíceis**:

| Esquema | Problema em que se apoia |
| --- | --- |
| RSA | Fatoração de inteiros grandes |
| Diffie-Hellman e curvas elípticas | Logaritmo discreto (em grupos adequados) |

Para os algoritmos clássicos conhecidos, esses problemas não têm solução eficiente (polinomial). Mas **não existe prova** de que seja impossível encontrar uma.

### 10.2 Funções de mão única e P × NP

A criptografia de chave pública pressupõe a existência de **funções de mão única**: fáceis de calcular, mas difíceis de inverter. A existência dessas funções implicaria que **P ≠ NP**. Em outras palavras, a segurança de boa parte da internet depende de uma conjectura ainda **não provada** da Teoria da Computação.

### 10.3 Sigilo perfeito

Em 1949, Claude Shannon demonstrou que a **cifra de uso único** (*one-time pad*) oferece **sigilo perfeito**, desde que a chave seja realmente aleatória, do tamanho da mensagem e **nunca reutilizada**. Na prática, distribuir essas chaves é tão difícil quanto proteger a própria mensagem, e por isso se usa criptografia baseada em **segurança computacional**: o atacante teria que gastar recursos além do viável.

### 10.4 Computação quântica

Algoritmos quânticos, como o de **Shor**, poderiam fatorar inteiros grandes de forma eficiente em computadores quânticos suficientemente grandes. Por isso, a comunidade desenvolve **criptografia pós-quântica**, e o instituto americano NIST publicou, em 2024, os primeiros padrões desse tipo.

### 10.5 Grafos e algoritmos

A análise de saltos (seção 5) é uma aplicação direta de **grafos** e **BFS**. A discussão sobre quantos saltos permitir é, no fundo, uma discussão sobre **crescimento do número de vértices alcançados**.

---

## 11. Comparações históricas

### 11.1 Enigma × criptografia moderna

| Aspecto | Enigma (anos 1940) | Criptografia moderna |
| --- | --- | --- |
| Tecnologia | Eletromecânica | Matemática implementada em software e hardware |
| Tipo de chave | Simétrica (mesma configuração para cifrar e decifrar) | Simétrica, assimétrica e híbrida |
| Distribuição de chaves | Livros de códigos distribuídos fisicamente | Troca de chaves por protocolos criptográficos |
| Principal fraqueza explorada | Padrões previsíveis, erros operacionais e propriedades da máquina | Implementação, erros humanos e o dispositivo (endpoint) |
| Princípio de Kerckhoffs | Já valia (a segurança deveria depender só da chave) | Princípio central: algoritmos são públicos, só a chave é secreta |
| Espaço de chaves | Cerca de 10²⁰ configurações (modelo militar de 3 rotores) | AES-256: cerca de 10⁷⁷ chaves |

Em ambos os casos, **o elo humano** continua sendo o ponto mais frágil.

### 11.2 Interceptação em guerra × vigilância digital

| Aspecto | Anos 1940 (*Enigma*) | Era digital (*Citizenfour*) |
| --- | --- | --- |
| Alvo | Comunicações militares do inimigo | Comunicações em geral, incluindo as de cidadãos |
| Volume | Mensagens interceptadas por rádio, lidas por analistas e máquinas | Volumes massivos, processados automaticamente |
| Automação | Máquinas especializadas (Bombe) | Sistemas de computação distribuída, grafos, mineração |
| Questão central | Decifrar o conteúdo | Inferir padrões a partir de metadados e de conteúdo |
| Debate público | Sigilo durante a guerra | Equilíbrio entre segurança, privacidade e controle democrático |

---

## 12. Ética profissional e legislação

### 12.1 Código de Ética da ACM

O **Código de Ética e Conduta Profissional da ACM** (2018) inclui princípios diretamente relacionados ao tema, entre eles:

- evitar danos;
- respeitar a privacidade;
- honrar a confidencialidade.

Esses princípios entram em tensão em casos como o retratado no filme: o que fazer quando **confidencialidade** e **evitar danos** apontam para direções diferentes?

### 12.2 Contexto brasileiro

| Norma | Relevância |
| --- | --- |
| **Marco Civil da Internet** (Lei 12.965/2014) | Estabelece princípios, direitos e deveres para o uso da internet, incluindo a proteção da privacidade e dos dados pessoais. Foi aprovado em meio ao debate gerado pelas revelações |
| **LGPD** (Lei 13.709/2018) | Regula o tratamento de dados pessoais, com bases legais, direitos dos titulares e deveres para quem coleta dados |

Para profissionais de TI, a lição prática é que **projetar sistemas** é também **tomar decisões sobre privacidade**: quais dados coletar, por quanto tempo guardar e quem pode acessar.

---

## 13. Atividades

### 13.1 Prática de programação

1. **Metadados:** execute o código da [seção 4.3](#43-demonstração-com-dados-fictícios) e acrescente novos registros. Que novas inferências se tornam possíveis?
2. **Saltos:** modifique a função `saltos` para contar **quantos** vértices são alcançados a cada salto. Gere um grafo aleatório maior e observe o crescimento.
3. **RSA:** execute o RSA de brinquedo com outros valores de `p` e `q` (primos pequenos) e tente quebrá-lo por **força bruta**, fatorando `n`. Depois discuta por que isso é inviável com primos de 1.024 bits.

### 13.2 Discussão

4. Por que cifrar o conteúdo **não** elimina o risco de vigilância por metadados?
5. Que combinação de ferramentas cobre melhor o problema: conteúdo, metadados e dispositivo?
6. Como aplicar menor privilégio e separação de funções em um sistema de uma empresa que você conhece?
7. Um *backdoor* "só para os bons" é tecnicamente possível? Justifique.
8. Que dilemas éticos aparecem quando um profissional de TI descobre uma prática que considera abusiva dentro da organização?

### 13.3 Modelo de ameaças pessoal

9. Escolha três tipos de dados seus (localização, contatos, histórico de navegação). Para cada um, responda: **quem** poderia ter interesse, **que dano** poderia causar e **que medida** reduziria o risco.

### 13.4 Projeto

10. Em grupo, desenhe a arquitetura de um sistema fictício de coleta de dados que **respeite a privacidade por padrão** (*privacy by design*): defina quais dados coletar, por quanto tempo guardar, quem acessa e como auditar.

---

## 14. Glossário

| Termo | Significado |
| --- | --- |
| **Ameaça interna** | Risco que vem de quem tem acesso legítimo a um sistema |
| **Backdoor** | Acesso oculto ou excepcional que contorna a proteção de um sistema |
| **Chave pública / privada** | Par de chaves da criptografia assimétrica |
| **Controle de acesso** | Regras sobre quem pode fazer o quê em um sistema |
| **Criptografia ponta a ponta** | Cifragem em que só os extremos da comunicação têm as chaves |
| **Forward secrecy** | Propriedade que protege mensagens passadas mesmo se uma chave futura vazar |
| **Função de mão única** | Função fácil de calcular e difícil de inverter |
| **Hop (salto)** | Conexão entre dois nós em uma cadeia de contatos |
| **Metadados** | Dados sobre a comunicação (quem, quando, quanto), e não sobre o conteúdo |
| **Menor privilégio** | Princípio de dar a cada pessoa apenas o acesso necessário |
| **PGP/GPG** | Sistema de criptografia híbrida muito usado em e-mail |
| **Tor** | Rede de anonimato que dificulta ligar o usuário ao tráfego |

---

## 15. Considerações finais

Em *Citizenfour*, a tecnologia não é apenas cenário: ela determina **o que é possível** (coletar, analisar, proteger) e **o que está em jogo** (privacidade, segurança, transparência). Os conceitos que sustentam essa discussão são conteúdos centrais do curso: **grafos e algoritmos de busca**, **criptografia**, **complexidade**, **controle de acesso** e **ética profissional**.

A mensagem técnica final é dupla. Primeiro, **criptografia forte é necessária, mas não suficiente**: metadados, dispositivos e pessoas continuam sendo pontos de falha. Segundo, **escolhas de projeto são escolhas políticas**: quem projeta sistemas decide, na prática, quanto dado existe, quem o vê e por quanto tempo.

Lido junto com a [análise fílmica](analise_filmica.md) e com o material sobre *Enigma* ([análise tecnológica](../enigma/analise_tecnologica.md)), este arquivo mostra uma linha contínua: das cifras mecânicas e da criptoanálise artesanal de 1940 à criptografia matemática e à análise de dados em escala do século XXI.

---

## Referências

CITIZENFOUR. Direção: Laura Poitras. Estados Unidos, Alemanha, 2014. 1 documentário (114 min).

GREENWALD, Glenn. *Sem lugar para se esconder*. Rio de Janeiro: Sextante, 2014.

SNOWDEN, Edward. *Eterna vigilância* (*Permanent Record*). 2019.

SCHNEIER, Bruce. *Data and Goliath: The Hidden Battles to Collect Your Data and Control Your World*. 2015.

DIFFIE, Whitfield; HELLMAN, Martin. New Directions in Cryptography. *IEEE Transactions on Information Theory*, v. 22, n. 6, 1976.

RIVEST, Ronald; SHAMIR, Adi; ADLEMAN, Leonard. A Method for Obtaining Digital Signatures and Public-Key Cryptosystems. *Communications of the ACM*, v. 21, n. 2, 1978.

SHANNON, Claude. Communication Theory of Secrecy Systems. *Bell System Technical Journal*, 1949.

KERCKHOFFS, Auguste. *La cryptographie militaire*. 1883.

UNITED STATES. House Committee on the Judiciary. *USA FREEDOM Act of 2015: Report* (H. Rept. 114-109). Disponível em: <https://irp.fas.org/congress/2015_rpt/hjc-usaf.html>. Acesso em: 16 out. 2026.

PRIVACY AND CIVIL LIBERTIES OVERSIGHT BOARD (PCLOB). *Report on the Surveillance Program Operated Pursuant to Section 702 of the FISA*. 2014.

ASSOCIATION FOR COMPUTING MACHINERY (ACM). *ACM Code of Ethics and Professional Conduct*. 2018.

BRASIL. Lei nº 12.965, de 23 de abril de 2014 (Marco Civil da Internet).

BRASIL. Lei nº 13.709, de 14 de agosto de 2018 (Lei Geral de Proteção de Dados Pessoais).
