import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");

        ArrayList<String> oddNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 1) {
                oddNumbers.add(numbers[i]);
            }
        }

        Collections.reverse(oddNumbers);
        for (String number : oddNumbers) {
            System.out.print(number + " ");
        }
    }
}