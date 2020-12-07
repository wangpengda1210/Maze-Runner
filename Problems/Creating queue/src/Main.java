import java.util.*;

public class Main {

    public static void main(String[] args) {  
        // write your code here
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(2);
        queue.offerLast(0);
        queue.offerLast(1);
        queue.offerLast(7);

        System.out.println(queue);
    }
}