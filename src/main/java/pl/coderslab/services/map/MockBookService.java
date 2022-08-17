package pl.coderslab.services.map;
import org.springframework.context.annotation.Primary;
import pl.coderslab.models.Book;
import org.springframework.stereotype.Service;
import pl.coderslab.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
//@Primary
public class MockBookService implements BookService {

    private List<Book> books;

    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
        System.out.println("Initial books list loaded...");
    }

    private static Long nextId = 4L;


    @Override
    public List<Book> getBooksList() {
        return getBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return books.stream().filter(book -> Objects.equals(book.getId(), id)).findFirst().get();
    }

    @Override
    public void updateBookById(Book book) {
        int bookPosition =books.indexOf(books.stream().filter(b-> Objects.equals(b.getId(), book.getId())).findFirst().get());
        books.set(bookPosition, book);
    }

    @Override
    public void deleteBookById(Long id) {
        int bookPosition =books.indexOf(books.stream().filter(b-> Objects.equals(b.getId(), id)).findFirst().get());
        books.remove(bookPosition);
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
        nextId=nextId+1L;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static Long getNextId() {
        return nextId;
    }

    public static void setNextId(Long nextId) {
        MockBookService.nextId = nextId;
    }
}
