/**
 * ResistorColorCode
 * Decodifica um resistor de 4 faixas (ex.: "red", "violet", "yellow", "gold")
 * Retorna resistência em ohms e tolerância.
 */
public class CodigoCoresResistor {

    /** Map simples: cores -> dígitos e multiplicadores */
    public static int digit(String color) {
        switch (color.toLowerCase()) {
            case "black": return 0;
            case "brown": return 1;
            case "red": return 2;
            case "orange": return 3;
            case "yellow": return 4;
            case "green": return 5;
            case "blue": return 6;
            case "violet": return 7;
            case "gray": return 8;
            case "white": return 9;
            default: throw new IllegalArgumentException("Cor desconhecida: " + color);
        }
    }

    public static double multiplier(String color) {
        switch (color.toLowerCase()) {
            case "black": return 1;
            case "brown": return 10;
            case "red": return 100;
            case "orange": return 1000;
            case "yellow": return 10000;
            case "green": return 100000;
            case "blue": return 1e6;
            case "gold": return 0.1;
            case "silver": return 0.01;
            default: return 1;
        }
    }

    public static String tolerance(String color) {
        switch (color.toLowerCase()) {
            case "brown": return "±1%";
            case "red": return "±2%";
            case "gold": return "±5%";
            case "silver": return "±10%";
            default: return "±20%";
        }
    }

    public static double decode(String d1, String d2, String mult, String tol) {
        int v1 = digit(d1);
        int v2 = digit(d2);
        double m = multiplier(mult);
        return ((v1 * 10) + v2) * m;
    }

    public static void main(String[] args) {
        double r = decode("red", "violet", "yellow", "gold");
        System.out.println("R = " + r + " Ω, tol = " + tolerance("gold"));
    }
}
