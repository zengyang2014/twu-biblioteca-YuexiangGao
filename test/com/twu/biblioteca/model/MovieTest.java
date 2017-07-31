package com.twu.biblioteca.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void should_construct_rating_to_be_given_number_when_number_between_1_to_10() throws Exception {
        int expectRating = 1;
        Movie movie = new Movie("name", 2008, "me", expectRating);
        assertEquals(expectRating, movie.getRating());
    }

    @Test
    public void should_construct_rating_to_0_when_given_number_not_between_1_to_10() throws Exception {
        Movie movie = new Movie("name", 2008, "me", -10);
        assertEquals(0, movie.getRating());
    }

    @Test
    public void should_set_rating_when_given_number_is_between_1_to_10() throws Exception {
        Movie movie = new Movie("name", 2008, "me", 1);
        int expectRating = 5;
        movie.setRating(expectRating);
        assertEquals(expectRating, movie.getRating());
    }

    @Test
    public void shuold_set_rating_to_0_when_given_number_is_not_between_1_to_10() throws Exception {
        Movie movie = new Movie("name", 2008, "me", 1);
        movie.setRating(30);
        assertEquals(0, movie.getRating());
    }
}