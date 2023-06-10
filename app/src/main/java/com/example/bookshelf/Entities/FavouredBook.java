package com.example.bookshelf.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class FavouredBook implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    @ColumnInfo(name = "book_id")
    private Integer bookId;

    @NonNull
    @ColumnInfo(name = "username")
    private String username;
}
