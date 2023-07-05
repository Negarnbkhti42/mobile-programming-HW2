package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.Google.Book;
import com.example.bookshelf.services.SessionManager;
import com.example.bookshelf.viewmodels.FavouredBookViewModel;

public class BookInfoActivity extends AppCompatActivity {

    private boolean bookFavorite = false;
    private SessionManager session;
    private FavouredBookViewModel favouredBookViewModel;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        session = new SessionManager(getApplicationContext());
        favouredBookViewModel = new ViewModelProvider(this).get(FavouredBookViewModel.class);
        Intent intent = getIntent();
        book = intent.getParcelableExtra("book");
        Button buyBookButton = findViewById(R.id.buy_book_button);
        ImageButton favoriteButton = findViewById(R.id.favorite_button);
        TextView author = findViewById(R.id.author);
        author.setText(book.getAuthor());
        TextView title = findViewById(R.id.title);
        title.setText(book.getTitle());
        TextView description = findViewById(R.id.description);
        description.setText("this book is about " + book.getDescription());
        Spinner rateSpinner = findViewById(R.id.rate_spinner);

        Button submitCommentButton = findViewById(R.id.submit_comment_button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rate_entries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateSpinner.setAdapter(adapter);

        buyBookButton.setOnClickListener(view ->
                startActivity(new Intent(BookInfoActivity.this, PurchaseActivity.class)));

        favoriteButton.setOnClickListener(view -> {
            if (bookFavorite) {
                removeFromFavorites();
                favoriteButton.setImageResource(R.drawable.round_favorite_border_24);
                favoriteButton.setColorFilter(getResources().getColor(R.color.grey_darker));
                bookFavorite = false;
            } else {
                addToFavorites(book.getId());
                favoriteButton.setImageResource(R.drawable.round_favorite_24);
                favoriteButton.setColorFilter(getResources().getColor(R.color.red));
                bookFavorite = true;
            }
        });

        submitCommentButton.setOnClickListener(view -> {
            String rate = rateSpinner.getSelectedItem().toString();
            EditText commentEditText = findViewById(R.id.comment_edit_text);
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
        });
    }

    private void removeFromFavorites() {

    }

    private void addToFavorites(String id) {
        String username = session.getUserId();
        favouredBookViewModel.insert(new FavouredBook(null, id, username));
    }
}