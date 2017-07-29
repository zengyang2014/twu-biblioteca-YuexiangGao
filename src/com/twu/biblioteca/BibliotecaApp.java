package com.twu.biblioteca;

import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.Book;

import java.util.*;
import java.util.stream.Collectors;

import static com.twu.biblioteca.enums.ConsoleState.CHECKOUT;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;

public class BibliotecaApp {

    static Map<String, String> mainMenu;
    static ConsoleState state;

    static {
        state = COMMAND;
        mainMenu = new HashMap<>();
        mainMenu.put("1", "List Book");
        mainMenu.put("co", "Check Out");
        mainMenu.put("q", "Quit");
    }

    public static void main(String[] args) {
        BibliotecaLibrary library = new BibliotecaLibrary();
        printWelcome(library);
        printMainMenu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (state == COMMAND) {
                if (!parseCommand(library, input)) break;
                if (state == COMMAND) {
                    printMainMenu();
                }
            } else if (state == CHECKOUT) {
                printCheckOutResult(checkOut(library, input));
                printMainMenu();
            }
        }
        scanner.close();
    }

    static boolean parseCommand(BibliotecaLibrary library, String input) {
        String option = mainMenu.get(input);
        if (option == null) {
            printInvalidOptoinNotification();
        } else {
            switch (option) {
                case "List Book":
                    printBookList(library);
                    break;
                case "Check Out":
                    state = CHECKOUT;
                    break;
                case "Quit":
                    return false;
            }
        }
        return true;
    }

    private static void printCheckOutResult(boolean result) {
        System.out.println(result ?
                "Thank you! Enjoy the book" :
                "That book is not available");
    }

    static boolean checkOut(BibliotecaLibrary library, String bookName) {
        state = COMMAND;
        return library.checkOutBook(bookName);
    }

    static void printWelcome(BibliotecaLibrary library) {
        System.out.println(library.getWelcome());
    }

    static void printBookList(BibliotecaLibrary library) {
        List<Book> books = library.getBooks().stream().filter(book1 -> !book1.isCheckOut()).collect(Collectors.toList());
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.isCheckOut()) {
                System.out.println(String.format("%d. %s", i + 1, book.loadDetail()));
            }
        }
    }

    static void printMainMenu() {
        System.out.println(" Main Menu ");
        System.out.println("command | action");
        mainMenu.forEach((key, value) ->
                System.out.println(String.format("%s    %s", key, value)));
    }

    static void printInvalidOptoinNotification() {
        System.out.println("Select a valid option!");
    }

    static boolean returnBook(BibliotecaLibrary library, String bookName) {
        state = COMMAND;
        return library.returnBook(bookName);
    }
}
