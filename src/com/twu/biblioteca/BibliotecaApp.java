package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaLibrary library = new BibliotecaLibrary();
        printWelcome(library);
    }

    static void printWelcome(BibliotecaLibrary library) {
        System.out.println(library.getWelcome());
    }

    static void printBookList(BibliotecaLibrary library) {
        List<Book> books = library.getBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, books.get(i)));
        }
    }
}
