package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BibliotecaApp {

    static Map<String, String> mainMenu;

    static {
        mainMenu = new HashMap<>();
        mainMenu.put("1", "List Book");
        mainMenu.put("q", "Quit");
    }

    public static void main(String[] args) {
        BibliotecaLibrary library = new BibliotecaLibrary();
        printWelcome(library);
        printMainMenu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = mainMenu.get(scanner.next());
            if (command == null) {
                printInvalidOptoinNotification();
            } else {
                if (command.equals("List Book")) {
                    printBookList(library);
                } else if (command.equals("Quit")) {
                    break;
                }
            }
            printMainMenu();
        }
        scanner.close();
    }

    static void printWelcome(BibliotecaLibrary library) {
        System.out.println(library.getWelcome());
    }

    static void printBookList(BibliotecaLibrary library) {
        List<Book> books = library.getBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, books.get(i).loadDetail()));
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
}
