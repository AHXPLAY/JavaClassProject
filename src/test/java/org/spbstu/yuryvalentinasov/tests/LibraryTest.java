package org.spbstu.yuryvalentinasov.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.spbstu.yuryvalentinasov.Book;
import org.spbstu.yuryvalentinasov.Library;

import java.util.ArrayList;

class LibraryTest {
    Library lib = new Library(
            new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма"),
            new Book("Двадцать тысяч лье под водой", "Жюль Верн", "Фантастика"),
            new Book("Хоббит", "Джон Рональд Руэл Толкиен", "Фэнтези"),
            new Book("Гарри Поттер и философский камень", "Джоан Роулинг", "Фэнтези"),
            new Book("Сказка о рыбаке и рыбке", "Алекасандр Сергеевич Пушкин", "Сказка"),
            new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"),
            new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"),
            new Book("Государь", "Николо Макиавелли", "Трактат"),
            new Book("Мертвые души", "Николай Васильевич Гоголь", "Поэма"),
            new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"),
            new Book("Медный всадник", "Алекасандр Сергеевич Пушкин", "Поэма"),
            new Book("Медный всадник", "Алекасандр Сергеевич Пушкин", "Поэма"),
            new Book("Мартин Иден", "Джек Лондон", "Роман"),
            new Book("Морской волк", "Джек Лондон", "Роман"),
            new Book("Музыка Эриха Цанна", "Говард Филлипс Лавкрафт", "Ужасы"),
            new Book("Марсианские хроники", "Рэй Бредбери", "Фантастика"),
            new Book("Маленький герой", "Федор Михайлович Достоевский", "Рассказ")
    );


    @Test
    void constructorTest() {

        Library test0 = new Library(new Book("Старорусские писания не имеющие ни автора, ни жанра", "", ""));
        assertEquals(1, test0.getList().size());

        Library test1 = new Library((Book[]) null);
        assertEquals(0, test1.getList().size());

        Library test2 = new Library(new Book("", "", ""));
        assertEquals(0, test2.getList().size());
    }


    @Test
    void addBook() {
        Book b1 = new Book("Мастер и маргарита", "Михаил Афанасьевич Булгаков", "Роман");
        lib.deleteBook(new Book("Мертвые души", "Николай Васильевич Гоголь", "Поэма"));
        lib.addBook(b1);
        Book addedBook = lib.getList().get(lib.getList().size() - 1);
        assertEquals(b1, addedBook);
        assertEquals("М0", addedBook.getShelf());

        assertFalse(lib.addBook(null));



    }

    @Test
    void deleteBook() {
        Book b1 = new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма");
        assertTrue(lib.deleteBook(b1));

        //Book b2 = new Book("Двадцать тысяч лье под водой", "Жюль Верн", "Фантастика");
        assertTrue(lib.deleteBook(0));
    }
    @Test
    void changeBook() {
        Book b1 = new Book("Зов Ктулху", "Говард Филлипс Лавкрафт", "Ужасы");
        lib.changeBook(0, b1);
        assertEquals(b1, lib.getList().get(0));

        Book b2 = new Book("Чистый код. Создание, анализ и рефакторинг",
                "Робер Мартин",
                "Научно-техническая литература");
        lib.changeBook(new Book("Хоббит", "Джон Рональд Руэл Толкиен", "Фэнтези"), b2);

        assertEquals(b2, lib.getList().get(2));
    }
    
    @Test
    void changeShelfOrder() {
        lib.changeShelfOrder(0, 2);
        assertEquals("Р2", lib.getList().get(0).getShelf());

        lib.changeShelfOrder(new Book("Хоббит", "Джон Рональд Руэл Толкиен", "Фэнтези"), 5);
        assertEquals("Х5", lib.getList().get(2).getShelf());
    }
    
    @Test
    void findAllByName() {
        ArrayList<Book> result = new ArrayList<>();
        result.add(new Book("Мартин Иден", "Джек Лондон", "Роман"));
        result.add(new Book("Марсианские хроники", "Рэй Бредбери", "Фантастика"));
        assertEquals(result, lib.findAllByName("Мар"));

        assertEquals(new ArrayList<Book>(), lib.findAllByName("Война и мир"));
    }

    @Test
    void findByName() {
        Book b1 = new Book("Мертвые души", "Николай Васильевич Гоголь", "Поэма");

        assertEquals(b1, lib.findByName("Ме").orElse(null));

        assertFalse(lib.findByName("Словарь").isPresent());
    }

    @Test
    void findAllByAuthor() {
        ArrayList<Book> result = new ArrayList<>();
        result.add(new Book("Государь", "Николо Макиавелли", "Трактат"));
        result.add(new Book("Мертвые души", "Николай Васильевич Гоголь", "Поэма"));
        assertEquals(result, lib.findAllByAuthor("Ник"));

        assertEquals(new ArrayList<Book>(), lib.findAllByAuthor("Оксимирон"));
    }

