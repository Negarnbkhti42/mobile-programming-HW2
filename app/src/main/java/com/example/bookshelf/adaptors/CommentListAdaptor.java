package com.example.bookshelf.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Entities.Comment;
import com.example.bookshelf.R;
import com.example.bookshelf.viewholders.CommentViewHolder;

import java.util.List;

public class CommentListAdaptor extends RecyclerView.Adapter<CommentViewHolder>{

    private LayoutInflater commentInflater;
    private List<Comment> commentList;

    public CommentListAdaptor(Context context, List<Comment> commentList) {
        this.commentInflater = LayoutInflater.from(context);
        this.commentList = commentList;
    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = commentInflater.inflate(R.layout.layout_comment_item, parent, false);
        return new CommentViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
