public class UseCase13PalindromeCheckerApp import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

// Strategy interface
interface PalindromeStrategy {
    boolean isPalindrome(String text);
    String getName();
}

// Stack-based strategy
class StackStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String text) {
        Stack<Character> stack = new Stack<>();
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();
        for (char c : cleanText.toCharArray()) stack.push(c);

        String reversed = "";
        while (!stack.isEmpty()) reversed += stack.pop();
        return cleanText.equals(reversed);
    }
    public String getName() { return "Stack-based"; }
}

// Deque-based strategy
class DequeStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String text) {
        Deque<Character> deque = new ArrayDeque<>();
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();
        for (char c : cleanText.toCharArray()) deque.addLast(c);

        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) return false;
        }
        return true;
    }
    public String getName() { return "Deque-based"; }
}

// Simple reverse string strategy
class ReverseStringStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String text) {
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleanText).reverse().toString();
        return cleanText.equals(reversed);
    }
    public String getName() { return "Reverse String"; }
}

// Context class
class PalindromeCheckerContext {
    private PalindromeStrategy strategy;

    public void setStrategy(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean check(String text) {
        if (strategy == null) throw new IllegalStateException("Strategy not set!");
        return strategy.isPalindrome(text);
    }
}

public class UseCase13PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Palindrome Checker Performance Comparison ===");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        PalindromeCheckerContext checker = new PalindromeCheckerContext();
        PalindromeStrategy[] strategies = {
                new StackStrategy(),
                new DequeStrategy(),
                new ReverseStringStrategy()
        };

        for (PalindromeStrategy strategy : strategies) {
            checker.setStrategy(strategy);
            long start = System.nanoTime();
            boolean result = checker.check(input);
            long end = System.nanoTime();
            long duration = end - start;
            System.out.println(strategy.getName() + ": "
                    + (result ? "Palindrome" : "Not Palindrome")
                    + " | Time: " + duration + " ns");
        }

        scanner.close();
    }
}{
}
