import java.util.Scanner;
import java.util.Stack;

class PalindromeChecker {
    private String text;

    public PalindromeChecker(String text) {
        this.text = text;
    }

    public boolean checkPalindrome() {
        Stack<Character> stack = new Stack<>();
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();

        for (int i = 0; i < cleanText.length(); i++) {
            stack.push(cleanText.charAt(i));
        }

        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        return cleanText.equals(reversed);
    }
}

public class UseCase11PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Palindrome Checker App ===");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        PalindromeChecker checker = new PalindromeChecker(input);

        if (checker.checkPalindrome()) {
            System.out.println("\"" + input + "\" is a palindrome.");
        } else {
            System.out.println("\"" + input + "\" is NOT a palindrome.");
        }

        scanner.close();
    }
}