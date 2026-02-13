### O Conceito Fundamental

Antes de Von Neumann, os computadores eram "programados" fisicamente, alterando conexões de cabos e chaves. A inovação de Von Neumann foi o **Conceito de Programa Armazenado**: a ideia de que as instruções (o software) e os dados devem residir no mesmo espaço de memória.

---

### Os Componentes do Modelo

De acordo com esta arquitetura, um sistema computacional é dividido em quatro componentes principais:

1. **Unidade Central de Processamento (CPU):** Onde ocorre a execução das instruções. Ela se divide em:

 
**Unidade Lógica e Aritmética (ULA):** Realiza os cálculos (aritmética computacional) e as decisões lógicas.


**Unidade de Controle (UC):** Gerencia o fluxo de dados, interpretando as instruções da memória.


**Registradores:** Pequenas áreas de memória ultra-rápida dentro da CPU para armazenamento temporário.




2. **Memória Principal (RAM):** Um espaço de armazenamento linear onde as instruções do programa e os dados necessários para o processamento são guardados.


3. **Sistemas de Entrada e Saída (I/O):** Interfaces que permitem a comunicação do computador com o mundo externo, como teclados, monitores e redes.


4. **Barramentos (Bus):** Os caminhos físicos (fios/circuitos) que transportam os dados, endereços e sinais de controle entre todos os componentes.



---

### O "Gargalo" de Von Neumann e a Cibersegurança

Como você abordará a **Confidencialidade** e a **Integridade** em suas aulas de janeiro de 2026, é importante destacar dois pontos críticos deste modelo:

* 
**O Gargalo:** Como a CPU e a memória estão separadas, a velocidade do computador é limitada pela velocidade com que os dados viajam entre elas. Isso explica a necessidade de **Memórias Cache**, que você pode citar ao falar sobre desempenho de servidores **Back-End**.


* 
**Vulnerabilidade Logística:** Como o modelo armazena dados e instruções no mesmo local, um invasor pode tentar injetar um "código malicioso" em uma área destinada apenas a "dados". Muitos ataques de segurança exploram exatamente essa característica da arquitetura original para ganhar controle do sistema.
