package com.example.opencv;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.android.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class speckel_reduction {

    public static Bitmap spek(Bitmap inputBitmap) {
        try {
            // Convert Bitmap to Mat
            Mat inputMat = new Mat();
            Utils.bitmapToMat(inputBitmap, inputMat);

            // Convert image to grayscale
            Mat grayMat = new Mat();
            Imgproc.cvtColor(inputMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            // Speckle Reduction
            // Median Filtering
            Mat speckleReducedMedian = new Mat();
            Imgproc.medianBlur(grayMat, speckleReducedMedian, 5);

            // Gaussian Smoothing
            Mat speckleReducedGaussian = new Mat();
            Imgproc.GaussianBlur(grayMat, speckleReducedGaussian, new org.opencv.core.Size(5, 5), 0);

            // Convert Mat to Bitmap for displaying
            Bitmap medianBitmap = Bitmap.createBitmap(speckleReducedMedian.cols(), speckleReducedMedian.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(speckleReducedMedian, medianBitmap);

            Bitmap gaussianBitmap = Bitmap.createBitmap(speckleReducedGaussian.cols(), speckleReducedGaussian.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(speckleReducedGaussian, gaussianBitmap);

            // Concatenate median and Gaussian images side by side
            Bitmap comparisonBitmap = Bitmap.createBitmap(medianBitmap.getWidth() * 2, medianBitmap.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(comparisonBitmap);
            canvas.drawBitmap(medianBitmap, 0f, 0f, null);
            canvas.drawBitmap(gaussianBitmap, medianBitmap.getWidth(), 0f, null);

            return comparisonBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
