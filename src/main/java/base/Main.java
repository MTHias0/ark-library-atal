package base;

import base.core.model.Book;
import base.core.service.BookService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {

    private final BookService bookService = new BookService();
    private final DefaultTableModel tableModel;
    private final JTable bookTable;

    public Main() {
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Code", "Title", "Author", "Year"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Book");
        JButton listTitleButton = new JButton("List by Title");
        JButton listAuthorButton = new JButton("List by Author");
        JButton findTitleButton = new JButton("Find by Title");
        JButton findAuthorButton = new JButton("Find by Author");

        buttonPanel.add(addButton);
        buttonPanel.add(listTitleButton);
        buttonPanel.add(listAuthorButton);
        buttonPanel.add(findTitleButton);
        buttonPanel.add(findAuthorButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        addButton.addActionListener(e -> addBookDialog());
        listTitleButton.addActionListener(e -> listBooksDialog(true));
        listAuthorButton.addActionListener(e -> listBooksDialog(false));
        findTitleButton.addActionListener(e -> findBookDialog(true));
        findAuthorButton.addActionListener(e -> findBookDialog(false));
    }

    private void addBookDialog() {
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField yearField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());

            bookService.create(title, author, year);
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        }
    }

    private void listBooksDialog(boolean orderByTitle) {
        tableModel.setRowCount(0);
        List<Book> books = bookService.findAll(orderByTitle);
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getCode(), book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }

    private void findBookDialog(boolean byTitle) {
        String input = JOptionPane.showInputDialog(this, byTitle ? "Enter Title:" : "Enter Author:");
        if (input != null) {
            Book book = byTitle ? bookService.findByTitle(input) : bookService.findByAuthor(input);
            if (book != null) {
                JOptionPane.showMessageDialog(this,
                        "Book found:\nCode: " + book.getCode() +
                                "\nTitle: " + book.getTitle() +
                                "\nAuthor: " + book.getAuthor() +
                                "\nYear: " + book.getYear());
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
