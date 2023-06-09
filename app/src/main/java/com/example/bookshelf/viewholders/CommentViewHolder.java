package com.example.bookshelf.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.adaptors.CommnetListAdaptor;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    public TextView comment_text;
    public TextView comment_author;
    public TextView comment_rating;

    private CommnetListAdaptor adaptor;

    public CommentViewHolder(@NonNull View itemView, CommnetListAdaptor adaptor) {
        super(itemView);
        this.comment_text = (TextView) itemView.findViewById(R.id.comment_text);
        this.comment_author = (TextView) itemView.findViewById(R.id.comment_author);
        this.comment_rating = (TextView) itemView.findViewById(R.id.comment_rating);

        this.adaptor = adaptor;
    }
}
