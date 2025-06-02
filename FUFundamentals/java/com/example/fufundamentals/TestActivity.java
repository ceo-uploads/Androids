package com.example.fufundamentals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class TestActivity extends AppCompatActivity {
    private TextView batteryLevelTextView;
    private TextView networkStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);

        // Handle edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        batteryLevelTextView = findViewById(R.id.batteryLevelTextView);
        networkStatusTextView = findViewById(R.id.networkStatusTextView);

        // Register the BroadcastReceiver for battery level updates
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        // Check network status when the activity is created
        checkNetworkStatus();

    }

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryPercentage = (int) ((level / (float) scale) * 100);
            batteryLevelTextView.setText("Battery Level: " + batteryPercentage + "%");
        }
    };

    // Method to check network status
    private void checkNetworkStatus() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                networkStatusTextView.setText("Network Status: Connected (Wi-Fi)");
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                networkStatusTextView.setText("Network Status: Connected (Mobile Data)");
            }
        } else {
            networkStatusTextView.setText("Network Status: Not Connected");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the battery receiver when the activity is destroyed
        unregisterReceiver(batteryReceiver);
    }

}
