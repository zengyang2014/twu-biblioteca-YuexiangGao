package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static com.twu.biblioteca.enums.ConsoleState.CHECKOUT;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
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
            listStr.append(String.format("%d. %s\n", i + 1, book.loadDetail()));
        }
        BibliotecaApp.printBookList(library);
        assertEquals(listStr.toString(), outputMonitor.toString());
    }

    @Test
    public void should_print_book_list_when_choose_list_book_option_and_state_is_command() throws Exception {
        List<Book> books = library.getBooks();
        StringBuilder listStr = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            listStr.append(String.format("%d. %s\n", i + 1, books.get(i).loadDetail()));
        }
        BibliotecaApp.parseCommand(library, "1");
        assertEquals(listStr.toString(), outputMonitor.toString());
    }

    @Test
    public void should_return_true_when_option_is_list_book() throws Exception {
        assertTrue(BibliotecaApp.parseCommand(library, "1"));
    }

    @Test
    public void should_print_main_menu() throws Exception {
        StringBuilder mainMenuBuilder = new StringBuilder(" Main Menu \n");
        mainMenuBuilder.append("command | action\n");
        BibliotecaApp.mainMenu.forEach((key, value) ->
                mainMenuBuilder.append(String.format("%s    %s\n", key, value)));
        BibliotecaApp.printMainMenu();
        assertEquals(mainMenuBuilder.toString(), outputMonitor.toString());
    }

    @Test
    public void should_print_invalid_notification() throws Exception {
        BibliotecaApp.printInvalidOptoinNotification();
        assertEquals("Select a valid option!\n", outputMonitor.toString());
    }

    @Test
    public void should_print_invalid_notification_when_choose_an_invalid_option() throws Exception {
        BibliotecaApp.parseCommand(null, "invalid option");
        assertEquals("Select a valid option!\n", outputMonitor.toString());
    }

    @Test
    public void should_return_true_when_choose_an_invalid_option() throws Exception {
        assertTrue(BibliotecaApp.parseCommand(null, "invalid option"));
    }

    @Test
    public void should_return_false_when_option_is_quit() throws Exception {
        assertFalse(BibliotecaApp.parseCommand(null, "q"));
    }

    @Test
    public void should_make_book_isCheckOut_to_be_true_when_check_out_success() throws Exception {
        Book book = library.getBooks().get(0);
        BibliotecaApp.parseCheckOut(library, "book1");
        assertTrue(book.isCheckout());
    }

    @Test
    public void should_make_state_to_be_command_after_checkout_a_book() throws Exception {
        BibliotecaApp.state = CHECKOUT;
        BibliotecaApp.parseCheckOut(library, "book1");
        assertEquals(COMMAND, BibliotecaApp.state);
    }
}