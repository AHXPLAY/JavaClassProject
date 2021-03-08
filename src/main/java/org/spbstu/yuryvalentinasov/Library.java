package org.spbstu.yuryvalentinasov;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Вариант 21. Библиотека
 * <p>Создать класс который хранит информацию о книгах
 * Поля: список кинг {@link Book}, размер полки.
 * Методы:
 * добавить книгу addBook,
 * удалить кингу deleteBook,
 * изменить существующую книгу changeBook,
 * переставить книгу на другую полку changeShelf,
 * поиск книг по их параметрам.</p>
 * <p>
 * Для избежания NPE добавил всем методам добавляющим
 * в список новые объекты (в том числе и конструктору)
 * проверку объектов на null, таким образом, что в
 * список нельзя добавить null.</p>
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

    /**
     * Удаляет книгу из библиотеки по ее экземпляру
     *
     * @param book {@code Book} удаляемая книга
     * @return true если удаление успешно, иначе false
     */
    public boolean deleteBook(Book book) {
        int k = list.indexOf(book);
        if (k != -1) return list.remove(book);
        return false;
    }

    /**
     * Удаляет книгу из библиотеки по ее индексу в списке
     *
     * @param index индекс книги в списке
     * @return true если удаление успешно, иначе false
     */
    public boolean deleteBook(int index) {
        if (isIndexIncorrect(index)) return false;
        return list.remove(index) != null;
    }

    /**
     * Конструктор
     *
     * @param books varargs  {@code Book} которые добавляются в библиотеку при создании
     */
    public Library(Book... books) {
        if (books != null)
            for (Book book : books) {
                this.addBook(book);
            }
    }

    /**
     * Меняет книгу по ее индексу.
     *
     * @param index индекс изменяемой книги
     * @param newBook новая книга
     * @return true в случае успешной замены, иначе false
     */
    public boolean changeBook(int index, Book newBook) {
        if (isIndexIncorrect(index)) return false;
        list.set(index, newBook);
        return true;
    }
    /**
     * Меняет книгу по ее экземпляру.
     *
     * @param changingBook экзмепляр изменяемой книги
     * @param newBook новая книга
     * @return true в случае успешной замены, иначе false
     */
    public boolean changeBook(Book changingBook, Book newBook) {
        if (list.contains(changingBook)) {
            int index = list.indexOf(changingBook);
            return changeBook(index, newBook);
        }
        return false;
    }

    /**
     * Изменяет полку книги по ее индексу
     *
     * @param index индекс книги в списке
     * @param number номер полки на которую она перемещается
     * @return true в случае успешной замены, иначе false
     */
    public boolean changeShelfOrder(int index, int number) {
        if (isIndexIncorrect(index)) return false;
        Book book = list.get(index);
        char[] bookShelf = book.getShelf().toCharArray();
        book.setShelf(bookShelf[0] + "" + number);
        list.set(index, book);
        return true;
    }

    /**
     * Изменяет полку книги по ее экземпляру
     *
     * @param book экзмепляр книги в списке
     * @param number номер полки на которую она перемещается
     * @return true в случае успешной замены, иначе false
     */
    public boolean changeShelfOrder(Book book, int number) {
        if (list.contains(book)) {
            int index = list.indexOf(book);
            return changeShelfOrder(index, number);
        }
        return false;
    }

    /**
     * Ищет все книги с переданным названием
     *
     * @param name название книги
     * @return {@code List<Book>}, со всеми найденными книгами.
     */
    public List<Book> findAllByName(String name) {
        return findAllByParams(name, null, null, null);
    }
    /**
     * Ищет первую книгу по переданному названию.
     *
     * @param name имя книги
     * @return {@code Optional<Book>}, с книгой если она найдена, либо без нее.
     */
    public Optional<Book> findByName(String name) {
        return findByParams(name, null, null, null);
    }

    /**
     * Ищет все книги переданного автора
     *
     * @param author автор книги
     * @return {@code List<Book>}, со всеми найденными книгами.
     */
    public List<Book> findAllByAuthor(String author) {
        return findAllByParams(null, author, null, null);
    }

    /**
     * Ищет первую книгу переданного автора.
     *
     * @param author жанр книги
     * @return {@code Optional<Book>}, с книгой если она найдена, либо без нее.
     */
    public Optional<Book> findByAuthor(String author) {
        return findByParams(null, author, null, null);
    }

    /**
     * Ищет все книги переданного жанра
     *
     * @param genre жанр книги
     * @return {@code List<Book>}, со всеми найденными книгами.
     */
    public List<Book> findAllByGenre(String genre) {
        return findAllByParams(null, null, genre, null);
    }

    /**
     * Ищет первую книгу переданного жанра
     *
     * @param genre жанр книги
     * @return {@code Optional<Book>}, с книгой если она найдена, либо без нее.
     */
    public Optional<Book> findByGenre(String genre) {
        return findByParams(null, null, genre, null);
    }

    /**
     * Ищет все книги на переданной полке
     *
     * @param shelf полка на которой стоит книга
     * @return {@code List<Book>}, со всеми найденными книгами.
     */
    public List<Book> findAllByShelf(String shelf) {
        return findAllByParams(null, null, null, shelf);
    }

    /**
     * Ищет первую книгу на переданной полке
     *
     * @param shelf полка на которой стоит книга
     * @return {@code Optional<Book>}, с книгой если она найдена, либо без нее.
     */
    public Optional<Book> findByShelf(String shelf) {
        return findByParams(null, null, null, shelf);
    }
    /**
     * Ищет все книги подходящие переданным аргументам
     *
     * @param name название книги
     * @param author автор книги
     * @param genre жанр книги
     * @param shelf полка на которой стоит книга
     * @return {@code List<Book>}, со всеми найденными книгами.
     */

    public List<Book> findAllByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        return filterList(name, author, genre, shelf).collect(Collectors.toList());
    }

    /**
     * Ищет первую книгу подходящую переданным аргументам
     *
     * @param name название книги
     * @param author автор книги
     * @param genre жанр книги
     * @param shelf полка на которой стоит книга
     * @return {@code Optional<Book>}, с книгой если она найдена, либо без нее.
     */
    public Optional<Book> findByParams(
            String name,
            String author,
            String genre,
            String shelf) {
        return filterList(name, author, genre, shelf).findFirst();
    }

    /**
     * Фильтрует из списка книги с подходящими параметрами. В случае
     * если параметр равен null, то по нему не происходит фильтрации.
     * @param name название книги
     * @param author автор книги
     * @param genre жанр книги
     * @param shelf полка на которой стоит книга
     * @return {@code Stream<Book>} содержащий книги подходящие по переданным аргументам.
     */
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

    /** generateShelf метод, генерирующий полку.
     * Код полки создается из первого символа в названии
     * книги и порядкового номера на полке. Размер полки задается
     * константой shelfSize.
     **/
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

    /**
     * isSubstringContains метод, являющийся вспомогательным для поиска книг в библиотеке.
     * Проверяет наличие подстроки в строке в не зависимости от реигстра.
     *
     * @param text {@code String} текст, в котором ищется подстрока
     * @param substring {@code String} подстрока, котоаря ищется в тексте.
     * @return значение true если подстрока содержится в строке либо если значение substring равно null, иначе false
     **/
    private boolean isSubstringContains(String text, String substring) {
        if (substring == null) return true;
        return text.lastIndexOf(substring) != -1 ||
                text.toLowerCase().lastIndexOf(substring) != -1 ||
                text.toUpperCase().lastIndexOf(substring) != -1;
    }

    /**
     * Проверяет входит ли индекс в размеры списка
     * @param index индекс проверяемого элемента
     * @return значение true если подстрока содержится в строке, иначе false
     */
    private boolean isIndexIncorrect(int index) {
        return index >= list.size() || index < 0;
    }


}