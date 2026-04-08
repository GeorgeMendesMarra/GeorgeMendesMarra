// Importa a classe principal para criar circuitos quânticos
import io.quantum4j.core.circuit.QuantumCircuit;
// Importa todas as classes do backend (simuladores, opções de execução, etc.)
import io.quantum4j.core.backend.*;

// Classe que demonstra a criação de um Estado de Bell (emaranhamento quântico)
public class BellState {
    
    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        
        // ==========================================
        // PASSO 1: CRIAÇÃO DO CIRCUITO QUÂNTICO
        // ==========================================
        // QuantumCircuit.create(2) - Cria um circuito com 2 qubits
        // Os qubits são indexados como 0 e 1
        // Estado inicial: |00⟩ (ambos os qubits no estado clássico 0)
        QuantumCircuit qc = QuantumCircuit.create(2)
        
                // ==========================================
                // PASSO 2: PORTA HADAMARD (H) no qubit 0
                // ==========================================
                // .h(0) - Aplica a porta Hadamard no primeiro qubit
                // Efeito: Cria superposição
                // |0⟩ → (|0⟩ + |1⟩)/√2
                // |1⟩ → (|0⟩ - |1⟩)/√2
                // Resultado após esta operação: (|00⟩ + |10⟩)/√2
                // O qubit 0 agora tem 50% chance de ser |0⟩ e 50% de ser |1⟩
                .h(0)
                
                // ==========================================
                // PASSO 3: PORTA CNOT (Controlled-NOT)
                // ==========================================
                // .cx(0, 1) - Aplica CNOT com controle no qubit 0 e alvo no qubit 1
                // Funcionamento:
                // - Se o qubit de controle (0) for |0⟩ → não faz nada no alvo (1)
                // - Se o qubit de controle (0) for |1⟩ → inverte o qubit alvo (1)
                // Resultado final: (|00⟩ + |11⟩)/√2
                // Os qubits agora estão EMARANHADOS (entangled)
                // Medir um determina instantaneamente o estado do outro
                .cx(0, 1)
                
                // ==========================================
                // PASSO 4: MEDIÇÃO DE TODOS OS QUBITS
                // ==========================================
                // .measureAll() - Mede o estado dos qubits
                // Colapsa a superposição para um estado clássico definitivo
                // No estado de Bell, os resultados possíveis são:
                // - 00 com 50% de probabilidade
                // - 11 com 50% de probabilidade
                // OBS: NUNCA medirá 01 ou 10 devido ao emaranhamento!
                .measureAll();
        
        // ==========================================
        // PASSO 5: EXECUÇÃO DA SIMULAÇÃO
        // ==========================================
        // new StateVectorBackend() - Cria um simulador baseado em vetor de estado
        //   Representa o estado quântico como um vetor de amplitudes complexas
        //   Permite simular sistemas de até ~25-30 qubits em hardware comum
        //
        // .run(qc, ...) - Executa o circuito quântico
        //
        // RunOptions.shots(1000) - Define o número de execuções/medições
        //   Cada "shot" executa o circuito do zero e mede ao final
        //   1000 shots = 1000 medições independentes
        //   Mais shots = maior precisão estatística (mas mais tempo de processamento)
        Result r = new StateVectorBackend().run(qc, RunOptions.shots(1000));
        
        // ==========================================
        // PASSO 6: EXIBIÇÃO DOS RESULTADOS
        // ==========================================
        // r.getCounts() - Retorna um mapa com a contagem de cada resultado
        // Exemplo de saída: {00=502, 11=498}
        //   - Chave "00": Estado onde ambos os qubits são 0
        //   - Chave "11": Estado onde ambos os qubits são 1
        //   - Valor: Número de vezes que este resultado foi medido
        // 
        // A soma total deve ser igual ao número de shots (1000)
        // Pequenas variações (502/498 em vez de 500/500) são normais
        // devido à natureza probabilística da mecânica quântica
        System.out.println(r.getCounts());
        
        // ==========================================
        // RESULTADO ESPERADO (exemplo):
        // ==========================================
        // {00=495, 11=505}
        // 
        // INTERPRETAÇÃO:
        // - Em 495 execuções, mediu-se 00
        // - Em 505 execuções, mediu-se 11
        // - Isso confirma o emaranhamento: os qubits estão sempre juntos
        // - A correlação é perfeita (nunca aparecem 01 ou 10)
        // - Quanto mais shots, mais próximo de 500/500 (50%/50%)
    }
}
