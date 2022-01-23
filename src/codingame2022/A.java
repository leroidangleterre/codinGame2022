package codingame2022;

import java.util.Stack;

/**
 *
 * @author arthu
 */
public class A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(A.check("[()]"));   // true
        System.out.println(A.check("(()[])")); // true
        System.out.println(A.check("([)]"));   // false
        System.out.println(A.check("(("));     // false
        System.out.println(A.check("[(()])")); // false

        System.out.println(A.check("([(([[(([]))]]))])"));   // true
        System.out.println(A.check("[](()()[[]])()[]([])")); // true
        System.out.println(A.check("([((([(([]))])))))])")); // false
        System.out.println(A.check("[](()()[[]])[][[([])")); // false

        String block1 = "([(([[(([]))]]))])";
        String block2 = "[](()()[[]])()[]([])";
        String testFinal = buildString(block1, block2);
        System.out.println("Test final: " + testFinal.length());
        System.out.println(A.check(testFinal));
    }

    /**
     * Checks that the given string is​​​​​​‌​​‌​‌‌‌​‌​​​​‌‌‌‌​​‌‌‌‌​
     * correct.
     */
    static boolean check(String str) {

        if (str.isEmpty()) {
            return true;
        }

        Stack stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            char topOfStack;

            switch (c) {
            case '(':
            case '[':
                // Add the character if it is an opening one.
                stack.add(c);
                break;
            case ')':
                // Pop the last character if it matches
                topOfStack = (char) stack.peek();
                if (topOfStack == '(') {
                    // Closing parenthesis
                    stack.pop();
                } else {
                    // Error detected
                    return false;
                }
                break;
            case ']':
                // Pop the last character if it matches
                topOfStack = (char) stack.peek();
                if (topOfStack == '[') {
                    // Closing parenthesis
                    stack.pop();
                } else {
                    // Error detected
                    return false;
                }
                break;
            default:
                // Other character detected
                return false;
            }
        }
        // No error detected and string completely processed:
        // Valid only if all parentheses and brackets have been closed
        return (stack.isEmpty());
    }

    private static String buildString(String block1, String block2) {

        System.out.println("Building: " + block1.length() + ", " + block2.length());

        int maxSize = 10000;

        String build1 = "(" + block1 + ")" + block2;
        String build2 = "(([" + block1 + block2 + "]))";

        String test = build1 + build2;
        if (test.length() > maxSize) {
            return test;
        } else {
            return buildString(build1, build2);
        }
    }
}
