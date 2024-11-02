package base.collections.datastructures;

import java.util.Arrays;

public class DynamicArray<T> {
    private final int DEFAULT_BREAK_VALUE = 3;

    private int size;
    private int capacity = 3;
    private Object[] array;

    public DynamicArray() {
        this.array = new Object[this.capacity];
    }

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    private Object[] grow() {
        this.capacity += DEFAULT_BREAK_VALUE;
        return Arrays.copyOf(this.array, this.capacity);
    }

    public void add(T element) {
        if (size >= capacity) {
            this.array = grow();
        }

        array[size] = element;
        size++;
    }

    public Object[] shrink() {
        this.capacity -= DEFAULT_BREAK_VALUE;
        return Arrays.copyOf(this.array, capacity);
    }

    public void remove(int index) {
        if (capacity - size >= DEFAULT_BREAK_VALUE) {
            this.array = shrink();
        }

        this.array[index] = null;
        size--;
    }

    public Object find(int index) {
        return array[index];
    }

    public void set(int index, T element) {
        array[index] = element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder elements = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            elements.append(array[i])
                    .append(i == size - 1 ? "]" : ", ");
        }

        return elements.toString();
    }
}
