package edu.grinnell.csc207.exploration;

public class FizzBuzz {
    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java -q "-Dexec.mainClass=edu.grinnell.csc207.exploration.FizzBuzz"
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for(int i = 1; i <= n; i ++) {
            FizzBuzzHelper(i);
        }
    }
    public static void FizzBuzzHelper(int n) {
        String tmp = new String();
        boolean printNum = true;
        if (n % 3 == 0) {
            tmp = tmp + "fizz";
            printNum = false;
        }
        if (n % 5 == 0) {
            tmp = tmp + "buzz";
            printNum = false;
        }
        if (printNum) { // print number if nothing else
            System.out.println(n);
        } else {
            System.out.println(tmp);
        }
    }
}
