package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

        Spinner rateSpinner = (Spinner) findViewById(R.id.rate_spinner);

        Button submitCommentButton = (Button) findViewById(R.id.submit_comment_button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rate_entries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateSpinner.setAdapter(adapter);

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

        submitCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rate = rateSpinner.getSelectedItem().toString();
                EditText commentEditText = (EditText) findViewById(R.id.comment_edit_text);
                String comment = commentEditText.getText().toString();

                if (rate.isEmpty()) {
                    Toast.makeText(BookInfoActivity.this, "please select a rating", Toast.LENGTH_SHORT).show();
                    rateSpinner.requestFocus();
                    return;
                }

                if (comment.isEmpty()) {
                    Toast.makeText(BookInfoActivity.this, "please enter a comment", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.comment_edit_text).requestFocus();
                    return;
                }

                Toast.makeText(BookInfoActivity.this, "comment submitted", Toast.LENGTH_SHORT).show();

                rateSpinner.setSelection(-1);
                commentEditText.setText("");
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