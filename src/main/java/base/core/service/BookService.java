package base.core.service;

import base.collections.DynamicArray;
import base.core.model.Book;

public class BookService {
    private final DynamicArray<Book> books = new DynamicArray<>();

    public void create(Book book) {


        books.add(book);
    }

    public DynamicArray<Book> findAll(boolean orderByTitle) {
        return null;
    }

    public Book findById(Long id) {
        return null;
    }
}
