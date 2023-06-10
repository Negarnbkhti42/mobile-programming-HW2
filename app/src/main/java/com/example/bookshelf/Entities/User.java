package com.example.bookshelf.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "nickname")
    private String nickname;

    @ColumnInfo(name = "password")
    private String password;
}
