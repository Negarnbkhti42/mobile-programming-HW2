package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookshelf.Google.Book;
import com.example.bookshelf.Google.BookFilter;
import com.example.bookshelf.Google.GoogleFacade;
import com.example.bookshelf.Google.GoogleFacadeImpl;
import com.example.bookshelf.Google.OpenLibraryImpl;
import com.example.bookshelf.adaptors.BookListAdaptor;

import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    GoogleFacade googleFacade = OpenLibraryImpl.getINSTANCE();
    RecyclerView bookRecyclerView;
    BookListAdaptor bookAdaptor;
    RecyclerView.LayoutManager bookLayoutManager;

    EditText authorEditText;
    EditText titleEditText;
    EditText publisherEditText;
    Button searchButton;

    BookFilter bookFilter = new BookFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        authorEditText = findViewById(R.id.author_edit_text);
        titleEditText = findViewById(R.id.title_edit_text);
        publisherEditText =  findViewById(R.id.publisher_edit_text);
        searchButton = findViewById(R.id.search_button);

        bookRecyclerView =  findViewById(R.id.book_recycler_view);

        bookAdaptor = new BookListAdaptor();
        bookRecyclerView.setAdapter(bookAdaptor);

        bookLayoutManager = new LinearLayoutManager(this);
        bookRecyclerView.setLayoutManager(bookLayoutManager);

        searchButton.setOnClickListener(view -> {
            bookFilter.setAuthor(authorEditText.getText().toString());
            bookFilter.setTitle(titleEditText.getText().toString());
            bookFilter.setPublisher(publisherEditText.getText().toString());

            new Thread(() -> {
                List<Book> books = googleFacade.searchBook(bookFilter);
                System.out.println(books);
                runOnUiThread(() -> bookAdaptor.setBookList(books));
            }).start();
        });
    }
}