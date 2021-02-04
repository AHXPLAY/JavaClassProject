import static org.junit.jupiter.api.Assertions.*;

import com.spbstu.yuryvalentinasov.Book;
import com.spbstu.yuryvalentinasov.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class LibraryTest {
    Library lib = new Library();
    @BeforeEach
    void setUp() {
        lib = new Library(
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

    }

    @Test
    void addBook() {
        Book b1 = new Book("Мастер и маргарита", "Михаил Афанасьевич Булгаков", "Роман");
        lib.deleteBook(new Book("Мертвые души", "Николай Васильевич Гоголь", "Поэма"));
        lib.addBook(b1);
        Book addedBook = lib.getLibraryList().get(lib.getLibraryList().size() - 1);
        assertEquals(b1, addedBook);
        assertEquals("М0", addedBook.getShelf());



    }

    @Test
    void deleteBook() {
        Book b1 = new Book("Руслан и Людмила", "Алекасандр Сергеевич Пушкин", "Поэма");
        assertTrue(lib.deleteBook(b1));

        Book b2 = new Book("Двадцать тысяч лье под водой", "Жюль Верн", "Фантастика");
        assertEquals(b2, lib.deleteBook(0));
    }
    @Test
    void changeBook() {
        Book b1 = new Book("Зов Ктулху", "Говард Филлипс Лавкрафт", "Ужасы");
        lib.changeBook(0, b1);
        assertEquals(b1, lib.getLibraryList().get(0));

        Book b2 = new Book("Чистый код. Создание, анализ и рефакторинг",
                "Робер Мартин",
                "Научно-техническая литература");
        lib.changeBook(new Book("Хоббит", "Джон Рональд Руэл Толкиен", "Фэнтези"), b2);

        assertEquals(b2, lib.getLibraryList().get(2));
    }
    
    @Test
    void changeShelf() {
        lib.changeShelf(0, 2);
        assertEquals("Р2", lib.getLibraryList().get(0).getShelf());

        lib.changeShelf(new Book("Хоббит", "Джон Рональд Руэл Толкиен", "Фэнтези"), 5);
        assertEquals("Х5", lib.getLibraryList().get(2).getShelf());
    }
    
    @Test
    void findbyName() {
        
    }

}