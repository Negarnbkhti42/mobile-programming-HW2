package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Button datePickerButton = (Button) findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                Date currentDate = c.getTime();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PurchaseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Calendar instance = Calendar.getInstance();
                                instance.set(year, monthOfYear, dayOfMonth);

                                Date selectedDate = instance.getTime();

                                long differenceInMillis = selectedDate.getTime() - currentDate.getTime();
                                long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

                                if (differenceInDays >= 1 && differenceInDays <= 7) {
                                    // Date is within the range, perform your desired action
                                    datePickerButton.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                } else {
                                    // Date is outside the range, show a toast
                                    Toast.makeText(PurchaseActivity.this, "Please select a date between 1 to 7 days from the current date.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },

                        year, month, day);
                datePickerDialog.show();
            }
        });
    }
}