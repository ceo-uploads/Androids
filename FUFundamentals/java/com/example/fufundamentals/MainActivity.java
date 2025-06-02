package com.example.fufundamentals;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    ImageView dashboard,cpg,downloads,others;
    private boolean doubleBackToExitPressedOnce = false;
    TextView textTime, dayText, dateText, greetingText;
    private Handler handler;
    private Runnable updateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        setContentView(R.layout.activity_main);

        dashboard = findViewById(R.id.dashboard);
        cpg = findViewById(R.id.cpg);
        downloads = findViewById(R.id.downloads);
        others = findViewById(R.id.others);
        textTime = findViewById(R.id.textTime);
        dayText = findViewById(R.id.dayText);
        dateText = findViewById(R.id.dateText);
        greetingText = findViewById(R.id.greetingText);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                NetworkUtil.canLoadFromServerAsync(new NetworkUtil.NetworkCallback() {
                    @Override
                    public void onResult(boolean canLoadData) {
                        if (canLoadData) {
                            startActivity(new Intent(MainActivity.this, ActivityIEMS.class));
                            //finish();
                        } else {
                            startActivity(new Intent(MainActivity.this, OfflineActivity.class));
                            Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        cpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivitySelector.class));
                //finish();
            }
        });

        downloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_list.class));
                Toast.makeText(MainActivity.this, "Press & Hold to Share and Delete", Toast.LENGTH_SHORT).show();
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });



        /*btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkUtil.canLoadFromServerAsync(new NetworkUtil.NetworkCallback() {
                    @Override
                    public void onResult(boolean canLoadData) {
                        if (canLoadData) {
                            startActivity(new Intent(MainActivity.this, activity_list.class));
                        } else {
                            startActivity(new Intent(MainActivity.this, OfflineActivity.class));
                            Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/

        handler = new Handler();
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                // Get the current time, date, and day
                String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault())
                        .format(new Date());
                String currentDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        .format(new Date());
                String currentDay = new SimpleDateFormat("EEEE", Locale.getDefault())
                        .format(new Date());

                // Set the values to the TextViews
                textTime.setText(currentTime);
                dateText.setText(currentDate);
                dayText.setText(currentDay);

                // Schedule the next update after 1 second
                handler.postDelayed(this, 1000);
            }
        };

        // Start the Runnable
        handler.post(updateRunnable);

        greetingText.setText(getGreetingMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove callbacks when the activity is destroyed to avoid memory leaks
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }
    }

    private String getGreetingMessage() {
        // Get the current hour of the day
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Determine the greeting based on the hour
        if (hour >= 5 && hour < 12) {
            return "Good Morning!";
        } else if (hour >= 12 && hour < 15) {
            return "Good Noon!";
        } else if (hour >= 15 && hour < 18) {
            return "Good Afternoon!";
        } else if (hour >= 18 && hour < 21) {
            return "Good Evening!";
        } else {
            return "Good Night!";
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        // Reset the flag after 2 seconds
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

}
