package pl.coderslab.services.map;
import pl.coderslab.models.Book;
import org.springframework.stereotype.Service;
import pl.coderslab.services.GeneralService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockBookService implements GeneralService<Book, Long> {


    private List<Book> books;


    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    private static Long nextId = 4L;


    @Override
    public List<Book> getBooksList() {
        return getBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book updateBookById(Long id) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

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
