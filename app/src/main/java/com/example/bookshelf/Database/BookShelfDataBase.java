package com.example.bookshelf.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookshelf.Dao.CommentDao;
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

    public abstract CommentDao commentDao();
    public abstract FavouredBookDao favouredBookDao();

    public static BookShelfDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookShelfDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    BookShelfDataBase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
