package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookshelf.adaptors.BookListAdaptor;

public class ExploreActivity extends AppCompatActivity {

    RecyclerView bookRecyclerView;
    BookListAdaptor bookAdaptor;
    RecyclerView.LayoutManager bookLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        bookRecyclerView = (RecyclerView) findViewById(R.id.book_recycler_view);

        bookAdaptor = new BookListAdaptor(this, null);
        bookRecyclerView.setAdapter(bookAdaptor);

        bookLayoutManager = new LinearLayoutManager(this);
        bookRecyclerView.setLayoutManager(bookLayoutManager);
    }
}