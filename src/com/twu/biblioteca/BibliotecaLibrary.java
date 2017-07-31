package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BibliotecaLibrary {

    private List<Book> books;
    private List<Movie> movies;

    public BibliotecaLibrary() {
        books = buildPreExistingList();
        movies = buildPreExistingMovies();
    }

    private List<Book> buildPreExistingList() {
        return Stream.of("book1", "book2")
                .map(Book::new)
                .collect(toList());
    }

    private List<Movie> buildPreExistingMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("movie1", 2006, "director1", 8));
        movies.add(new Movie("movie2", 1980, "director2", 9));
        movies.add(new Movie("movie3", 2015, "director3", 2));
        return movies;
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

    public boolean returnBook(String bookName) {
        Optional<Book> findBook = books.stream()
                .filter(book -> book.isCheckOut() && book.getName().equals(bookName))
                .findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckOut(false);
            return true;
        }
        return false;
    }
}
