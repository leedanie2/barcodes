package edu.grinnell.csc207.exploration;


import java.util.Scanner;
public class StringExploration {

    /**
     * Intersperses a comma between the strings in an array
     *
     * @param strs an array of strings
     * @return a string with strings in strs separated by commas
     */
    public static String intersperse(String[] strs) {
        String myStr = new String();
        int i;
        for(i = 0; i < strs.length - 1; i++) {
            myStr = myStr + strs[i] + ",";
        }
        return myStr + strs[i];
    }

    /**
     * Reorders a given full name assuming no commas in the name
     *
     * @param fullName string given in the form <last name>,<first name>,<middle name>
     * @return a string in the form <first name> <middle name> <last name>
     */
    public static String parseName(String fullName) {
        String[] nameArr = fullName.split(",");
        return nameArr[1] + " " + nameArr[2] + " " + nameArr[0];
    }

    /**
     * Prompts user with a given question until some non-case sensitive yes or no is given
     *
     * @param question string that can be interpreted as a y/n question
     * @return true if some form of y is given
     *        false if some form of n is given
     */
    public static boolean forgivingPrompt(String question) {
        Scanner scanner = new Scanner(System.in);

        String[] yes = new String[] {"y", "yes", "yep"};
        String[] no = new String[] {"n", "no", "nope"};

        while (true) {
            System.out.println(question);
            String response = scanner.nextLine().toLowerCase();
            
            for (int i = 0; i < 3; i++) {
                if (response.equals(yes[i])) {
                    return true;
                } else if (response.equals(no[i])) {
                    return false;
                }
            }
        }
    }

    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java -q "-Dexec.mainClass=edu.grinnell.csc207.exploration.StringExploration"
    public static void main(String[] args) {
        // test intersperse: expect "ab,cd,ef"
        String[] strs = new String[] { "ab", "cd", "ef" };
        System.out.println(intersperse(strs));

        // test parseName: expect "Alan Mathison Turing"
        System.out.println(parseName("Turing,Alan,Mathison"));

        // test forgivingPrompt
        forgivingPrompt("Are you dumb?");
    }
}
