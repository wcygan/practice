package io.wcygan.algorithms.miscellaneous;

import java.util.List;

public class BinarySearch {
    public static <T extends Comparable<T>> int search(List<T> list, T toFind) {
        int lo = 0;
        int hi = list.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (list.get(mid).compareTo(toFind) == 0) {
                return mid;
            } else if (list.get(mid).compareTo(toFind) < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return -1;
    }
}
