import java.util.Scanner;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

// Strategy interface
interface PalindromeStrategy {
    boolean isPalindrome(String text);
}

// Stack-based strategy
class StackStrategy implements PalindromeStrategy {
    @Override
    public boolean isPalindrome(String text) {
        Stack<Character> stack = new Stack<>();
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();

        for (char c : cleanText.toCharArray()) {
            stack.push(c);
        }

        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        return cleanText.equals(reversed);
    }
}

// Deque-based strategy
class DequeStrategy implements PalindromeStrategy {
    @Override
    public boolean isPalindrome(String text) {
        Deque<Character> deque = new ArrayDeque<>();
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();

        for (char c : cleanText.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

// Context class using Strategy pattern
class PalindromeCheckerContext {
    private PalindromeStrategy strategy;

    public void setStrategy(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean check(String text) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set!");
        }
        return strategy.isPalindrome(text);
    }
}

// Main application
public class UseCase12PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PalindromeCheckerContext checker = new PalindromeCheckerContext();

        System.out.println("=== Advanced Palindrome Checker App (Strategy Pattern) ===");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        System.out.println("Choose algorithm strategy: ");
        System.out.println("1. Stack-based");
        System.out.println("2. Deque-based");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> checker.setStrategy(new StackStrategy());
            case 2 -> checker.setStrategy(new DequeStrategy());
            default -> {
                System.out.println("Invalid choice. Using Stack-based by default.");
                checker.setStrategy(new StackStrategy());
            }
        }

        if (checker.check(input)) {
            System.out.println("\"" + input + "\" is a palindrome.");
        } else {
            System.out.println("\"" + input + "\" is NOT a palindrome.");
        }

        scanner.close();
    }
}