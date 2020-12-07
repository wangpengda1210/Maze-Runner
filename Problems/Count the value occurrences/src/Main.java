import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method

        return list1.stream().filter(number -> number == elem).count() == list2.stream().filter(number -> number == elem).count();
    }
}