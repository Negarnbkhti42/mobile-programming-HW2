package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.services.SessionManager;
import com.example.bookshelf.viewmodels.FavouredBookViewModel;

public class BookInfoActivity extends AppCompatActivity {

    private boolean bookFavorited = false;
    private SessionManager session;
    private FavouredBookViewModel favouredBookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        session = new SessionManager(getApplicationContext());
        favouredBookViewModel = new ViewModelProvider(this).get(FavouredBookViewModel.class);

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
                    removeFromFavorites();
                    favoriteButton.setImageResource(R.drawable.round_favorite_border_24);
                    favoriteButton.setColorFilter(getResources().getColor(R.color.grey_darker));
                    bookFavorited = false;
                } else {
                    addToFavorites();
                    favoriteButton.setImageResource(R.drawable.round_favorite_24);
                    favoriteButton.setColorFilter(getResources().getColor(R.color.red));
                    bookFavorited = true;
                }
            }
        });
    }

    private void removeFromFavorites() {

    }

    private void addToFavorites() {
        String username = session.getUserId();
        favouredBookViewModel.insert(new FavouredBook(null, "1", username));
    }
}