import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < count; i++) {
            int next = scanner.nextInt();
            if (next % 2 == 0) {
                deque.offerFirst(next);
            } else {
                deque.offerLast(next);
            }
        }

        while (!deque.isEmpty()) {
            System.out.println(deque.pollFirst());
        }
    }
}