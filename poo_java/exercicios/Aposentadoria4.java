import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.ParseException;

public class Aposentadoria4 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Aposentadoria");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==========================================
        // PAINEL DO FORMULÁRIO COM VALIDAÇÕES
        // ==========================================
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Campo Nome
        JTextField campoNome = new JTextField();
        JLabel labelNome = new JLabel("Nome completo:*");
        labelNome.setForeground(Color.RED);
        formPanel.add(labelNome);
        formPanel.add(campoNome);

        // Campo CPF com Máscara
        JFormattedTextField campoCPF = criarCampoCPF();
        JLabel labelCPF = new JLabel("CPF:*");
        labelCPF.setForeground(Color.RED);
        formPanel.add(labelCPF);
        formPanel.add(campoCPF);

        // Campo Idade
        JTextField campoIdade = new JTextField();
        JLabel labelIdade = new JLabel("Idade:*");
        labelIdade.setForeground(Color.RED);
        formPanel.add(labelIdade);
        formPanel.add(campoIdade);

        // Campo Sexo (usando JComboBox para evitar erro)
        JComboBox<String> comboSexo = new JComboBox<>(new String[]{"", "M", "F"});
        JLabel labelSexo = new JLabel("Sexo:*");
        labelSexo.setForeground(Color.RED);
        formPanel.add(labelSexo);
        formPanel.add(comboSexo);

        // Campo Contribuição
        JTextField campoContribuicao = new JTextField();
        JLabel labelContribuicao = new JLabel("Anos de contribuição:*");
        labelContribuicao.setForeground(Color.RED);
        formPanel.add(labelContribuicao);
        formPanel.add(campoContribuicao);

        // ==========================================
        // PAINEL DOS BOTÕES
        // ==========================================
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        // ==========================================
        // ÁREA DE RESULTADO
        // ==========================================
        JTextArea resultadoArea = new JTextArea(5, 40);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultado da Análise"));

        // ==========================================
        // ORGANIZANDO O PAINEL PRINCIPAL
        // ==========================================
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // ==========================================
        // BOTÃO CALCULAR COM VALIDAÇÕES
        // ==========================================
        btnCalcular.addActionListener((ActionEvent e) -> {
            
            // Limpa área de resultado
            resultadoArea.setText("");
            
            // ===== 1. VALIDAÇÃO DO NOME =====
            String nome = campoNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Preencha o nome completo!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoNome.requestFocus();
                return;
            }
            
            if (nome.length() < 3) {
                JOptionPane.showMessageDialog(frame,
                        "Nome deve ter pelo menos 3 caracteres!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoNome.requestFocus();
                return;
            }
            
            if (!nome.matches("[a-zA-ZÀ-ú\\s]+")) {
                JOptionPane.showMessageDialog(frame,
                        "Nome deve conter apenas letras e espaços!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoNome.requestFocus();
                return;
            }
            
            // ===== 2. VALIDAÇÃO DO CPF =====
            String cpf = campoCPF.getText().trim();
            if (cpf.isEmpty() || cpf.equals("   .   .   -  ")) {
                JOptionPane.showMessageDialog(frame,
                        "Preencha o CPF corretamente!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoCPF.requestFocus();
                return;
            }
            
            // Remove máscara para validação
            String cpfNumeros = cpf.replaceAll("\\D", "");
            if (cpfNumeros.length() != 11) {
                JOptionPane.showMessageDialog(frame,
                        "CPF deve conter 11 dígitos!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoCPF.requestFocus();
                return;
            }
            
            if (!validarCPF(cpfNumeros)) {
                JOptionPane.showMessageDialog(frame,
                        "CPF inválido! Digite um CPF válido.",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoCPF.requestFocus();
                return;
            }
            
            // ===== 3. VALIDAÇÃO DA IDADE =====
            String idadeTexto = campoIdade.getText().trim();
            if (idadeTexto.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Preencha a idade!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoIdade.requestFocus();
                return;
            }
            
            int idade;
            try {
                idade = Integer.parseInt(idadeTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Idade deve ser um número inteiro!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoIdade.requestFocus();
                return;
            }
            
            if (idade < 0 || idade > 150) {
                JOptionPane.showMessageDialog(frame,
                        "Idade inválida! Digite uma idade entre 0 e 150 anos.",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoIdade.requestFocus();
                return;
            }
            
            // ===== 4. VALIDAÇÃO DO SEXO =====
            String sexoStr = (String) comboSexo.getSelectedItem();
            if (sexoStr == null || sexoStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Selecione o sexo (M ou F)!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                comboSexo.requestFocus();
                return;
            }
            
            char sexo = sexoStr.charAt(0);
            
            // ===== 5. VALIDAÇÃO DA CONTRIBUIÇÃO =====
            String contribuicaoTexto = campoContribuicao.getText().trim();
            if (contribuicaoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Preencha os anos de contribuição!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoContribuicao.requestFocus();
                return;
            }
            
            int contribuicao;
            try {
                contribuicao = Integer.parseInt(contribuicaoTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Contribuição deve ser um número inteiro!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoContribuicao.requestFocus();
                return;
            }
            
            if (contribuicao < 0 || contribuicao > 70) {
                JOptionPane.showMessageDialog(frame,
                        "Contribuição inválida! Digite um valor entre 0 e 70 anos.",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
                campoContribuicao.requestFocus();
                return;
            }
            
            // ===== 6. CÁLCULO DA APOSENTADORIA =====
            int faltante = 0;
            String resultado;
            StringBuilder mensagem = new StringBuilder();
            
            mensagem.append("=== DADOS DO SOLICITANTE ===\n");
            mensagem.append("Nome: ").append(nome).append("\n");
            mensagem.append("CPF: ").append(cpf).append("\n");
            mensagem.append("Idade: ").append(idade).append(" anos\n");
            mensagem.append("Sexo: ").append(sexo).append("\n");
            mensagem.append("Contribuição: ").append(contribuicao).append(" anos\n");
            mensagem.append("============================\n\n");
            
            if (sexo == 'F') {
                if (idade >= 60 || contribuicao >= 30) {
                    resultado = "Aposentado";
                    mensagem.append("✅ Você já tem direito à aposentadoria!\n");
                    
                    if (idade >= 60) {
                        mensagem.append("✓ Idade mínima atingida (60 anos)\n");
                    }
                    if (contribuicao >= 30) {
                        mensagem.append("✓ Tempo de contribuição mínimo atingido (30 anos)\n");
                    }
                } else {
                    resultado = "Não aposentado";
                    int faltanteIdade = 60 - idade;
                    faltante = 30 - contribuicao;
                    
                    mensagem.append("❌ Você NÃO tem direito à aposentadoria.\n\n");
                    mensagem.append("--- MOTIVOS ---\n");
                    
                    if (idade < 60) {
                        mensagem.append("• Faltam ").append(faltanteIdade).append(" anos para atingir a idade mínima (60 anos)\n");
                    }
                    if (contribuicao < 30) {
                        mensagem.append("• Faltam ").append(faltante).append(" anos de contribuição (mínimo 30 anos)\n");
                    }
                }
                
            } else if (sexo == 'M') {
                if (idade >= 65 || contribuicao >= 35) {
                    resultado = "Aposentado";
                    mensagem.append("✅ Você já tem direito à aposentadoria!\n");
                    
                    if (idade >= 65) {
                        mensagem.append("✓ Idade mínima atingida (65 anos)\n");
                    }
                    if (contribuicao >= 35) {
                        mensagem.append("✓ Tempo de contribuição mínimo atingido (35 anos)\n");
                    }
                } else {
                    resultado = "Não aposentado";
                    int faltanteIdade = 65 - idade;
                    faltante = 35 - contribuicao;
                    
                    mensagem.append("❌ Você NÃO tem direito à aposentadoria.\n\n");
                    mensagem.append("--- MOTIVOS ---\n");
                    
                    if (idade < 65) {
                        mensagem.append("• Faltam ").append(faltanteIdade).append(" anos para atingir a idade mínima (65 anos)\n");
                    }
                    if (contribuicao < 35) {
                        mensagem.append("• Faltam ").append(faltante).append(" anos de contribuição (mínimo 35 anos)\n");
                    }
                }
            } else {
                // Nunca deve chegar aqui por causa da validação
                return;
            }
            
            resultadoArea.setText(mensagem.toString());
            
            // ===== 7. SALVAR EM CSV =====
            salvarCSV(nome, cpf, idade, sexo, contribuicao, faltante, resultado);
            
            // ===== 8. EXIBIR RESUMO =====
            JOptionPane.showMessageDialog(frame,
                    "Dados salvos com sucesso!\n" +
                    "Nome: " + nome + "\n" +
                    "CPF: " + cpf + "\n" +
                    "Resultado: " + resultado,
                    "Processo Concluído",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // ==========================================
        // BOTÃO LIMPAR
        // ==========================================
        btnLimpar.addActionListener(e -> {
            campoNome.setText("");
            campoCPF.setText("");
            campoIdade.setText("");
            comboSexo.setSelectedIndex(0);
            campoContribuicao.setText("");
            resultadoArea.setText("");
            campoNome.requestFocus();
        });

        // ==========================================
        // BOTÃO SAIR
        // ==========================================
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Deseja realmente sair?",
                    "Confirmar saída",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    // ==========================================
    // MÉTODO PARA CRIAR CAMPO CPF COM MÁSCARA
    // ==========================================
    private static JFormattedTextField criarCampoCPF() {
        JFormattedTextField campoCPF = null;
        try {
            MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
            mascaraCPF.setPlaceholderCharacter(' ');
            mascaraCPF.setValidCharacters("0123456789");
            campoCPF = new JFormattedTextField(mascaraCPF);
            campoCPF.setColumns(15);
        } catch (ParseException e) {
            // Fallback se a máscara falhar
            campoCPF = new JFormattedTextField();
            campoCPF.setColumns(15);
        }
        return campoCPF;
    }

    // ==========================================
    // MÉTODO PARA VALIDAR CPF (ALGORITMO OFICIAL)
    // ==========================================
    private static boolean validarCPF(String cpf) {
        
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");
        
        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais (CPF inválido conhecido)
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }
        
        // Cálculo do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }
        
        // Verifica primeiro dígito
        if (primeiroDigito != (cpf.charAt(9) - '0')) {
            return false;
        }
        
        // Cálculo do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }
        
        // Verifica segundo dígito
        if (segundoDigito != (cpf.charAt(10) - '0')) {
            return false;
        }
        
        return true;
    }

    // ==========================================
    // MÉTODO PARA SALVAR EM CSV
    // ==========================================
    private static void salvarCSV(String nome, String cpf, int idade, char sexo, 
                                   int contribuicao, int faltante, String resultado) {
        
        try {
            File file = new File("aposentadoria.csv");
            boolean novoArquivo = !file.exists();
            
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);
            
            // Cabeçalho do CSV
            if (novoArquivo) {
                bw.write("Nome,CPF,Idade,Sexo,Contribuicao,Faltante,Resultado,DataHora");
                bw.newLine();
            }
            
            // Adiciona data e hora do registro
            java.time.LocalDateTime agora = java.time.LocalDateTime.now();
            String dataHora = agora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            
            // Escreve os dados (CPF sem máscara para o CSV)
            String cpfNumeros = cpf.replaceAll("\\D", "");
            bw.write(String.format("%s,%s,%d,%c,%d,%d,%s,%s",
                    nome, cpfNumeros, idade, sexo, contribuicao, faltante, resultado, dataHora));
            bw.newLine();
            
            bw.close();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar no arquivo: " + e.getMessage(),
                    "Erro de Arquivo",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
