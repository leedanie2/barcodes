package edu.grinnell.csc207.barcodes;

public class Barcodes {
    /** TODO: fill in this definition and doc comment! */
    private static int[][] ENCODINGS = {
        // TODO: fill me in!
    };

    /**
     * TODO: fill in this definition and doc comment!
     */
    public static int toDigit(char ch) {
        return ch - '0';
    }

    /**
     * TODO: fill in this definition and doc comment!
     */
    public static boolean isValidCode(String code) {
        if(code.length() != 12) {
            return false;
        }
        for(int i = 0; i < 12; i++) {
            if(!Character.isDigit(toDigit(code.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * TODO: fill in this definition and doc comment!
     */
    public static int computeCheckDigit(String code) {
        int C = 0;
        for(int i = 0; i < 10; i++) {
            C += 3 * toDigit(code.charAt(i));
            C += toDigit(code.charAt(++i));
        }
        C = C % 10;
        if (C == 0) {
            return 0;
        } else {
            return 10 - C;
        }
    }

    public static void printBlack() {
        System.out.println("\033[30m█\033[0m");
    }

    public static void printWhite() {
        System.out.println("\033[37m█\033[0m");
    }

    /**
     * TODO: fill in this definition and doc comment!
     * @param args
     */
    public static void main(String[] args) {
        
    }
}