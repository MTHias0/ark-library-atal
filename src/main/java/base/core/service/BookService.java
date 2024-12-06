package base.core.service;

import base.collections.algorithms.BinarySearchTree;
import base.core.model.Book;

import java.util.Comparator;
import java.util.List;

public class BookService {
    private final BinarySearchTree<Book> booksByTitle = new BinarySearchTree<>();
    private final BinarySearchTree<Book> booksByAuthor = new BinarySearchTree<>();
    private long currentCode = 1;

    public void create(String title, String author, int year) {
        Book book = new Book();
        book.setCode(currentCode++);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);

        booksByTitle.insert(book, Comparator.comparing(Book::getTitle));
        booksByAuthor.insert(book, Comparator.comparing(Book::getAuthor));
    }

    public List<Book> findAll(boolean orderByTitle) {
        return orderByTitle ? booksByTitle.inOrder() : booksByAuthor.inOrder();
    }

    public Book findByTitle(String title) {
        return booksByTitle.search(new Book(null, title, null, null),
                Comparator.comparing(Book::getTitle));
    }

    public Book findByAuthor(String author) {
        return booksByAuthor.search(new Book(null, null, author, null),
                Comparator.comparing(Book::getAuthor));
    }
}
