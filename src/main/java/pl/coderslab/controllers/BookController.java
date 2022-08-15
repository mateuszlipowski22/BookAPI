package pl.coderslab.controllers;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.models.Book;
import pl.coderslab.services.BookService;
import pl.coderslab.services.map.MockBookService;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService generalService;


    public BookController(BookService generalService) {
        this.generalService = generalService;
    }


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping({"","/"})
    public List<Book> getBooks(){
        return generalService.getBooksList();
    }

    @PostMapping({"","/"})
    public void addBooks(@RequestBody Book book){
        book.setId(MockBookService.getNextId());
        generalService.addBook(book);
    }

    @RequestMapping("/{id}")
    public Book getBook(@PathVariable String id){
        return generalService.getBookById(Long.parseLong(id));
    }

    @PutMapping({"","/"})
    public void updateBook(@RequestBody Book book){
        generalService.updateBookById(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id){
        generalService.deleteBookById(Long.parseLong(id));
    }
}
