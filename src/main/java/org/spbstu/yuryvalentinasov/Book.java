package org.spbstu.yuryvalentinasov;

import java.util.Objects;

public class Book {
    private String name;
    private String author;
    private String genre;
    private String shelf;

    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getShelf() {
        return shelf;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;

        boolean isNamesEquals =
                (name == null && book.name == null) || (name != null && name.equals(book.name));

        boolean isAuthorsEquals =
                (author == null && book.author == null) || (author != null && author.equals(book.author));

        boolean isGenresEquals =
                (genre == null && book.genre == null) || (genre != null && genre.equals(book.genre));

        return  isNamesEquals &&
                isAuthorsEquals &&
                isGenresEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", shelf='" + shelf + '\'' +
                '}';
    }
}
