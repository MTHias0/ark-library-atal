package base;

import base.core.model.Book;
import base.core.service.BookService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        JButton listButton = new JButton("List Books");
        JButton findButton = new JButton("Find Book by Code");

        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(findButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Button Action Listeners
        addButton.addActionListener(e -> addBookDialog());
        listButton.addActionListener(e -> listBooksDialog());
        findButton.addActionListener(e -> findBookDialog());
    }

    private void addBookDialog() {
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField yearField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(Box.createHorizontalStrut(15)); // Space between fields
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(Box.createHorizontalStrut(15));
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel,
                "Add Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setYear(year);

            bookService.create(book);
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        }
    }

    private void listBooksDialog() {
        Object[] options = {"Title", "Author"};
        int orderBy = JOptionPane.showOptionDialog(this, "Order by:",
                "Order Books", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        boolean orderByTitle = orderBy == 0;

        // Clear current table data
        tableModel.setRowCount(0);

        // Populate table with sorted book list
        for (int i = 0; i < bookService.findAll(orderByTitle).size(); i++) {
            Book book = (Book) bookService.findAll(orderByTitle).find(i);
            tableModel.addRow(new Object[]{book.getCode(), book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }

    private void findBookDialog() {
        String codeStr = JOptionPane.showInputDialog(this, "Enter Book Code:");
        if (codeStr != null) {
            long code = Long.parseLong(codeStr);
            Book book = bookService.findById(code);
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
