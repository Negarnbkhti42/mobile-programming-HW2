package com.example.bookshelf;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Button datePickerButton = findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            Date currentDate = c.getTime();

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    PurchaseActivity.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        Calendar instance = Calendar.getInstance();
                        instance.set(year1, monthOfYear, dayOfMonth);

                        Date selectedDate = instance.getTime();

                        long differenceInMillis = selectedDate.getTime() - currentDate.getTime();
                        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

                        if (differenceInDays >= 1 && differenceInDays <= 7) {
                            // Date is within the range, perform your desired action
                            datePickerButton.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        } else {
                            // Date is outside the range, show a toast
                            Toast.makeText(PurchaseActivity.this, "Please select a date between 1 to 7 days from the current date.", Toast.LENGTH_SHORT).show();
                        }
                    },

                    year, month, day);
            datePickerDialog.show();
        });
    }
}