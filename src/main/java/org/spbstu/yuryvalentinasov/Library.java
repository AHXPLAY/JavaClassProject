package org.spbstu.yuryvalentinasov;

import java.util.*;

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
 *
 * Для избежания NPE добавил всем методам добавляющим
 * в список новые объекты (в том числе и конструктору)
 * проверку объектов на null, таким образом, что в
 * список нельзя добавить null.
 */
public class Library extends ArrayList<Book> {

    private final int shelfSize = 5;

    @Override
    public boolean add(Book book) {
        if (book == null) return false;
        book.setShelf(generateShelf(book));
        super.add(book);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Book> c) {
        for (Book book: c) if (book == null) return false;
        return super.addAll(c);
    }

    public boolean deleteBook(Book book) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(book)) {
                return this.remove(book);
            }
        }
        return false;
    }

    public boolean deleteBook(int index) {
        if (isIndexIncorrect(index)) return false;
        return this.remove(index) != null;
    }

    public Library(Book... books) {
        for (Book book : books) {
            if (book == null) continue;
            book.setShelf(generateShelf(book));
            this.add(book);
        }
    }

    public boolean changeBook(int index, Book newBook) {
        if (isIndexIncorrect(index)) return false;
        this.set(index, newBook);
        return true;
    }

    public boolean changeBook(Book changingBook, Book newBook) {
        if (this.contains(changingBook)) {
            int index = this.indexOf(changingBook);
            return changeBook(index, newBook);
        }
        return false;
    }

    public boolean changeShelfOrder(int index, int number) {
        if (isIndexIncorrect(index)) return false;
        Book book = this.get(index);
        char[] bookShelf = book.getShelf().toCharArray();
        book.setShelf(bookShelf[0] + "" + number);
        this.set(index, book);
        return true;
    }

    public boolean changeShelfOrder(Book book, int number) {
        if (this.contains(book)) {
            int index = this.indexOf(book);
            return changeShelfOrder(index, number);
        }
        return false;
    }

    public ArrayList<Book> findAllByName(String name) {
        return findAllByParams(name, null, null, null);
    }

    public Book findByName(String name) {
        return findByParams(name, null, null, null);
    }

    public ArrayList<Book> findAllByAuthor(String author) {
        return findAllByParams(null, author, null, null);
    }

    public Book findByAuthor(String author) {
        return findByParams(null, author, null, null);
    }

    public ArrayList<Book> findAllByGenre(String genre) {
        return findAllByParams(null, null, genre, null);
    }

    public Book findByGenre(String genre) {
        return findByParams(null, null, genre, null);
    }


    public ArrayList<Book> findAllByShelf(String shelf) {
        return findAllByParams(null, null, null, shelf);
    }

    public Book findByShelf(String shelf) {
        return findByParams(null, null, null, shelf);
    }


    public ArrayList<Book> findAllByParams(
            String name,
            String author,
            String genre,
            String shelf) {

        ArrayList<Book> resultList = new ArrayList<>();

        this.stream().filter(book -> isSubstringContains(book.getName(), name) &&
                isSubstringContains(book.getAuthor(), author) &&
                isSubstringContains(book.getGenre(), genre) &&
                isSubstringContains(book.getShelf(), shelf)).forEach(resultList::add);

        return resultList;
    }

    public Book findByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        Optional<Book> optional = this.stream().filter(book ->
                isSubstringContains(book.getName(), name) &&
                        isSubstringContains(book.getAuthor(), author) &&
                        isSubstringContains(book.getGenre(), genre) &&
                        isSubstringContains(book.getShelf(), shelf)).findFirst();

        return optional.orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        Library library = (Library) o;
        return this.equals(library);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toArray());
    }

    private String generateShelf(Book book) {
        char letter = book.getName().toCharArray()[0];
        int[] booksOnShelfArray = new int[this.size()];
        for (Book value : this) {
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
        return index >= this.size() && index <= 0;
    }

}