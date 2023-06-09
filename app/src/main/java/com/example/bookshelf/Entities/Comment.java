package com.example.bookshelf.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Comment implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int commentId;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "rate")
    private Double rate;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
