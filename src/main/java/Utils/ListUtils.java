package Utils;

import com.google.common.collect.Ordering;

import java.util.List;


public abstract class ListUtils {
    public static boolean isListSortedDescending(List<? extends Comparable> sortedList) {
        return Ordering.natural().reverse().isOrdered(sortedList);
    }
}