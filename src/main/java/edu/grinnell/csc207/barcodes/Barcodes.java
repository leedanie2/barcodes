package edu.grinnell.csc207.barcodes;

/** 
 * Class for UPC-A barcodes as 12-digit codes 
 * represented by black and white squares
*/
public class Barcodes {
    /** Encodings for UPC-A digits 0 - 9 */
    private static int[][] ENCODINGS = {
        {3, 2, 1, 1}, {2, 2, 2, 1}, {2, 1, 2, 2}, 
        {1, 4, 1, 1}, {1, 1, 3, 2}, {1, 2, 3, 1}, 
        {1, 1, 1, 4}, {1, 3, 1, 2}, {1, 2, 1, 3}, 
        {3, 1, 1, 2}
    };

    /**
     * Converts a character to a corresponding integer
     *
     * @param ch a character digit ('0'-'9')
     * @return the integer value of the digit (0-9)
     */
    public static int toDigit(char ch) {
        return ch - '0';
    }

    /**
     * Checks if a code is a valid UPC-A barcode.
     * To be valid, must be exactly 12 digits long and
     * each character must be a digit.
     *
     * @param code the code to validate as a string
     * @return true if the code is valid, otherwise false
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
     * Compute the expected check digit (12th digit) of a barcode
     * If C = 0, check digit is 0; otherwise, check digit is 10 - C.
     *
     * @param code a valid barcode
     * @return the expected check digit (0-9)
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

    /** 
     * Prints a black square
     */
    public static void printBlack() {
        System.out.print("\033[30m█\033[0m");
    }

    /** 
     * Prints a white square
     */
    public static void printWhite() {
        System.out.print("\033[37m█\033[0m");
    }

    /**
     * Prints the Quiet Zone as 9 consecutive white squares
     */
    public static void printQuietZone() {
        for(int i = 0; i < 9; i++) {
            printWhite();
        }
    }

    /**
     * Prints the Start Zone as BWB
     */
    public static void printStartZone() {
        printBlack();
        printWhite();
        printBlack();
    }

    /**
     * Prints white or black for a digit using its encodings 
     * based on orientation given by inverted
     * Each digit has 4 numbers, which alternate between white and black
     * 
     * @param digit the digit to print its encodings of
     * @param inverted whether white (false) or black (true) prints first
     */
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

    /**
     * Prints Left Side, or first 6 digits of the barcode
     * @param digits valid 12 digit barcode
     */
    public static void printLeftSide(String digits) {
        for(int i = 0; i < 6; i++) {
            int digit = toDigit(digits.charAt(i));
            printDigit(digit, false); // not inverted
        }
    }

    /**
     * Prints Middle Zone as WBWBW
     */
    public static void printMiddleZone() {
        for(int i = 0; i < 5; i++) {
            if(i % 2 == 0) {
                printWhite();
            } else {
                printBlack();
            }
        }
    }

    /**
     * Prints Right Side, or last 6 digits of the barcode
     * 
     * @param digits valid 12 digit barcode
     */
    public static void printRightSide(String digits) {
        for(int i = 0; i < 6; i++) {
            int digit = toDigit(digits.charAt(i));
            printDigit(digit, true); // inverted
        }
    }

    /**
     * Prints a barcode as white and black squares based on 12 digit barcode
     * 
     * @param code valid 12 digit barcode
     */
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
     * Main program for barcode
     * Checks and handles errors for if:
     * (1) 2 command-line arguments are given,
     * (2) given barcode is valid, 
     * (3) height is positive, 
     * (4) check digit is computed correctly
     * 
     * If any of those 4 conditions aren't met, program prints error and exits
     * If all are met, program prints barcode as white and black squares
     * 
     * @param args Two command-line arguments: 12 digit barcode and height
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