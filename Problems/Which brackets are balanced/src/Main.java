import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        Deque<Character> deque = new ArrayDeque<>();
        boolean result = true;
        loop:for (char c : line.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    deque.offerFirst(c);
                    break;
                case ')':
                    if (deque.peekFirst() == null || '(' != deque.peekFirst()) {
                        result = false;
                        break loop;
                    } else {
                        deque.pollFirst();
                    }
                    break;
                case ']':
                    if (deque.peekFirst() == null || '[' != deque.peekFirst()) {
                        result = false;
                        break loop;
                    } else {
                        deque.pollFirst();
                    }
                    break;
                case '}':
                    if (deque.peekFirst() == null || '{' != deque.peekFirst()) {
                        result = false;
                        break loop;
                    } else {
                        deque.pollFirst();
                    }
                    break;
            }
        }

        System.out.println(deque.isEmpty() && result);
    }
}