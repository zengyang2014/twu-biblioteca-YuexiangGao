package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        System.out.println(app.getWelcome());
    }

    public String getWelcome() {
        return "Welcome to Yuexiang's Biblioteca";
    }
}
