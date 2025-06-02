package com.example.fufundamentals;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
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
import android.widget.ImageView;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class AssignmentActivity extends AppCompatActivity {

    private EditText assignmentNum, assignmentTopicName, courseTitle, courseCode;
    private EditText teachersName, studentName, studentID, studentBatch, studentSemester, dateField;
    private Spinner teacherDes, teacherDept, studentTerm, studentSec, studentDept;
    private Button resetButton, submitButton, homeButton;
    ImageView nav;
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
        teacherDes = findViewById(R.id.teacherDesignation);
        teacherDept = findViewById(R.id.teachersDept);
        studentTerm = findViewById(R.id.studentTerm);
        studentSec = findViewById(R.id.studentSec);
        studentDept = findViewById(R.id.studentDept);
        resetButton = findViewById(R.id.resetButton);
        submitButton = findViewById(R.id.submitButton);
        homeButton = findViewById(R.id.homeButton);
        nav  = findViewById(R.id.nav4);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //teacherDes.setSelection(5);

        teacherDes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(AssignmentActivity.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
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
        adapter1.setDropDownViewResource(R.layout.dropdown_layout);
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
                new ArrayAdapter<>(AssignmentActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, list2);
        adapter1.setDropDownViewResource(R.layout.dropdown_layout);
        teacherDept.setAdapter(adapter2);


        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Spring");
        list3.add("Autumn");
        list3.add("Fall");

        ArrayAdapter<String> adapter3 =
                new ArrayAdapter<>(AssignmentActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, list3);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentTerm.setAdapter(adapter3);


        ArrayList<String> list4 = new ArrayList<>();
        list4.add("Undergraduate");
        list4.add("Diploma");
        list4.add("Postgraduate");

        ArrayAdapter<String> adapter4 =
                new ArrayAdapter<>(AssignmentActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, list4);
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
                new ArrayAdapter<>(AssignmentActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, list5);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentDept.setAdapter(adapter5);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateField.setText(sdf.format(new Date()));

        dateField.setOnClickListener(v -> showDatePicker());

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                Toast.makeText(AssignmentActivity.this, "Fields Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentNameInput = studentName.getText().toString().trim();
                String studentIdInput = studentID.getText().toString().trim();
                String teacherNameInput = teachersName.getText().toString().trim();
                String semesterInput = studentSemester.getText().toString().trim();
                String batchInput = studentBatch.getText().toString().trim();

                if (studentNameInput.isEmpty() || studentIdInput.isEmpty() ||
                        teacherNameInput.isEmpty() || semesterInput.isEmpty()) {
                    Toast.makeText(AssignmentActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    createPdf();
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AssignmentActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(AssignmentActivity.this, (view, year1, month1, dayOfMonth) ->
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
        String assignmentNo = assignmentNum.getText().toString();

        String assignmentTopic = "Assignment";

        if (assignmentTopicName.getText().toString().length() >= 4) {
            assignmentTopic = assignmentTopicName.getText().toString();
        }

        String titleOfCourse = courseTitle.getText().toString();
        String codeOfCourse = courseCode.getText().toString();
        String nameOfTeacher = teachersName.getText().toString();
        String designationOfTeacher = teacherDes.getSelectedItem().toString();
        String departmentOfTeacher = teacherDept.getSelectedItem().toString();

        String facultyOfTeacher = "";
        if (departmentOfTeacher.equals("CSE") || departmentOfTeacher.equals("EEE") ||
                departmentOfTeacher.equals("CE") || departmentOfTeacher.equals("MATH")) {
            facultyOfTeacher = "Faculty of Science & Engineering";
        } else if (departmentOfTeacher.equals("ENGLISH") || departmentOfTeacher.equals("LAW") ||
                departmentOfTeacher.equals("LIS")) {
            facultyOfTeacher = "Faculty of Arts, Law & Social Science";
        } else if (departmentOfTeacher.equals("BA")) {
            facultyOfTeacher = "Faculty of Business Administration";
        }

        String nameOfStudent = studentName.getText().toString();
        String idOfStudent = studentID.getText().toString();
        String batchOfStudent = studentBatch.getText().toString();
        String termOfStudent = studentTerm.getSelectedItem().toString();
        String semesterOfStudent = studentSemester.getText().toString();
        String sectionOfStudent = studentSec.getSelectedItem().toString();
        String departmentOfStudent = studentDept.getSelectedItem().toString();

        String batchText = " ";

        if (sectionOfStudent == "Undergraduate") {
            batchText = batchOfStudent+ "(UG)";
        } else if (sectionOfStudent == "Diploma" ) {
            batchText = batchOfStudent+ "(DH)";
        } else if (sectionOfStudent == "Postgraduate") {
            batchText = batchOfStudent+ "(PG)";
        }

        String semesterText = " ";
        if (semesterOfStudent.equals("1")) {
            semesterText = "st";
        } else if (semesterOfStudent.equals("2")) {
            semesterText = "nd";
        } else if (semesterOfStudent.equals("3")) {
            semesterText = "rd";
        } else if (semesterOfStudent.equals("4")) {
            semesterText = "th";
        } else if (semesterOfStudent.equals("5")) {
            semesterText = "th";
        } else if (semesterOfStudent.equals("6")) {
            semesterText = "th";
        } else if (semesterOfStudent.equals("7")) {
            semesterText = "th";
        } else if (semesterOfStudent.equals("8")) {
            semesterText = "th";
        } else if (semesterOfStudent.equals("9")) {
            semesterText = "th";
        }


        // Define the PDF document
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        // Page setup
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // A4 size
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        // Add top logo
        Bitmap topLogo = BitmapFactory.decodeResource(getResources(), R.drawable.logotop); // Replace with your top logo resource
        canvas.drawBitmap(topLogo, null, new Rect(150, 40, 450, 110), paint);

        // middle logo
        if (departmentOfTeacher.equalsIgnoreCase("CSE") || departmentOfStudent.equalsIgnoreCase("CSE")) {
            Bitmap watermarkLogo = BitmapFactory.decodeResource(getResources(), R.drawable.logomiddle); // Replace with watermark logo
            paint.setAlpha(20); // Lower transparency
            canvas.drawBitmap(watermarkLogo, null, new Rect(180, 330, 420, 570), paint);
            paint.setAlpha(255);
        }




        paint.setTextSize(18);
        //paint.setColor(Color.parseColor("#FF8C00")); // Orange
        Typeface dataFont = Typeface.create("times new roman", Typeface.NORMAL);
        paint.setTypeface(dataFont);
        canvas.drawText("Assignment No: " + assignmentNo, 230, 160, paint);
        paint.setTextAlign(Paint.Align.CENTER);

        paint.setTextSize(20);
        paint.setColor(Color.parseColor("#FF8C00")); // Orange
        paint.setTextAlign(Paint.Align.CENTER);
        Typeface dataFont2 = Typeface.create("times new roman", Typeface.BOLD);
        paint.setTypeface(dataFont2);
        canvas.drawText(assignmentTopic, pageInfo.getPageWidth() / 2, 200, paint);

        // Course info table
        drawTableWithHeader(canvas, paint, 100, 240, new String[]{"                                                         " +
                        "Course Title", "                                                            " + "Course Code"},
                new String[][]{{titleOfCourse, codeOfCourse}});

        // Prepared for section
        drawTableWithHeader(canvas, paint, 100, 320, new String[]{"                                               "+
                        "Prepared For", "                    "},
                new String[][]{{"Name", nameOfTeacher},
                        {"Designation", designationOfTeacher},
                        {"Department", departmentOfTeacher},
                        {"Faculty", facultyOfTeacher}});

        // Prepared by section
        drawTableWithHeader(canvas, paint, 100, 490, new String[]{"                                                  "+
                        "Prepared By", "                    "},
                new String[][]{{"Name", nameOfStudent},
                        {"ID", idOfStudent},
                        {"Batch", batchText},
                        {"Term", termOfStudent},
                        {"Semester", semesterOfStudent+semesterText},
                        {"Department", departmentOfStudent}});

        // Submission date
        paint.setTextSize(12);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Date of Submission: " + "2024-12-19", 200, 750, paint);

        // Footer
        paint.setTextSize(8);
        paint.setTextAlign(Paint.Align.RIGHT);
        //paint.setColor(Color.parseColor("#1A2BC3"));
        Typeface dataFont3 = Typeface.create("courier", Typeface.BOLD);
        paint.setTypeface(dataFont3);
        canvas.drawText("generated from ", 480, 820, paint);
        paint.setColor(Color.parseColor("#6D97E5"));
        canvas.drawText("FU_Essentials_app", 550, 820, paint);

        // Finish the page
        pdfDocument.finishPage(page);

        // Save the document
        savePdfToDocuments(pdfDocument);
    }

    private void drawTableWithHeader(Canvas canvas, Paint paint, int startX, int startY, String[] headers, String[][] data) {
        int rowHeight = 30;
        int colWidth = 200;

        // Draw header row
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#FEE4C3"));// Transparent orange
        paint.setAlpha(170);
        canvas.drawRect(startX, startY, startX + colWidth * headers.length, startY + rowHeight, paint);

        Typeface dataFont = Typeface.create("monospace", Typeface.BOLD);
        paint.setTypeface(dataFont);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(12);
        for (int i = 0; i < headers.length; i++) {
            canvas.drawText(headers[i], startX + (colWidth * i) + 10, startY + 20, paint);
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(startX, startY, startX + colWidth * headers.length, startY + rowHeight, paint);

        // Draw data rows
        for (int i = 0; i < data.length; i++) {
            int currentY = startY + ((i + 1) * rowHeight);
            for (int j = 0; j < data[i].length; j++) {
                // Draw text inside the cell
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                if (data[i][j].length() > 34) {
                    paint.setTextSize(10);
                } else {
                    paint.setTextSize(11);
                }
                //paint.setTextSize(12);
                Typeface dataFontRows = Typeface.create("cursive", Typeface.NORMAL);
                paint.setTypeface(dataFontRows);
                paint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(data[i][j], startX + (colWidth * j) + 10, currentY + 20, paint);

                // Draw cell border
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                canvas.drawRect(startX + (colWidth * j), currentY, startX + (colWidth * (j + 1)), currentY + rowHeight, paint);
            }
        }
    }

    private void savePdfToDocuments(PdfDocument pdfDocument) {
        String id = studentID.getText().toString();
        String code = courseCode.getText().toString();
        String fileName = id+"_"+code+"_assignment.pdf";
        try {
            ContentValues contentValues = new ContentValues();
            //contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, studentID.getText().toString()+"_"+courseCode.getText().toString()+"_assignment.pdf");
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Cover Pages FU");

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

}