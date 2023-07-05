package com.example.bookshelf.Google;

import lombok.Data;

@Data
public class BookFilter {
    private String author;
    private String title;
    private String publisher;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.isEmpty() ? null : author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.isEmpty() ? null : title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher.isEmpty() ? null : publisher;
    }
}
