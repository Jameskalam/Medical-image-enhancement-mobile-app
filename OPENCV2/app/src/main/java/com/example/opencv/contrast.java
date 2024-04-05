package com.example.opencv;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class contrast {


    public static Bitmap cons(Bitmap inputBitmap) {
        try {

            // Load the image
            // Convert Bitmap to Mat
            Mat inputMat = new Mat();
            Utils.bitmapToMat(inputBitmap, inputMat);

            // Convert the image to grayscale
            Mat grayMat = new Mat();
            Imgproc.cvtColor(inputMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            // Apply image enhancements
            // Example: Contrast Stretching
            Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(grayMat);
            double min_val = minMaxLocResult.minVal;
            double max_val = minMaxLocResult.maxVal;

            Mat imageEnhancedStretch = new Mat();
            Core.multiply(grayMat, new Scalar(750.0 / (max_val - min_val)), imageEnhancedStretch);
            Core.subtract(imageEnhancedStretch, new Scalar((750.0 * min_val) / (max_val - min_val)), imageEnhancedStretch);

            // Convert Mat to Bitmap
            Bitmap resultBitmap = Bitmap.createBitmap(imageEnhancedStretch.cols(), imageEnhancedStretch.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(imageEnhancedStretch, resultBitmap);

            return resultBitmap;



        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
