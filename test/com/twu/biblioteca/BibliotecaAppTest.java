package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaAppTest {

    @Test
    public void should_return_welcome_message() throws Exception {
        String expectedWelcome = "Welcome to Yuexiang's Biblioteca";
        assertEquals(expectedWelcome, new BibliotecaApp().getWelcome());
    }
}