## 🟢 **Temas Simples (DFA básico)**

1. **Assistente de pedidos de pizza** 🍕

   * O chatbot reconhece intenções simples: escolher sabor, tamanho, bebida e confirmar o pedido.
   * Cada escolha muda o estado (ex: `inicio → escolher_sabor → escolher_tamanho → confirmar`).

2. **FAQ de biblioteca** 📚

   * O autômato responde perguntas como “horário de funcionamento”, “renovar livro”, “multas”.
   * Cada categoria é um estado; o chatbot retorna ao estado inicial após responder.

3. **Atendimento de transporte escolar** 🚌

   * Estados: saudação → informar rota → horário → finalizar.
   * Reage a palavras-chave como “horário”, “motorista”, “rota”.

4. **Assistente de cinema** 🎬

   * Estados: saudação → escolher filme → horário → ingresso → pagamento.
   * Usa expressões simples para reconhecimento de comandos.

5. **Chatbot de suporte técnico** 💻

   * Identifica se o problema é de “internet”, “impressora” ou “login” e oferece soluções curtas.

---

## 🟡 **Temas Intermediários (DFA + memória limitada ou contexto)**

1. **Chatbot de banco** 🏦

   * Simula operações básicas: consultar saldo, ver extrato, transferir.
   * Usa transições condicionais (ex: só permite transferência após autenticação).

2. **Assistente de matrícula escolar** 🏫

   * Guia o usuário em etapas: dados pessoais → curso → turno → confirmação.
   * DFA com validações simples de entrada.

3. **Chatbot para cantina escolar** 🍔

   * Estados: menu → escolher item → quantidade → total → pagamento.
   * Pode incluir um contador de itens usando autômato com contador limitado.

4. **Assistente de triagem médica** 🏥

   * Pergunta sintomas e sugere “resfriado”, “gripe”, “urgência”.
   * Transições baseadas em respostas “sim/não”.

5. **Chatbot de turismo local** 🌍

   * Estados para atrações, hospedagem, alimentação e transporte.
   * DFA com subautômatos por categoria.

---

## 🔵 **Temas Avançados (DFA + NLP simbólico / Regex)**

1. **Chatbot de help desk com detecção de intenções**

   * DFA alimentado por expressões regulares que reconhecem padrões como “esqueci minha senha” → vai ao estado de recuperação.

2. **Chatbot de e-commerce** 🛒

   * Gerencia carrinho, consulta estoque e finaliza compra.
   * Usa autômato com *tokens* e pequenos scripts para reconhecer comandos.

3. **Chatbot de atendimento de prefeitura** 🏙️

   * Oferece serviços: IPTU, agendamento, reclamações.
   * Integração simbólica de NFA → DFA para lidar com sinônimos.

4. **Chatbot de ensino de línguas** 🇧🇷🇺🇸

   * DFA que corrige frases simples e fornece feedback (“Erro de verbo”, “Falta artigo”).
   * Transições baseadas em padrões linguísticos.

5. **Chatbot para sistema de biblioteca inteligente** 📖

   * Permite buscar livros, reservar, devolver e ver multas.
   * Estados com hierarquia (ex: busca → resultado → reserva).

---

## 💡 Sugestão de estrutura para o trabalho:

1. **Introdução** — o que são autômatos finitos e sua aplicação em chatbots.
2. **Objetivo** — simular um diálogo estruturado usando DFA.
3. **Metodologia** — modelagem de estados e transições.
4. **Implementação** — diagrama de estados + código (Python, Java ou JavaScript).
5. **Testes** — exemplos de conversas.
6. **Conclusão** — vantagens e limitações do modelo.

---
