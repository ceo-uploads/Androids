package com.example.fufundamentals;

import static android.os.Environment.DIRECTORY_DOCUMENTS;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class activity_list extends AppCompatActivity {

    ListView listView;
    ImageView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        nav = findViewById(R.id.nav);

        List<File> pdfFiles = getPdfFilesFromFolder();
        ArrayAdapter<File> adapter = new ArrayAdapter<File>(activity_list.this, R.layout.item_pdf, pdfFiles) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_pdf, parent, false);
                }

                ImageView itemImageView = convertView.findViewById(R.id.itemImageView);
                TextView itemTextView = convertView.findViewById(R.id.itemTextView);

                File file = getItem(position);
                if (file != null) {
                    itemTextView.setText(file.getName());
                }
                itemImageView.setImageResource(R.drawable.file); // Ensure file.png exists in res/drawable

                return convertView;
            }
        };

        listView.setAdapter(adapter);

        File folder = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS), "Cover Pages FU");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File pdf = new File(folder, pdfFiles.get(position).getName());

                if (pdf.exists()) {
                    Uri uri = FileProvider.getUriForFile(activity_list.this, activity_list.this.getPackageName() + ".provider", pdf);
                    // Create the main intent for viewing the PDF
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW);
                    viewIntent.setDataAndType(uri, "application/pdf");
                    viewIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    // Wrap the intent in a chooser
                    Intent chooserIntent = Intent.createChooser(viewIntent, "Open PDF File");
                    try {
                        // Start the activity to view the PDF
                        startActivity(viewIntent);
                    } catch (Exception e) {
                        Toast.makeText(activity_list.this, "No application found to open PDF", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity_list.this, "File not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            File pdf = new File(folder, pdfFiles.get(position).getName());
            String[] options = {"View", "Share", "Delete"};

            new AlertDialog.Builder(activity_list.this)
                    .setTitle("Choose an action")
                    .setItems(options, (dialog, which) -> {
                        switch (which) {
                            case 0: // View
                                if (pdf.exists()) {
                                    Uri uri = FileProvider.getUriForFile(activity_list.this, activity_list.this.getPackageName() + ".provider", pdf);
                                    Intent viewIntent = new Intent(Intent.ACTION_VIEW);
                                    viewIntent.setDataAndType(uri, "application/pdf");
                                    viewIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    try {
                                        startActivity(viewIntent);
                                    } catch (Exception e) {
                                        Toast.makeText(activity_list.this, "No application found to open PDF", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(activity_list.this, "File not found", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 1: // Share
                                if (pdf.exists()) {
                                    Uri uri = FileProvider.getUriForFile(activity_list.this, activity_list.this.getPackageName() + ".provider", pdf);
                                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                    shareIntent.setType("application/pdf");
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(Intent.createChooser(shareIntent, "Share PDF File"));
                                } else {
                                    Toast.makeText(activity_list.this, "File not found", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 2: // Delete
                                if (pdf.exists() && pdf.delete()) {
                                    pdfFiles.remove(position);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(activity_list.this, "File deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity_list.this, "Failed to delete file", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    })
                    .show();
            return true;
        });

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private List<File> getPdfFilesFromFolder() {
        File folder = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS), "Cover Pages FU");
        List<File> pdfFiles = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            if (files != null) {
                for (File file : files) {
                    pdfFiles.add(file);
                }
            }
        }
        return pdfFiles;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}