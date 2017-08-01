package com.twu.biblioteca.model;

public class Book {
    private int yearPublished;
    private String author;
    private String name;
    private boolean isCheckOut;

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, String author, int yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getYearPublished() != book.getYearPublished()) return false;
        if (isCheckOut() != book.isCheckOut()) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        return getName() != null ? getName().equals(book.getName()) : book.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getYearPublished();
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (isCheckOut() ? 1 : 0);
        return result;
    }

    public String getAuthor() {
        return author;
    }
// you can delete useless getter and setter
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String loadDetail() {
        return String.format("%s, %s, %d", getName(), getAuthor(), getYearPublished());
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }
}
