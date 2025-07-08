package simplesolution.dev;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExamples {

    public static <T> T getLast(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static void removeLast(List<?> list) {
        list.remove(list.size() - 1);
    }

    public static <T> int findIndex(List<T> list, T target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> void swap(List<T> list,
                                int i,
                                int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static <T> List<T> copyRange(List<T> source,
                                       int from,
                                       int to) {
        List<T> result = new ArrayList<>();
        for (int k = from; k < to; k++) {
            result.add(source.get(k));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("alpha");
        list.add("beta");
        list.add("gamma");

        final int N = (int) list.size();
        for (int idx = 0; idx < N; idx++) {
            System.out.println(list.get(idx));
        }

        String last = getLast((List<String>) list);
        System.out.println("Last element was: " + last);
        removeLast((List<?>) list);

        // Swap first two elements if list has at least 2 elements
        if (list.size() > 1) {
            swap((List<String>) list, 0, 1);
        }

        // Find an element and use the index if found
        int index = findIndex(list, "alpha");
        if (index != -1) {
            System.out.println("Found \"" + list.get(index) + "\" at index " + index);
        }

        // Copy the entire list using indices [0, list.size())
        List<String> listCopy = copyRange(list, 0, list.size());
        System.out.println("Copy has " + listCopy.size() + " elements.");
    }
}
