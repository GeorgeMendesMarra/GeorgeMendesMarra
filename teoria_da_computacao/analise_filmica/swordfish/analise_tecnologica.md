# Análise Tecnológica: *Swordfish* (*A Senha*, 2001)

> **Disciplina:** Teoria da Computação
> **Objetivo:** separar o que o filme mostra do que a computação realmente permite, usando conceitos de criptografia, complexidade e segurança de redes.

---

## 1. Visão geral

*Swordfish* gira em torno de uma ideia tecnológica central: **um programa escrito por um hacker, capaz de desviar bilhões de dólares de um fundo secreto do governo americano**. Para isso, o filme mobiliza quatro grandes temas técnicos:

1. **Worms** (programas que se replicam sozinhos pela rede);
2. **Criptografia** (proteção dos dados do fundo);
3. **Invasão de sistemas** (a cena dos 60 segundos);
4. **Visualização de dados** (as interfaces 3D coloridas).

A análise a seguir examina cada um, comparando a **tecnologia do filme** com a **tecnologia real** e com os **conceitos da Teoria da Computação** envolvidos.

---

## 2. Quadro comparativo: filme × realidade

| Elemento | No filme | Na realidade | Conceito de TC |
|---|---|---|---|
| **Worm (Hydra)** | Escrito sob encomenda, em pouco tempo, e faz tudo sozinho | Worms existem e são perigosos, mas exigem vulnerabilidades específicas e meses de engenharia | Autorreplicação, programas que geram programas |
| **Quebra da criptografia** | Resolvida em 60 segundos por habilidade humana | Chaves modernas não são quebráveis por força bruta | Complexidade exponencial, espaço de chaves |
| **Interface 3D de cubos** | O hacker "mexe" em blocos de dados | Ataques reais são textuais, automatizados e silenciosos | Abstração, representação de dados |
| **Invasão do sistema do governo** | Acontece a partir de um computador comum, rapidamente | Alvos sensíveis têm camadas de defesa, isolamento e monitoramento | Modelos de ameaça, superfície de ataque |
| **Transferência de dinheiro** | Bilhões movidos por milhares de contas, de forma instantânea | Transferências reais deixam rastros e passam por controles de auditoria | Sistemas distribuídos, consistência e rastreabilidade |

---

## 3. O worm: autorreplicação como conceito

### 3.1 O que o filme mostra
O programa do filme (o **Hydra**, nome que remete ao monstro de várias cabeças) é apresentado como uma criação genial, quase artística, que percorre as redes sozinha até concluir o roubo.

### 3.2 O que existe de real
- **Worms** são programas que se propagam de máquina em máquina sem precisar de um arquivo hospedeiro.
- Casos históricos mostram o poder e os limites desse tipo de código: o **Worm de Morris (1988)** paralisou boa parte da internet da época por um erro de projeto (replicava demais); o **ILOVEYOU (2000)** se espalhou por e-mail; o **Code Red (julho de 2001)**, contemporâneo do filme, explorava uma falha em servidores web.
- Em todos os casos, o worm depende de uma **vulnerabilidade concreta**. Não existe "worm universal".

### 3.3 Ligação com a Teoria da Computação
A autorreplicação tem base teórica sólida: o **Teorema da Recursão de Kleene** e os trabalhos de **von Neumann** sobre autômatos autorreprodutores mostram que é possível um programa produzir uma cópia de si mesmo. Isso conecta *Swordfish* com a linhagem de **Turing**, trabalhada nos outros filmes da pasta.

Há também um limite teórico importante: **detectar com certeza se um programa qualquer é malicioso é um problema indecidível** (consequência do Teorema de Rice). Por isso antivírus são sempre aproximações.

---

## 4. Criptografia: o coração da inverossimilhança

### 4.1 O espaço de chaves
Uma chave de **128 bits** admite 2¹²⁸ ≈ 3,4 × 10³⁸ combinações. Mesmo supondo um computador capaz de testar **10¹² chaves por segundo** (um trilhão):

