/* Bibliotecas básicas do Java
          Nem todos os import precisam estar em um programa ao mesmo tempo — você usa conforme necessário.
          A biblioteca java.lang é importada automaticamente, mas está listada aqui por fins didáticos.
*/
import java.lang.*; // Importação implícita em todo programa Java — contém classes fundamentais como String, Math, Object, etc.

import java.util.*; // Contém estruturas de dados como List, ArrayList, HashMap, Scanner, Date, Calendar, etc.

import java.io.*; // Para entrada/saída de arquivos e dados (File, FileReader, BufferedReader, etc.)

import java.text.*; // Para formatação de textos, datas, números e moedas (DateFormat, DecimalFormat, etc.)

import java.time.*; // API moderna de datas (desde Java 8) — LocalDate, LocalTime, LocalDateTime, etc.

import java.time.format.*; // Para formatar objetos da API java.time, como DateTimeFormatter

import java.math.*; // Para operações matemáticas avançadas — BigInteger, BigDecimal

import java.net.*; // Para comunicação em rede — URL, Socket, ServerSocket, etc.

import javax.swing.*; // Para construção de interfaces gráficas (GUI) — JFrame, JButton, JLabel, etc.

import java.sql.*; // Para acesso a banco de dados — Connection, Statement, ResultSet, etc.

public class ImportExemplo {
    public static void main(String[] args) {
        System.out.println("Programa exemplo com os principais imports do Java.");
    }
}

