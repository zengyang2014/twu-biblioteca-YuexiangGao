package com.twu.biblioteca.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void should_load_detail_with_name_author_and_year_published() throws Exception {
        String name = "book";
        String author = "author";
        int yearPublished = 2016;
        Book book = new Book(name, author, yearPublished);
        assertEquals(String.format("%s, %s, %d", name, author, yearPublished), book.loadDetail());
    }
}