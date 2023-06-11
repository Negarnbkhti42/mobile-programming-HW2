package com.example.bookshelf.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Google.Book;
import com.example.bookshelf.R;
import com.example.bookshelf.viewholders.BookViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookListAdaptor extends RecyclerView.Adapter<BookViewHolder> {

    private List<Book> bookList = new ArrayList<>();
    private Context context;


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_book_item, parent, false);
        return new BookViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book current = bookList.get(position);
        holder.book_title.setText(current.getTitle());

        StringBuilder authors = new StringBuilder();
        for (String author : current.getAuthors()) {
            authors.append(author).append(", ");
        }
        authors.delete(authors.length() - 2, authors.length());

        holder.book_author.setText(String.format(context.getResources().getString(R.string.book_placeholder_author), authors));
        holder.book_rating.setText(String.format(context.getResources().getString(R.string.book_placeholder_rate), current.getRate()));
        holder.book_price.setText(String.format(context.getResources().getString(R.string.book_placeholder_price), current.getPrice()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }
}
