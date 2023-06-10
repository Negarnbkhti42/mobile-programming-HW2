package com.example.bookshelf.Google;

import java.util.List;

public interface GoogleFacade {
    List<Book> searchBook(BookFilter filter);
}
