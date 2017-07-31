package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.twu.biblioteca.enums.ConsoleState.CHECK_OUT_BOOK;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
import static com.twu.biblioteca.enums.ConsoleState.RETURN_BOOK;
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
    public void should_print_book_list_without_checked_out_book() throws Exception {
        List<Book> books = library.getBooks();
        BibliotecaApp.checkOut(library, "book1");
        StringBuilder listStr = new StringBuilder();
        books = books.stream().filter(book1 -> !book1.isCheckOut()).collect(Collectors.toList());
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.isCheckOut()) {
                listStr.append(String.format("%d. %s\n", i + 1, book.loadDetail()));
            }
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
        BibliotecaApp.checkOut(library, "book1");
        assertTrue(book.isCheckOut());
    }

    @Test
    public void should_make_state_to_be_command_after_checkout_a_book() throws Exception {
        BibliotecaApp.state = CHECK_OUT_BOOK;
        BibliotecaApp.checkOut(library, "book1");
        assertEquals(COMMAND, BibliotecaApp.state);
    }

    @Test
    public void should_change_state_to_be_checkout_when_choose_checkout_option() throws Exception {
        BibliotecaApp.state = COMMAND;
        String checkout = "co";
        BibliotecaApp.parseCommand(library, checkout);
        assertEquals(CHECK_OUT_BOOK, BibliotecaApp.state);
    }

    @Test
    public void should_return_true_without_any_print_when_check_out_success() throws Exception {
        BibliotecaApp.state = CHECK_OUT_BOOK;
        assertTrue(BibliotecaApp.checkOut(library, "book1"));
        assertEquals("", outputMonitor.toString());
    }

    @Test
    public void should_return_false_without_any_print_when_check_out_failed() throws Exception {
        BibliotecaApp.state = CHECK_OUT_BOOK;
        assertFalse(BibliotecaApp.checkOut(library, "not exist book"));
        assertEquals("", outputMonitor.toString());
    }

    @Test
    public void should_print_successful_message_when_check_out_success() throws Exception {
        BibliotecaApp.printCheckOutResult(true);
        assertEquals("Thank you! Enjoy the book\n", outputMonitor.toString());
    }

    @Test
    public void should_print_unsuccessful_message_when_check_out_book_failed() throws Exception {
        BibliotecaApp.printCheckOutResult(false);
        assertEquals("That book is not available\n", outputMonitor.toString());
    }

    @Test
    public void should_change_book_isCheckOut_to_be_false_when_return_success() throws Exception {
        List<Book> books = library.getBooks();
        Book book = books.get(0);
        book.setCheckOut(true);
        BibliotecaApp.returnBook(library, "book1");
        assertFalse(book.isCheckOut());
    }

    @Test
    public void should_change_state_to_command_after_return_book() throws Exception {
        BibliotecaApp.state = RETURN_BOOK;
        String bookName = "book1";
        library.checkOutBook(bookName);
        BibliotecaApp.returnBook(library, bookName);
        assertEquals(COMMAND, BibliotecaApp.state);
    }

    @Test
    public void should_change_state_to_returnBook_when_choose_return_book_option() throws Exception {
        BibliotecaApp.state = COMMAND;
        BibliotecaApp.parseCommand(null, "rb");
        assertEquals(RETURN_BOOK, BibliotecaApp.state);
    }

    @Test
    public void should_return_true_without_any_print_when_return_book_success() throws Exception {
        String bookName = "book1";
        library.checkOutBook(bookName);
        assertTrue(BibliotecaApp.returnBook(library, bookName));
        assertEquals("", outputMonitor.toString());
    }

    @Test
    public void should_return_false_without_any_print_when_return_book_failed() throws Exception {
        assertFalse(BibliotecaApp.returnBook(library, "book1"));
        assertEquals("", outputMonitor.toString());
    }

    @Test
    public void should_print_successful_message_when_return_book_success() throws Exception {
        BibliotecaApp.printReturnBookResult(true);
        assertEquals("Thank you for returning the book.\n", outputMonitor.toString());
    }

    @Test
    public void should_print_unsuccessful_message_when_return_book_failed() throws Exception {
        BibliotecaApp.printReturnBookResult(false);
        assertEquals("That is not a valid book to return\n", outputMonitor.toString());
    }

    @Test
    public void should_print_movie_list_without_checked_out_movies() throws Exception {
        List<Movie> movies = library.getMovies();
        StringBuilder listStr = new StringBuilder();
        for (int i = 0; i < movies.size(); i++) {
            listStr.append(String.format("%d. %s\n", i + 1, movies.get(i).loadDetail()));
        }
        BibliotecaApp.parseCommand(library, "lm");
        assertEquals(listStr.toString(), outputMonitor.toString());
    }
}