    @Test
    void findByAuthor() {
        Book b1 = new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма");

        assertEquals(b1, lib.findByAuthor("Пушкин").orElse(null));

        assertFalse(lib.findByAuthor("Джордж Мартин").isPresent());
    }

    @Test
    void findAllByGenre() {
        ArrayList<Book> result = new ArrayList<>();
        result.add(new Book("Хоббит", "Джон Рональд Руэл Толкиен", "Фэнтези"));
        result.add(new Book("Гарри Поттер и философский камень", "Джоан Роулинг", "Фэнтези"));
        assertEquals(result, lib.findAllByGenre("Фэнт"));

        assertEquals(new ArrayList<Book>(), lib.findAllByGenre("Роман-эпопея"));
    }

    @Test
    void findByGenre() {
        Book b1 = new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма");

        assertEquals(b1, lib.findByGenre("Дра").orElse(null));

        assertFalse(lib.findByGenre("Трактор").isPresent());
    }

    @Test
    void findAllByShelf() {
        ArrayList<Book> result = new ArrayList<>();
        result.add(new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма"));
        result.add(new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"));
        result.add(new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"));
        result.add(new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"));
        assertEquals(result, lib.findAllByShelf("Р0"));

        assertEquals(new ArrayList<Book>(), lib.findAllByShelf("Р1"));
    }

    @Test
    void findByShelf() {
        Book b1 = new Book("Гарри Поттер и философский камень", "Джоан Роулинг", "Фэнтези");

        assertEquals(b1, lib.findByShelf("Г0").orElse(null));

        assertFalse(lib.findByName("Г1").isPresent());
    }

    @Test
    void findAllByParams() {
        ArrayList<Book> result = new ArrayList<>();
        result.add(new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма"));
        result.add(new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"));
        result.add(new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"));
        result.add(new Book("Ромэо и Джульетта", "Уильям Шекспир", "Драма"));

        assertEquals(result, lib.findAllByParams("Р", "ш", "ма", null));

        assertEquals(new ArrayList<Book>(), lib.findAllByParams("Х", "asdasdf", "aaa", "tt"));
    }

    @Test
    void findByParams() {
        Book b1 = new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма");

        assertEquals(b1, lib.findByParams("Р", "ш", "ма", null).orElse(null));

        assertFalse(lib.findByParams("Х", "asdasdf", "aaa", "tt").isPresent());
    }

    @Test
    void testEqualsBook() {
        Book b1 = new Book("Книга", "Автор", "Жанр");
        Book b2 = new Book("Книга", "Автор", "Жанр");
        assertEquals(b2, b1);
        assertEquals(b1, b2);

        Book b3 = new Book(null, null, null);
        Book b4 = new Book(null, null, null);
        assertEquals(b4, b3);
        assertEquals(b3, b4);

        Book b5 = new Book(null, "Автор", "Жанр");
        Book b6 = new Book("Книга", null, "Жанр");
        assertNotEquals(b6, b5);
        assertNotEquals(b5, b6);

    }

    @Test
    void testEqualsLibrary() {
        Library l1 = new Library(
                new Book("Книга", "Автор", "Жанр")
        );
        Library l2 = new Library(
                new Book("Книга", "Автор", "Жанр")
        );

        assertEquals(l1, l2);

        assertNotEquals(l1, null);
    }

    Book testBook = new Book("Book", "Author", "Genre");
    @Test
    void getName() {
        assertEquals("Book", testBook.getName());
    }

    @Test
    void setName() {
        testBook.setName("Not previous name");
        assertEquals("Not previous name", testBook.getName());
    }

    @Test
    void getAuthor() {
        assertEquals("Author", testBook.getAuthor());
    }

    @Test
    void setAuthor() {
        testBook.setAuthor("Not previous author");
        assertEquals("Not previous author", testBook.getAuthor());
    }

    @Test
    void getShelf() {
        assertNull( testBook.getShelf());
    }

    @Test
    void setShelf() {
        testBook.setShelf("Not previous shelf");
        assertEquals("Not previous shelf", testBook.getShelf());
    }

    @Test
    void getGenre() {
        assertEquals("Genre", testBook.getGenre());
    }

    @Test
    void setGenre() {
        testBook.setGenre("Not previous genre");
        assertEquals("Not previous genre", testBook.getGenre());
    }
    @Test
    void bookToString() {
        assertEquals("Book{" +
                "name='" + testBook.getName() + '\'' +
                ", author='" + testBook.getAuthor() + '\'' +
                ", genre='" + testBook.getGenre() + '\'' +
                ", shelf='" + testBook.getShelf() + '\'' +
                '}', testBook.toString());
    }

}