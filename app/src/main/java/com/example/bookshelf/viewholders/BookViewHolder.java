package com.example.bookshelf.viewholders;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Google.Book;
import com.example.bookshelf.Listener.MyItemClickListener;
import com.example.bookshelf.R;
import com.example.bookshelf.adaptors.BookListAdaptor;
import com.google.android.material.imageview.ShapeableImageView;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView book_title;
    public TextView book_author;
    public TextView book_rating;
    public TextView book_price;
    public ShapeableImageView book_cover;
    public int i = 0;

    BookListAdaptor adaptor;

    private final MyItemClickListener itemClickListener;


    public BookViewHolder(@NonNull View itemView, BookListAdaptor adaptor, MyItemClickListener itemClickListener) {
        super(itemView);
        this.book_title = itemView.findViewById(R.id.book_title);
        this.book_author = itemView.findViewById(R.id.book_author);
        this.book_rating = itemView.findViewById(R.id.book_rating);
        this.book_price = itemView.findViewById(R.id.book_price);
        this.book_cover = itemView.findViewById(R.id.book_cover);
        this.adaptor = adaptor;
        this.itemClickListener = itemClickListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            Book book = adaptor.getBookList().get(i);
            i++;
            itemClickListener.onItemClick(book, (Activity) view.getContext());
        }
    }
}
