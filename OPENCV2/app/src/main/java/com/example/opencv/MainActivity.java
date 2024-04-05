package com.example.opencv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.OpenCVLoader;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    static {
        // Load OpenCV library
        OpenCVLoader.initDebug();
    }

    Button btngall, but_denoise,but_strtenhance,but_spek,but_con,but_kmean;
    ImageView imageView;

    TextView textView;
    Bitmap selectedBitmap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btngall = findViewById(R.id.btngall);
        but_denoise = findViewById(R.id.but_denoise);
        but_strtenhance=findViewById(R.id.but_strtenhance);
        imageView = findViewById(R.id.imageView);
        but_spek=findViewById(R.id.but_spek);
        but_con = findViewById(R.id.but_con);
        textView=findViewById(R.id.textView);


        btngall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to select an image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });

        but_denoise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to select an image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 101); // Using a different requestCode to differentiate
            }
        });

        but_strtenhance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 102);
            }
        });


        but_spek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 103);
            }
        });

        but_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 104);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 100) {
                // Get the selected image from the gallery
                Uri imgUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imgUri);
                    selectedBitmap = BitmapFactory.decodeStream(inputStream);
                    // Perform denoising on the selected image
                    Bitmap denoisedBitmap = ImageUtils.enhanceEdges(selectedBitmap);
                    // Display the denoised image
                    if (denoisedBitmap != null) {
                        imageView.setImageBitmap(denoisedBitmap);
                        textView.setText("Edge Enhancement");

                    } else {
                        // Handle case where denoising failed
                        // You may want to display an error message or take appropriate action
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 101) {
                // Get the selected image from the gallery
                Uri imgUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imgUri);
                    selectedBitmap = BitmapFactory.decodeStream(inputStream);
                    // Perform denoising on the selected image
                    Bitmap denoisedBitmap = denoise.denoiseImage(selectedBitmap);
                    // Display the denoised image
                    if (denoisedBitmap != null) {
                        imageView.setImageBitmap(denoisedBitmap);
                        textView.setText("Denoise");
                    } else {
                        // Handle case where denoising failed
                        // You may want to display an error message or take appropriate action
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (requestCode == 102) {
                // Get the selected image from the gallery
                Uri imgUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imgUri);
                    selectedBitmap = BitmapFactory.decodeStream(inputStream);
                    // Perform denoising on the selected image
                    Bitmap denoisedBitmap = structure_enhance.strenhance(selectedBitmap);
                    // Display the denoised image
                    if (denoisedBitmap != null) {
                        imageView.setImageBitmap(denoisedBitmap);
                        textView.setText("Erosion and Dilation");
                    } else {
                        // Handle case where denoising failed
                        // You may want to display an error message or take appropriate action
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            else if (requestCode == 103) {
                // Get the selected image from the gallery
                Uri imgUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imgUri);
                    selectedBitmap = BitmapFactory.decodeStream(inputStream);
                    // Perform denoising on the selected image
                    Bitmap denoisedBitmap = speckel_reduction.spek(selectedBitmap);
                    // Display the denoised image
                    if (denoisedBitmap != null) {
                        imageView.setImageBitmap(denoisedBitmap);
                        textView.setText("Median and Gaussian");
                    } else {
                        // Handle case where denoising failed
                        // You may want to display an error message or take appropriate action
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            else if (requestCode == 104) {
                // Get the selected image from the gallery
                Uri imgUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imgUri);
                    selectedBitmap = BitmapFactory.decodeStream(inputStream);
                    // Perform denoising on the selected image
                    Bitmap denoisedBitmap = contrast.cons(selectedBitmap);
                    // Display the denoised image
                    if (denoisedBitmap != null) {
                        imageView.setImageBitmap(denoisedBitmap);
                        textView.setText("Contrast Stretching");

                    } else {
                        // Handle case where denoising failed
                        // You may want to display an error message or take appropriate action
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }




        }
    }
}
