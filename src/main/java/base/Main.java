package base;

import base.collections.DynamicArray;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DynamicArray<String> array = new DynamicArray<>();

        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("Hello");
        array.add("removido 1");
        array.add("removido 2");
        array.add("removido 3");

        array.remove(array.size() - 1);
        array.remove(array.size() - 1);
        array.remove(array.size() - 1);
        array.remove(array.size() - 1);
        array.remove(array.size() - 1);
        array.remove(array.size() - 1);

        System.out.println(array);

    }
}