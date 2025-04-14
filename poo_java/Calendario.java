// Importa classes necessárias para trabalhar com datas formatadas
import java.text.DateFormat;        // Classe usada para formatar datas
import java.util.Calendar;         // Classe que representa uma data completa (dia, mês, ano, hora, etc.)
import java.util.Locale;           // Classe que define o formato regional (idioma, país)

public class Calendario {
    public static void main(String[] args) {

        // Cria uma instância do calendário com a data atual do sistema
        Calendar calendario = Calendar.getInstance();

        // Cria um formatador de datas com estilo curto (ex: 14/04/25) no formato do Reino Unido (UK = dd/MM/yy)
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

        // Imprime a data atual formatada no estilo britânico
        System.out.println(df.format(calendario.getTime()));

        // Imprime os campos estáticos das constantes do Calendar:
        // IMPORTANTE: essas constantes são apenas inteiros fixos (valores simbólicos) — não imprimem a data real.
        // Ex: Calendar.DAY_OF_MONTH vale 5 (constante que representa "dia do mês")
        System.out.println(Calendar.DAY_OF_MONTH);  // Saída: 5
        System.out.println(Calendar.DAY_OF_WEEK);   // Saída: 7
        System.out.println(Calendar.DAY_OF_YEAR);   // Saída: 6
        // Essas linhas não são úteis para imprimir valores da data, e sim para serem usadas como "chave" ao obter dados do calendário.

        // Agora, modificamos o calendário:
        calendario.add(Calendar.DAY_OF_YEAR, 10); // Adiciona 10 dias ao dia do ano atual
        calendario.add(Calendar.MONTH, 5);        // Adiciona 5 meses à data atual
        calendario.add(Calendar.YEAR, 2);         // Adiciona 2 anos à data atual

        // Imprime a nova data, já com as alterações acima aplicadas
        System.out.println(df.format(calendario.getTime()));
    }
}

