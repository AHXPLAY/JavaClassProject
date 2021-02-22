package org.spbstu.yuryvalentinasov;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * <p>
 * Для избежания NPE добавил всем методам добавляющим
 * в список новые объекты (в том числе и конструктору)
 * проверку объектов на null, таким образом, что в
 * список нельзя добавить null.
 */
public class Library {
    private final List<Book> list = new ArrayList<>();

    private final int shelfSize = 5;

    public List<Book> getList() {
        return list;
    }

    public boolean addBook(Book book) {
        if (book == null || book.getName().length() == 0) return false;
        book.setShelf(generateShelf(book));
        list.add(book);
        return true;
    }

    public boolean deleteBook(Book book) {
        int k = list.indexOf(book);
        if (k != -1) return list.remove(book);
        return false;
    }

    public boolean deleteBook(int index) {
        if (isIndexIncorrect(index)) return false;
        return list.remove(index) != null;
    }

    public Library(Book... books) {
        if (books != null)
            for (Book book : books) {
                this.addBook(book);
            }
    }

    public boolean changeBook(int index, Book newBook) {
        if (isIndexIncorrect(index)) return false;
        list.set(index, newBook);
        return true;
    }

    public boolean changeBook(Book changingBook, Book newBook) {
        if (list.contains(changingBook)) {
            int index = list.indexOf(changingBook);
            return changeBook(index, newBook);
        }
        return false;
    }

    public boolean changeShelfOrder(int index, int number) {
        if (isIndexIncorrect(index)) return false;
        Book book = list.get(index);
        char[] bookShelf = book.getShelf().toCharArray();
        book.setShelf(bookShelf[0] + "" + number);
        list.set(index, book);
        return true;
    }

    public boolean changeShelfOrder(Book book, int number) {
        if (list.contains(book)) {
            int index = list.indexOf(book);
            return changeShelfOrder(index, number);
        }
        return false;
    }

    public List<Book> findAllByName(String name) {
        return findAllByParams(name, null, null, null);
    }

    public Optional<Book> findByName(String name) {
        return findByParams(name, null, null, null);
    }

    public List<Book> findAllByAuthor(String author) {
        return findAllByParams(null, author, null, null);
    }

    public Optional<Book> findByAuthor(String author) {
        return findByParams(null, author, null, null);
    }

    public List<Book> findAllByGenre(String genre) {
        return findAllByParams(null, null, genre, null);
    }

    public Optional<Book> findByGenre(String genre) {
        return findByParams(null, null, genre, null);
    }


    public List<Book> findAllByShelf(String shelf) {
        return findAllByParams(null, null, null, shelf);
    }

    public Optional<Book> findByShelf(String shelf) {
        return findByParams(null, null, null, shelf);
    }


    public List<Book> findAllByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        return filterList(name, author, genre, shelf).collect(Collectors.toList());
    }

    public Optional<Book> findByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        return filterList(name, author, genre, shelf).findFirst();
    }

    private Stream<Book> filterList(String name, String author, String genre, String shelf) {
        return list.stream().filter(book ->
                isSubstringContains(book.getName(), name) &&
                        isSubstringContains(book.getAuthor(), author) &&
                        isSubstringContains(book.getGenre(), genre) &&
                        isSubstringContains(book.getShelf(), shelf));
    }

    @Override
    public String toString() {
        return "Library{" +
                "list=" + list +
                ", shelfSize=" + shelfSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        Library library = (Library) o;
        return Objects.equals(list, library.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, shelfSize);
    }

    private String generateShelf(Book book) {
        char letter = book.getName().toCharArray()[0];
        int[] booksOnShelfArray = new int[list.size()];
        for (Book value : list) {
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


    private boolean isSubstringContains(String text, String substring) {
        if (substring == null) return true;
        return text.lastIndexOf(substring) != -1 ||
                text.toLowerCase().lastIndexOf(substring) != -1 ||
                text.toUpperCase().lastIndexOf(substring) != -1;
    }

    private boolean isIndexIncorrect(int index) {
        return index >= list.size() || index < 0;
    }


}