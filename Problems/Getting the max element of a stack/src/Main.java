import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int commands = scanner.nextInt();
        scanner.nextLine();

        Stack<Integer> numberStack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        int maxNow = Integer.MIN_VALUE;

        for (int i = 0; i < commands; i++) {
            String line = scanner.nextLine();
            if (line.contains("push")) {
                int toPush = Integer.parseInt(line.split(" ")[1]);
                if (toPush > maxNow) {
                    maxNow = toPush;
                }
                numberStack.push(toPush);
                maxStack.push(maxNow);
            } else if (line.contains("max")) {
                System.out.println(maxStack.peek());
            } else if (line.contains("pop") && !numberStack.isEmpty()) {
                numberStack.pop();
                maxStack.pop();
                maxNow = maxStack.peek();
            }
        }
    }
}