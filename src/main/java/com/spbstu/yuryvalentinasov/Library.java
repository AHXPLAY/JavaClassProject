package com.spbstu.yuryvalentinasov;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Вариант 21. Библиотека
 * Создать класс который хранит информацию о книгах
 * Поля: список кинг {@link Book}, размер полки.
 * Методы:
 * добавить книгу addBook,
 * удалить кингу deleteBook,
 * изменить существующую книгу changeBook,
 * переставить книгу на другую полку changeShelf,
 * поиск книг по их параметрам.
 */
public class Library {
    private ArrayList<Book> libraryList = new ArrayList<>();

    private final int shelfSize = 5;

    public void addBook(Book book) {
        book.setShelf(generateShelf(book));
        libraryList.add(book);
    }

    public ArrayList<Book> getLibraryList() {
        return libraryList;
    }

    public boolean deleteBook(Book book) {
        for (int i = 0; i < libraryList.size(); i++) {
            if (libraryList.get(i).equals(book)) {
                return libraryList.remove(book);
            }
        }
        return false;
    }

    public Book deleteBook(int index) {
        return libraryList.remove(index);
    }

    public Library(Book ...books) {
        for (Book book:books) {
            book.setShelf(generateShelf(book));
            libraryList.add(book);
        }
    }

    public boolean changeBook(int index, Book newBook) {
        if (index >= libraryList.size() || index < 0) return false;
        libraryList.set(index, newBook);
        return true;
    }

    public boolean changeBook(Book changingBook, Book newBook) {
        if (libraryList.contains(changingBook)) {
            int index = libraryList.indexOf(changingBook);
            return changeBook(index, newBook);
        }
        return false;
    }

    public boolean changeShelf(int index, int number) {
        if (index >= libraryList.size() || index < 0) return false;
        Book book = libraryList.get(index);
        char[] bookShelf = book.getShelf().toCharArray();
        book.setShelf(bookShelf[0] + "" + number);
        libraryList.set(index, book);
        return true;
    }

    public boolean changeShelf(Book book, int number) {
        if (libraryList.contains(book)) {
            int index = libraryList.indexOf(book);
            return changeShelf(index, number);
        }
        return false;
    }

    public ArrayList<Book> findAllByName(String name) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book: libraryList) {
            if (book.getName().equals(name)) result.add(book);
        }
        return result;
    }

    public Book findByName(String name) {
        for (Book book: libraryList) {
            if (book.getName().equals(name)) return book;
        }
        return null;
    }

    public ArrayList<Book> findAllByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book: libraryList) {
            if (book.getAuthor().equals(author)) result.add(book);
        }
        return result;
    }

    public Book findByAuthor(String author) {
        for (Book book: libraryList) {
            if (book.getAuthor().equals(author)) return book;
        }
        return null;
    }

    public ArrayList<Book> findAllByGenre(String genre) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book: libraryList) {
            if (book.getGenre().equals(genre)) result.add(book);
        }
        return result;
    }

    public Book findByGenre(String genre) {
        for (Book book: libraryList) {
            if (book.getGenre().equals(genre)) return book;
        }
        return null;
    }


    public ArrayList<Book> findAllByShelf(String shelf) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book: libraryList) {
            if (book.getShelf().equals(shelf)) result.add(book);
        }
        return result;
    }

    public Book findOnShelf(String shelf) {
        for (Book book: libraryList) {
            if (book.getShelf().equals(shelf)) return book;
        }
        return null;
    }


    public ArrayList<Book> findAllByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : libraryList) {
            boolean byName = name == null || book.getName().equals(name);
            boolean byAuthor = author == null || book.getAuthor().equals(author);
            boolean byGenre = genre == null || book.getGenre().equals(genre);
            boolean byShelf = shelf == null || book.getShelf().equals(shelf);
            if (byName && byAuthor && byGenre && byShelf) result.add(book);
        }
        return result;
    }

    public Book findByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        for (Book book : libraryList) {
            boolean byName = name == null || book.getName().equals(name);
            boolean byAuthor = author == null || book.getAuthor().equals(author);
            boolean byGenre = genre == null || book.getGenre().equals(genre);
            boolean byShelf = shelf == null || book.getShelf().equals(shelf);
            if (byName && byAuthor && byGenre && byShelf) return book;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        Library library = (Library) o;
        return libraryList.equals(library.getLibraryList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryList);
    }

    private String generateShelf(Book book) {
        char letter = book.getName().toCharArray()[0];
        int[] booksOnShelfArray = new int[libraryList.size()];
        for (Book value : libraryList) {
            char[] shelfArray = value.getShelf().toCharArray();
            if (shelfArray.length > 0 && shelfArray[0] == letter) {
                StringBuilder numOfShelf = new StringBuilder();
                for (int j = 1; j < shelfArray.length; j++) {
                    numOfShelf.append(shelfArray[j]);
                }
                int num = Integer.parseInt(numOfShelf.toString());
                booksOnShelfArray[num]++;
            }
        }
        for (int i = 0; i < booksOnShelfArray.length; i++) {
            if (booksOnShelfArray[i] < shelfSize) return letter + "" + i;
        }
        return letter + "" + 0;
    }


}