package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.models.Book;
import pl.coderslab.services.BookService;
import pl.coderslab.services.UserLogger;
import pl.coderslab.services.map.MockBookService;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final UserLogger userLogger;

    @Autowired
    public BookController(BookService generalService, UserLogger userLogger) {
        this.bookService = generalService;
        this.userLogger = userLogger;
    }


    @RequestMapping("/helloBook")
    public Book helloBook() {
        userLogger.log(new Object() {}.getClass().getEnclosingMethod().getName());
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping({"","/"})
    public List<Book> getBooks(@RequestHeader("user-agent") String userAgent){
        userLogger.log(new Object() {}.getClass().getEnclosingMethod().getName() +" " + userAgent);
        return bookService.getBooksList();
    }

    @PostMapping({"","/"})
    public void addBooks(@RequestBody Book book, @RequestHeader("user-agent") String userAgent){
        userLogger.log(new Object() {}.getClass().getEnclosingMethod().getName() +" " + userAgent);
        book.setId(MockBookService.getNextId());
        bookService.addBook(book);
    }

    @RequestMapping("/{id}")
    public Book getBook(@PathVariable String id, @RequestHeader("user-agent") String userAgent){
        userLogger.log(new Object() {}.getClass().getEnclosingMethod().getName() +" id " +id +" " + userAgent);
        return bookService.getBookById(Long.parseLong(id));
    }

    @PutMapping({"","/"})
    public void updateBook(@RequestBody Book book, @RequestHeader("user-agent") String userAgent){
        userLogger.log(new Object() {}.getClass().getEnclosingMethod().getName()+" id "+book.getId().toString()+" " + userAgent);
        bookService.updateBookById(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id, @RequestHeader("user-agent") String userAgent){
        userLogger.log(new Object() {}.getClass().getEnclosingMethod().getName()+" id " + id+" " + userAgent);
        bookService.deleteBookById(Long.parseLong(id));
    }

}
