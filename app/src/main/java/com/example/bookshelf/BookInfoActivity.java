package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class BookInfoActivity extends AppCompatActivity {

    private boolean bookFavorited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        Button buyBookButton = (Button) findViewById(R.id.buy_book_button);
        ImageButton favoriteButton = (ImageButton) findViewById(R.id.favorite_button);
        buyBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookInfoActivity.this, PurchaseActivity.class));
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookFavorited) {
                    favoriteButton.setImageResource(R.drawable.round_favorite_border_24);
                    favoriteButton.setColorFilter(getResources().getColor(R.color.grey_darker));
                    bookFavorited = false;
                } else {
                    favoriteButton.setImageResource(R.drawable.round_favorite_24);
                    favoriteButton.setColorFilter(getResources().getColor(R.color.red));
                    bookFavorited = true;
                }
            }
        });
    }
}