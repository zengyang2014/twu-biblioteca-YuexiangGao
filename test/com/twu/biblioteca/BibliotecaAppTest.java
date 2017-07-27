package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaAppTest {

    private BibliotecaLibrary library;
    private ByteArrayOutputStream outputMonitor = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputMonitor));
        library = new BibliotecaLibrary();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void should_print_welcome_message() throws Exception {
        BibliotecaApp.printWelcome(library);
        assertEquals(library.getWelcome() + '\n', outputMonitor.toString());
    }

    @Test
    public void should_print_book_list() throws Exception {
        List<Book> books = library.getBooks();
        StringBuilder listStr = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            listStr.append(String.format("%d. %s\n", i + 1, book));
            assertEquals(book.getName(), book.toString());
        }
        BibliotecaApp.printBookList(library);
        assertEquals(listStr.toString(), outputMonitor.toString());
    }
}