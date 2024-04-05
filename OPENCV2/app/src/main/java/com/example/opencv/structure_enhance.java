package com.example.opencv;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.android.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class structure_enhance {

    public static Bitmap strenhance(Bitmap inputBitmap) {
        try {
            // Convert Bitmap to Mat
            Mat inputMat = new Mat();
            Utils.bitmapToMat(inputBitmap, inputMat);

            // Convert image to grayscale
            Mat grayMat = new Mat();
            Imgproc.cvtColor(inputMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            // Define kernel for morphological operations
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));

            // Perform dilation
            Mat dilatedMat = new Mat();
            Imgproc.dilate(grayMat, dilatedMat, kernel);

            // Perform erosion
            Mat erodedMat = new Mat();
            Imgproc.erode(grayMat, erodedMat, kernel);

            // Convert Mat to Bitmap for displaying
            Bitmap dilatedBitmap = Bitmap.createBitmap(dilatedMat.cols(), dilatedMat.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(dilatedMat, dilatedBitmap);

            Bitmap erodedBitmap = Bitmap.createBitmap(erodedMat.cols(), erodedMat.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(erodedMat, erodedBitmap);

            // Create a new Bitmap containing just the dilated and eroded images side by side
            int width = dilatedBitmap.getWidth() + erodedBitmap.getWidth();
            int height = Math.max(dilatedBitmap.getHeight(), erodedBitmap.getHeight());
            Bitmap comparisonBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(comparisonBitmap);
            canvas.drawBitmap(dilatedBitmap, 0f, 0f, null);
            canvas.drawBitmap(erodedBitmap, dilatedBitmap.getWidth(), 0f, null);

            return comparisonBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
