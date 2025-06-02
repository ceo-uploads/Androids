package com.example.fufundamentals;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    private final Context context;
    private final List<File> pdfFiles;

    public PdfAdapter(Context context, List<File> pdfFiles) {
        this.context = context;
        this.pdfFiles = pdfFiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pdf, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File pdfFile = pdfFiles.get(position);
        holder.pdfName.setText(pdfFile.getName());

        // Open the PDF file on item click
        holder.itemView.setOnClickListener(v -> openPdf(pdfFile));

        // Show options (view, share, delete) on long press
        holder.itemView.setOnLongClickListener(v -> {
            showOptions(pdfFile);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return pdfFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pdfName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //pdfName = itemView.findViewById(R.id.pdfName);
        }
    }

    private void openPdf(File pdfFile) {
        Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", pdfFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "No application found to open PDF files.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showOptions(File pdfFile) {
        CharSequence[] options = {"View", "Share", "Delete"};
        new android.app.AlertDialog.Builder(context)
                .setTitle("Choose an option")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            openPdf(pdfFile);
                            break;
                        case 1:
                            sharePdf(pdfFile);
                            break;
                        case 2:
                            deletePdf(pdfFile);
                            break;
                    }
                }).show();
    }

    private void sharePdf(File pdfFile) {
        Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", pdfFile);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Share PDF"));
    }

    private void deletePdf(File pdfFile) {
        if (pdfFile.delete()) {
            Toast.makeText(context, "File Deleted", Toast.LENGTH_SHORT).show();
            pdfFiles.remove(pdfFile);
            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }
    }
}
