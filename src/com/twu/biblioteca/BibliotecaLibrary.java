package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BibliotecaLibrary {

    private List<Book> books;

    public BibliotecaLibrary() {
        books = buildPreExistingList();
    }

    private List<Book> buildPreExistingList() {
        return Stream.of("book1", "book2")
                .map(Book::new)
                .collect(toList());
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getWelcome() {
        return "Welcome to Yuexiang's Biblioteca";
    }
}
