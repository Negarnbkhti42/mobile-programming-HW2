package com.example.bookshelf.Google;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private final List<String> authors = new ArrayList<>();
    private String title;
    private Integer price;
    private Double rate;

    private String id;

    private URL imageThumbnail;

    public URL getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(URL imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
