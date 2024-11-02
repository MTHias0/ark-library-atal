package base.collections.algorithms;

import java.util.Comparator;
import base.collections.datastructures.DynamicArray;

public class Quicksort {

    public static <T> void sort(DynamicArray<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            return;
        }
        quicksort(list, 0, list.size() - 1, comparator);
    }

    private static <T> void quicksort(DynamicArray<T> list, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int pi = partition(list, left, right, comparator);
            quicksort(list, left, pi - 1, comparator);
            quicksort(list, pi + 1, right, comparator);
        }
    }

    private static <T> int partition(DynamicArray<T> list, int left, int right, Comparator<T> comparator) {
        T pivot = (T) list.find(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (comparator.compare((T) list.find(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, right);
        return i + 1;
    }

    private static <T> void swap(DynamicArray<T> list, int i, int j) {
        T temp = (T) list.find(i);
        list.set(i, (T) list.find(j));
        list.set(j, temp);
    }
}
