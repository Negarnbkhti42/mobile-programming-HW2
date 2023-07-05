package com.example.bookshelf.Google;

public enum APIParameter {
    AUTHOR("inauthor:"),
    PUBLISHER("inpublisher:"),
    TITLE("intitle:");

    private String value;
    APIParameter(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
