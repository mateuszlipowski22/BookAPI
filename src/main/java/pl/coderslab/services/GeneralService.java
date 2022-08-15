package pl.coderslab.services;

import java.util.List;

public interface GeneralService<T,Long>{


    List<T> getBooksList();
    T getBookById(Long id);
    T updateBookById(Long id);
    void deleteBookById(Long id);
    void addBook(T object);

}
