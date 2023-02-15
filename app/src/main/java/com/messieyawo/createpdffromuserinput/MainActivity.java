package com.messieyawo.createpdffromuserinput;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
                android.Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);



         submitButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 try {
                     createPDF();
                 }catch (FileNotFoundException e){
                     e.printStackTrace();
                 }
             }
         });

    }

    private void createPDF() throws FileNotFoundException{
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String number = editTextNumber.getText().toString();
        String location = editTextLocation.getText().toString();

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath,"myPDF.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
       PdfDocument pdfDocument = new PdfDocument(writer);
       Document document = new Document(pdfDocument);

       float[] columnWith = {140,140,140,140};
        Table table = new Table(columnWith);



        //table 01
        Drawable d1 = getDrawable(R.drawable.mcc);
        Bitmap bitmap = ((BitmapDrawable)d1).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);
        image.setWidth(120f);


        table.addCell(new Cell(3,1).add(image));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell(1,2).add(new Paragraph("Invoice")));
       // table.addCell(new Cell().add(new Paragraph("")));

        //table 02
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("Invoice No: ")));
        table.addCell(new Cell().add(new Paragraph("#254698763")));
        //table.addCell(new Cell().add(new Paragraph("")));


        //table 03
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("Invoice Date: ")));
        table.addCell(new Cell().add(new Paragraph("02-03-2023")));
        //table.addCell(new Cell().add(new Paragraph("")));


        //table 04
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("Account No: ")));
       table.addCell(new Cell().add(new Paragraph("25698741")));

        //table 05
        table.addCell(new Cell().add(new Paragraph("\n")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")));


        //table 06
        table.addCell(new Cell().add(new Paragraph("To")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")));


        //table 07
        table.addCell(new Cell().add(new Paragraph("Mawuli89")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("Payment Methode")));
        table.addCell(new Cell().add(new Paragraph("")));


        //table 08
        table.addCell(new Cell().add(new Paragraph("Bush Canteen Plot567")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell(1,2).add(new Paragraph("PayPal: payment@greenleaf.com")));
        //table.addCell(new Cell().add(new Paragraph("")));

        //table 09
        table.addCell(new Cell().add(new Paragraph("Mawuli")));
        table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell(1,2).add(new Paragraph("Card visa Payment Method welcome")));
       // table.addCell(new Cell().add(new Paragraph("")));



        document.add(table);
        document.add(new Paragraph("\n\n\n\n Authorised Signature \n\n\n").setTextAlignment(TextAlignment.RIGHT));

        document.close();
        Toast.makeText(this,"Pdf Created",Toast.LENGTH_LONG).show();

    }

}