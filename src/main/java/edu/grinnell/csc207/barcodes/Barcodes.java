package edu.grinnell.csc207.barcodes;

public class Barcodes {
    /** Encodings for UPC-A digits 0 - 9 */
    private static int[][] ENCODINGS = {
        {3, 2, 1, 1}, {2, 2, 2, 1}, {2, 1, 2, 2}, 
        {1, 4, 1, 1}, {1, 1, 3, 2}, {1, 2, 3, 1}, 
        {1, 1, 1, 4}, {1, 3, 1, 2}, {1, 2, 1, 3}, 
        {3, 1, 1, 2}
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
            if(!Character.isDigit(code.charAt(i))) {
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
        for(int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                C += 3 * toDigit(code.charAt(i));
            } else {
                C += toDigit(code.charAt(i));
            }
        }
        C = C % 10;
        if (C == 0) {
            return 0;
        } else {
            return 10 - C;
        }
    }

    public static void printBlack() {
        System.out.print("\033[30m█\033[0m");
    }

    public static void printWhite() {
        System.out.print("\033[37m█\033[0m");
    }


    public static void printQuietZone() {
        for(int i = 0; i < 9; i++) {
            printWhite();
        }
    }

    public static void printStartZone() {
        printBlack();
        printWhite();
        printBlack();
    }

    public static void printDigit(int digit, boolean inverted) {
        int[] digits = ENCODINGS[digit];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < digits[i]; j++) {
                if(!inverted) {
                    printWhite();
                } else {
                    printBlack();
                }
            }
            inverted = !inverted; // flip between white and black every iteration
        }
    }

    public static void printLeftSide(String digits) {
        for(int i = 0; i < 6; i++) {
            int digit = toDigit(digits.charAt(i));
            printDigit(digit, false); // not inverted
        }
    }

    public static void printMiddleZone() {
        for(int i = 0; i < 5; i++) {
            if(i % 2 == 0) {
                printWhite();
            } else {
                printBlack();
            }
        }
    }

    public static void printRightSide(String digits) {
        for(int i = 0; i < 6; i++) {
            int digit = toDigit(digits.charAt(i));
            printDigit(digit, true); // inverted
        }
    }

    public static void printBarcodeRow(String code) {
        printQuietZone();
        printStartZone();
        printLeftSide(code.substring(0,6));
        printMiddleZone();
        printRightSide(code.substring(6));
        printStartZone(); // endzone is same as startzone
        printQuietZone();
        System.out.println(); // newline
    }

    /**
     * TODO: fill in this definition and doc comment!
     * @param args
     */
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage: barcode <UPC-A code> <height>");
            System.exit(1);
        }

        String code = args[0];
        if(!isValidCode(code)) {
            System.out.println("Code must be a string of 12 digits.");
            System.exit(1);
        }

        int height = Integer.parseInt(args[1]);
        if(height <= 0) {
            System.out.println("Height must be a positive integer.");
            System.exit(1);
        }

        int expected = computeCheckDigit(code);
        int actual = toDigit(code.charAt(11)); // last digit is check digit
        if(expected != actual) {
            System.out.println("Expected check digit " + expected + " but found " + actual + ".");
            System.exit(1);
        }

        for(int i = 0; i < height; i++) {
            printBarcodeRow(code);
        }
    }
}