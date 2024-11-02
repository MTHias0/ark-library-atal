package base.core.service;

import base.collections.datastructures.DynamicArray;
import base.collections.algorithms.Quicksort;
import base.core.model.Book;

import java.util.Comparator;

public class BookService {
    private final DynamicArray<Book> books = new DynamicArray<>();
    private long currentCode = 1;

    public void create(Book book) {
        book.setCode(currentCode++); // Assign a unique code to each new book
        books.add(book);
    }

    public DynamicArray<Book> findAll(boolean orderByTitle) {
        DynamicArray<Book> sortedBooks = new DynamicArray<>(books.size());
        for (int i = 0; i < books.size(); i++) {
            sortedBooks.add((Book) books.find(i));
        }

        if (orderByTitle) {
            quickSort(sortedBooks, Comparator.comparing(Book::getTitle));
        } else {
            quickSort(sortedBooks, Comparator.comparing(Book::getAuthor));
        }
        return sortedBooks;
    }

    public Book findById(Long code) {
        for (int i = 0; i < books.size(); i++) {
            Book book = (Book) books.find(i);
            if (book.getCode().equals(code)) {
                return book;
            }
        }
        return null;
    }

    private void quickSort(DynamicArray<Book> books, Comparator<Book> comparator) {
        Quicksort.sort(books, comparator);
    }
}
