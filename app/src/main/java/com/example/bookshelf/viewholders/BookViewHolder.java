package com.example.bookshelf.viewholders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.google.android.material.imageview.ShapeableImageView;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView book_title;
    private TextView book_author;
    private TextView book_rating;
    private TextView book_price;
    private ShapeableImageView book_cover;

    private AdapterView.OnItemClickListener itemClickListener;


    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        book_title = (TextView) itemView.findViewById(R.id.book_title);
        book_author = (TextView) itemView.findViewById(R.id.book_author);
        book_rating = (TextView) itemView.findViewById(R.id.book_rating);
        book_price = (TextView) itemView.findViewById(R.id.book_price);
        book_cover = (ShapeableImageView) itemView.findViewById(R.id.book_cover);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

    }
}
