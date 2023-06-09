package com.example.bookshelf.viewholders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.adaptors.BookListAdaptor;
import com.google.android.material.imageview.ShapeableImageView;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView book_title;
    public TextView book_author;
    public TextView book_rating;
    public TextView book_price;
    public ShapeableImageView book_cover;

    BookListAdaptor adaptor;

    private AdapterView.OnItemClickListener itemClickListener;


    public BookViewHolder(@NonNull View itemView, BookListAdaptor adaptor) {
        super(itemView);
        this.book_title = (TextView) itemView.findViewById(R.id.book_title);
        this.book_author = (TextView) itemView.findViewById(R.id.book_author);
        this.book_rating = (TextView) itemView.findViewById(R.id.book_rating);
        this.book_price = (TextView) itemView.findViewById(R.id.book_price);
        this.book_cover = (ShapeableImageView) itemView.findViewById(R.id.book_cover);

        this.adaptor = adaptor;

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {

    }
}
