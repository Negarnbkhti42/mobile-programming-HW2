package com.example.bookshelf.Google;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String author;
    private String title;
    private Integer price;
    private Double rate;

    private String id;

    private String description;

    private String imageThumbnail;

    public Book() {
    }

    protected Book(Parcel in) {
        price = in.readInt();
        id = in.readString();
        title = in.readString();
        author = in.readString();
        imageThumbnail = in.readString();
        description = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        if (price != null) {
            return price;
        }
        return 0;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(imageThumbnail);
        dest.writeString(description);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