> 3,4 × 10³⁸ ÷ 10¹² ≈ 3,4 × 10²⁶ segundos ≈ **10¹⁹ anos**

Isso é cerca de um bilhão de vezes a idade do universo. Quebrar por força bruta em 60 segundos é, portanto, impossível, em 2001 e hoje.

### 4.2 O contexto histórico real de 2001
- **DES (56 bits):** em 1998, a EFF construiu uma máquina dedicada (*Deep Crack*) que quebrou uma chave DES em poucos dias. Chaves de 56 bits já eram consideradas frágeis.
- **128 bits:** já era o padrão de mercado para conexões seguras na web (SSL/TLS).
- **AES:** foi padronizado oficialmente em **novembro de 2001**, poucos meses depois da estreia do filme, e justamente com chaves de 128, 192 e 256 bits.

Ou seja, o filme escolheu um nível de proteção **exatamente no ponto em que a criptografia deixou de ser quebrável na prática**.

### 4.3 Como ataques reais funcionam
Quase nunca atacam o algoritmo. Atacam o que está em volta:
- **senhas fracas ou reaproveitadas**;
- **engenharia social** (convencer uma pessoa a entregar acesso);
- **falhas de implementação** (erros no código que usa a criptografia);
- **configurações incorretas** e sistemas sem atualização.

Esse é o ponto pedagógico mais importante: **a matemática da criptografia é sólida; o elo fraco costuma ser o humano e o software ao redor**.

### 4.4 Complexidade
A segurança criptográfica repousa na hipótese de que certos problemas são **difíceis** (crescimento exponencial com o tamanho da entrada). Isso se liga à questão **P versus NP**: não há prova de que esses problemas sejam realmente difíceis, apenas ausência de algoritmos eficientes conhecidos. O filme ignora completamente essa noção e trata a dificuldade como obstáculo de destreza.

---

## 5. A cena dos 60 segundos: análise passo a passo

| Etapa dramática | Problema técnico |
|---|---|
| Prazo de 60 segundos | Ataques reais levam horas, dias ou meses de preparação |
| Hacker digita e manipula blocos na tela | Ataques reais são scripts e ferramentas, não manipulação manual em tempo real |
| Invasão de um sistema de alta segurança em instantes | Alvos críticos costumam estar isolados e monitorados |
| Distrações e pressão física | Irrelevantes para o desempenho de um algoritmo; só afetam o ser humano |

**Observação didática:** a cena é um bom exemplo de **representação de um problema NP-difícil como habilidade intuitiva**. Na vida real, o que torna um ataque eficiente é o **algoritmo e o poder computacional**, não a rapidez de digitação.

---

## 6. Visualização de dados: o "tetris de cubos"

A decisão estética de criar interfaces 3D tem uma justificativa de linguagem: **o cinema precisa tornar visível o invisível**. Mas há implicações técnicas interessantes:

- **Abstração:** dados, redes e algoritmos são entidades abstratas. Visualizá-los é um exercício legítimo (existem ferramentas reais de visualização de redes e de logs), mas com outro propósito: **análise**, não "invasão".
- **Interface versus lógica:** a interface é só a camada de apresentação. A lógica de um ataque ou de uma defesa ocorre em outro nível.
- **Risco de desinformação:** a imagem do hacker como "manipulador de blocos" reforça uma ideia errada de como a segurança funciona.

---

## 7. Segurança de redes: o que o filme ignora

Para proteger um fundo como o do filme, uma organização real usaria:

- **Defesa em profundidade:** várias camadas, de modo que uma falha isolada não comprometa tudo;
- **Segmentação e isolamento de rede** (*air gap* em sistemas críticos);
- **Autenticação forte e múltiplos fatores;**
- **Registro e auditoria (logs)** para detectar movimentações suspeitas;
- **Princípio do menor privilégio:** cada usuário e sistema só acessa o necessário.

