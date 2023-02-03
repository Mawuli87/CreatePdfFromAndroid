package com.messieyawo.createpdffromuserinput;

import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
EditText editTextName,editTextAge,editTextNumber,editTextLocation;
Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextLocation = findViewById(R.id.editTextLocation);
        submitButton = findViewById(R.id.btnSubmit);

        ActivityCompat.requestPermissions(this,new String[]{
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

         try {
             createPDF();
         }catch (FileNotFoundException e){
             e.printStackTrace();
         }


    }

    private void createPDF() throws FileNotFoundException{
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath,"myPDF.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);

       PdfDocument pdfDocument = new PdfDocument(writer);
       Document document = new Document(pdfDocument);
        Paragraph paragraph = new Paragraph("Hello Mawuli");
        Text text1 = new Text("Bold ").setBold();
        Text text2 = new Text("Italic ").setItalic();
        Text text3 =  new Text("Underline ").setUnderline();

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(text1)
                        .add(text2)
                        .add(text3);


        document.add(paragraph);
        document.add(paragraph1);
        document.close();
        Toast.makeText(this,"Pdf Created",Toast.LENGTH_LONG).show();

    }

}