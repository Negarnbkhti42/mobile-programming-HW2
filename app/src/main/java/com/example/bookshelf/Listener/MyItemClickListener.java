package com.example.bookshelf.Listener;


import android.app.Activity;
import android.content.Intent;

import com.example.bookshelf.BookInfoActivity;
import com.example.bookshelf.Google.Book;

public class MyItemClickListener{
    public void onItemClick(Book book, Activity activity) {
        Intent intent = new Intent(activity, BookInfoActivity.class);
        intent.putExtra("book", book);
        activity.startActivity(intent);
    }
}