Do ponto de vista de **modelagem de ameaças**, o filme tenta fazer o espectador crer que o atacante precisa vencer **uma única barreira**. A realidade exige vencer várias, sem ser detectado.

---

## 8. Dinheiro digital e rastreabilidade

O filme sugere que movimentar bilhões é uma questão de redirecionar números em bancos de dados. Na prática:

- Transferências interbancárias passam por **sistemas distribuídos** com registro e verificação;
- Grandes movimentações geram **alertas automáticos** de conformidade;
- O dinheiro "some" apenas até que alguém o rastreie: **toda transação deixa um histórico**.

Aqui entram conceitos de **consistência e atomicidade** de transações, vistos em bancos de dados e sistemas distribuídos.

---

## 9. Ética e responsabilidade técnica

*Swordfish* levanta questões que vão além do código:

1. **Habilidade técnica é neutra?** Stanley escreve o worm por uma motivação pessoal legítima (a filha), mas o resultado é um crime.
2. **Responsabilidade do desenvolvedor:** quem escreve uma ferramenta responde pelo uso que fazem dela?
3. **Segurança nacional versus legalidade:** Gabriel defende que o fim justifica os meios, o que abre debate sobre **vigilância e abuso de poder** (ver também *Citizenfour*).
4. **Hacking ético:** a mesma habilidade, usada com autorização, é uma profissão legítima (testes de intrusão, *red team*, pesquisa de vulnerabilidades).

---

## 10. Conexão com os demais filmes da pasta

| Tema | *Enigma* | *O Jogo da Imitação* | *Citizenfour* | *Swordfish* |
|---|---|---|---|---|
| Criptografia | Quebra paciente e coletiva | Máquina eletromecânica de Turing | Criptografia como proteção de privacidade | Quebra instantânea e individual |
| Papel do computador | Instrumento científico | Origem da computação moderna | Instrumento de vigilância | Arma de ação |
| Realismo técnico | Alto | Alto | Alto | Baixo |

A evolução do cinema reflete a mudança cultural: da criptoanálise como **esforço coletivo e matemático** para o hacker como **gênio solitário**.

---

## 11. Atividades sugeridas

1. **Cálculo:** estimar o tempo de força bruta para chaves de 40, 56, 128 e 256 bits, variando a velocidade do hardware.
2. **Pesquisa:** comparar o Worm de Morris, o ILOVEYOU e o Code Red (ano, mecanismo de propagação, impacto).
3. **Debate:** reescrever a cena dos 60 segundos de forma tecnicamente plausível. Ela continuaria dramática?
4. **Programação (Java ou C):** implementar uma busca por força bruta em uma cifra simples (como César) e medir o tempo, observando o crescimento ao aumentar o tamanho da chave.
5. **Reflexão:** quais são as responsabilidades éticas de quem desenvolve software de segurança?

---

## 12. Conclusão

*Swordfish* é tecnicamente fraco como retrato da computação, mas **muito bom como objeto de estudo**: cada exagero do filme aponta para um conceito real que vale a pena entender. O contraste entre o hacker "instantâneo" do cinema e a realidade da criptografia, da complexidade e da segurança em camadas é uma lição direta de Teoria da Computação: **o que é computável em princípio nem sempre é computável na prática**.

---

## Referências sugeridas

- SENA, Dominic (dir.). *Swordfish* (*A Senha*). Warner Bros., 2001.
- NIST. *FIPS 197: Advanced Encryption Standard (AES)*, 2001.
- ELECTRONIC FRONTIER FOUNDATION. *Cracking DES*, 1998.
- SIPSER, Michael. *Introdução à Teoria da Computação*.
- STALLINGS, William. *Criptografia e Segurança de Redes*.
- Demais análises da pasta: *Enigma*, *O Jogo da Imitação*, *Citizenfour*.
