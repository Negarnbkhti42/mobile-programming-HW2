package com.example.bookshelf;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookshelf.Dao.FavouredBookDao;
import com.example.bookshelf.Dao.UserDao;
import com.example.bookshelf.Entities.BoughtBook;
import com.example.bookshelf.Entities.Comment;
import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.Entities.User;

@Database(entities = {User.class, Comment.class, FavouredBook.class, BoughtBook.class}, version = 1)
public abstract class BookShelfDataBase extends RoomDatabase {
    private static volatile BookShelfDataBase INSTANCE;

    public abstract UserDao userDao();
    public abstract FavouredBookDao favouredBookDao();

    public static synchronized BookShelfDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    BookShelfDataBase.class, "user_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }

        }
        return INSTANCE;
    }
}
