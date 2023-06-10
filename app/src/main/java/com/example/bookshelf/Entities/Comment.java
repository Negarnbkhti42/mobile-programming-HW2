package com.example.bookshelf.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Comment implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "rate")
    private Double rate;

    @ColumnInfo(name = "nickname")
    private String nickname;

    @ColumnInfo(name = "book_id")
    private Integer bookId;
}
