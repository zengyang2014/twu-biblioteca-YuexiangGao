package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.List;
import java.util.Optional;
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

    public boolean checkOutBook(String bookName) {
        Optional<Book> findBook = books.stream()
                .filter(book -> !book.isCheckOut() && book.getName().equals(bookName))
                .findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckOut(true);
            return true;
        }
        return false;
    }
}
