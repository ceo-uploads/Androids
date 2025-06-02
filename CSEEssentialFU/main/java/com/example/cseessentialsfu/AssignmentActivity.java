package com.example.cseessentialsfu;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AssignmentActivity extends AppCompatActivity {

    private EditText assignmentNum, assignmentTopicName, courseTitle, courseCode;
    private EditText teachersName, studentName, studentID, studentBatch, studentSemester, dateField;
    private Spinner teacherDes, teacherDept, studentTerm, studentSec, studentDept;
    private Button resetButton, submitButton;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assignment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        assignmentNum = findViewById(R.id.assignmentNum);
        assignmentTopicName = findViewById(R.id.assignmentTopicName);
        courseTitle = findViewById(R.id.courseTitle);
        courseCode = findViewById(R.id.courseCode);
        teachersName = findViewById(R.id.teachersName);
        studentName = findViewById(R.id.studentName);
        studentID = findViewById(R.id.studentID);
        studentBatch = findViewById(R.id.studentBatch);
        studentSemester = findViewById(R.id.studentSemester);
        dateField = findViewById(R.id.date);
        teacherDes = findViewById(R.id.techerDes);
        teacherDept = findViewById(R.id.teachersDept);
        studentTerm = findViewById(R.id.studentTerm);
        studentSec = findViewById(R.id.studentSec);
        studentDept = findViewById(R.id.studentDept);
        resetButton = findViewById(R.id.resetButton);
        submitButton = findViewById(R.id.submitButton);


        teacherDes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(AssignmentActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AssignmentActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });

        teacherDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(AssignmentActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AssignmentActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });

        studentTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(AssignmentActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AssignmentActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });

        studentSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(AssignmentActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AssignmentActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });

        studentDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(AssignmentActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AssignmentActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayList<String> list1 = new ArrayList<>();

        list1.add("Professor");
        list1.add("Associate Professor");
        list1.add("Assistant Professor");
        list1.add("Lecturer");
        list1.add("Lab Technical Officer");

        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(AssignmentActivity.this, android.R.layout.simple_spinner_item, list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacherDes.setAdapter(adapter1);

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("CSE");
        list2.add("EEE");
        list2.add("CE");
        list2.add("MATH");
        list2.add("ENGLISH");
        list2.add("LAW");
        list2.add("LIS");
        list2.add("BA");

        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(AssignmentActivity.this, android.R.layout.simple_spinner_item, list2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacherDept.setAdapter(adapter2);


        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Spring");
        list3.add("Autumn");
        list3.add("Fall");

        ArrayAdapter<String> adapter3 =
                new ArrayAdapter<>(AssignmentActivity.this, android.R.layout.simple_spinner_item, list3);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentTerm.setAdapter(adapter3);


        ArrayList<String> list4 = new ArrayList<>();
        list4.add("Undergraduate");
        list4.add("Diploma");
        list4.add("Postgraduate");

        ArrayAdapter<String> adapter4 =
                new ArrayAdapter<>(AssignmentActivity.this, android.R.layout.simple_spinner_item, list4);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSec.setAdapter(adapter4);


        ArrayList<String> list5 = new ArrayList<>();
        list5.add("CSE");
        list5.add("EEE");
        list5.add("CE");
        list5.add("MATH");
        list5.add("ENGLISH");
        list5.add("LAW");
        list5.add("LIS");
        list5.add("BA");

        ArrayAdapter<String> adapter5 =
                new ArrayAdapter<>(AssignmentActivity.this, android.R.layout.simple_spinner_item, list5);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentDept.setAdapter(adapter5);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateField.setText(sdf.format(new Date()));

        dateField.setOnClickListener(v -> showDatePicker());

        resetButton.setOnClickListener(v -> resetFields());

       submitButton.setOnClickListener(v -> createPdf());

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) ->
                dateField.setText(year1 + "-" + (month1 + 1) + "-" + dayOfMonth), year, month, day).show();
    }

    private void resetFields() {
        assignmentNum.setText("");
        assignmentTopicName.setText("");
        courseTitle.setText("");
        courseCode.setText("");
        teachersName.setText("");
        studentName.setText("");
        studentID.setText("");
        studentBatch.setText("");
        studentSemester.setText("");
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void createPdf() {
        // Define the PDF document
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        // Page setup
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // A4 size
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        // Add university logo
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.page); // Replace with your logo resource
        canvas.drawBitmap(logo, null, new Rect(200, 50, 400, 150), paint);

        // Title
        paint.setTextSize(16);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("FENI UNIVERSITY", pageInfo.getPageWidth() / 2, 180, paint);
        paint.setTextSize(12);
        canvas.drawText("Center for Learning and Development", pageInfo.getPageWidth() / 2, 200, paint);

        // Assignment title
        paint.setTextSize(16);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Assignment No: 01", pageInfo.getPageWidth() / 2, 240, paint);

        paint.setTextSize(14);
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        canvas.drawText("Assignment", pageInfo.getPageWidth() / 2, 260, paint);

        paint.setColor(getResources().getColor(android.R.color.black));
        paint.setTextAlign(Paint.Align.LEFT);

        // Course info table
        drawTable(canvas, paint, 100, 280, new String[][]{
                {"Course Title", "Digital Logic Design Lab"},
                {"Course Code", "CHM-211"}
        });

        // Prepared for
        paint.setTextSize(12);
        canvas.drawText("Prepared for", 100, 370, paint);
        drawTable(canvas, paint, 100, 390, new String[][]{
                {"Name", "MD Shoriful Islam"},
                {"Designation", "Professor"},
                {"Department", "Computer Science and Engineering"},
                {"Faculty", "Faculty of Science and Engineering"}
        });

        // Prepared by
        canvas.drawText("Prepared by", 100, 510, paint);
        drawTable(canvas, paint, 100, 530, new String[][]{
                {"Name", "MD Rayhan"},
                {"ID", "232031030"},
                {"Batch", "31st (UG)"},
                {"Term", "Autumn"},
                {"Semester", "3rd"},
                {"Department", "Computer Science and Engineering"}
        });

        // Submission date
        paint.setTextSize(12);
        canvas.drawText("Date of Submission: 2024-12-16", 100, 680, paint);

        // Footer
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(10);
        canvas.drawText("generated from csefu2012.web.app", pageInfo.getPageWidth() - 20, 800, paint);

        // Finish the page
        pdfDocument.finishPage(page);

        // Save the document
        savePdfToDocuments(pdfDocument);
    }

    private void drawTable(Canvas canvas, Paint paint, int startX, int startY, String[][] data) {
        int rowHeight = 25;
        int colWidth = 200;

        for (int i = 0; i < data.length; i++) {
            int currentY = startY + (i * rowHeight);

            // Draw column labels
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(data[i][0], startX, currentY + 15, paint);

            // Draw column values
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawText(data[i][1], startX + colWidth, currentY + 15, paint);

            // Draw row line
            canvas.drawLine(startX, currentY, startX + (colWidth * 2), currentY, paint);
        }
    }

    private void savePdfToDocuments(PdfDocument pdfDocument) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Assignment.pdf");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Assignments");

            Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), contentValues);

            if (uri != null) {
                try (OutputStream outputStream = getContentResolver().openOutputStream(uri)) {
                    pdfDocument.writeTo(outputStream);
                    Toast.makeText(this, "PDF saved successfully!", Toast.LENGTH_SHORT).show();

                    // Open PDF
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save PDF", Toast.LENGTH_SHORT).show();
        } finally {
            pdfDocument.close();
        }
    }

}