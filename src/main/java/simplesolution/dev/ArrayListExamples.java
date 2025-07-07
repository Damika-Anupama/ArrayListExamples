import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.common.value.qual.MinLen;

public class ArrayListExamples {

    public static <T> T getLast(@MinLen(1) List<T> list) {
        // list.size() - 1 is safe because list has at least 1 element
        return list.get(list.size() - 1);
    }

    public static void removeLast(@MinLen(1) List<?> list) {
        list.remove(list.size() - 1);
    }

    public static <T> @IndexOrLow("#1") int findIndex(List<T> list, T target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> void swap(List<T> list,
                                @IndexFor("#1") int i,
                                @IndexFor("#1") int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static <T> List<T> copyRange(List<T> source,
                                       @IndexOrHigh("#1") int from,
                                       @IndexOrHigh("#1") int to) {
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

        final @LengthOf("list") int N = (@LengthOf("list") int) list.size();
        for (int idx = 0; idx < N; idx++) {
            System.out.println(list.get(idx));
        }

        String last = getLast((List<@MinLen(1) String>) list);
        System.out.println("Last element was: " + last);
        removeLast((List<@MinLen(1) ?>) list);

        // Swap first two elements if list has at least 2 elements
        if (list.size() > 1) {
            // Cast to @MinLen(2) to satisfy the Index Checker that list length >= 2
            swap((List<@MinLen(2) String>) list, 0, 1);
        }

        // Find an element and use the index if found
        @IndexOrLow("list") int index = findIndex(list, "alpha");
        if (index != -1) {
            // index is guaranteed to be a valid index here (not -1)
            System.out.println("Found \"" + list.get(index) + "\" at index " + index);
        }

        // Copy the entire list using indices [0, list.size())
        List<String> listCopy = copyRange(list, 0, (@IndexOrHigh("list") int) list.size());
        System.out.println("Copy has " + listCopy.size() + " elements.");
    }
}
