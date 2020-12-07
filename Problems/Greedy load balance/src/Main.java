import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();

        int firstLoad = 0;
        int secondLoad = 0;

        for (int i = 0; i < count; i++) {
            if (firstLoad <= secondLoad) {
                first.add(scanner.nextInt());
                firstLoad += scanner.nextInt();
            } else {
                second.add(scanner.nextInt());
                secondLoad += scanner.nextInt();
            }
        }

        for (int i : first) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : second) {
            System.out.print(i + " ");
        }
    }
}