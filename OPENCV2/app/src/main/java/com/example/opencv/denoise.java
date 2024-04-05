package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.android.Utils;

import android.graphics.Bitmap;

class denoise {

    public static Bitmap denoiseImage(Bitmap inputBitmap) {
        try {
            // Convert Bitmap to Mat
            Mat inputMat = new Mat();
            Utils.bitmapToMat(inputBitmap, inputMat);

            // Convert image to grayscale
            Mat grayMat = new Mat();
            Imgproc.cvtColor(inputMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            // Apply bilateral filter for denoising
            Mat denoisedMat = new Mat();
            Imgproc.bilateralFilter(grayMat, denoisedMat, 9, 75, 75);

            // Convert Mat to Bitmap for displaying
            Bitmap denoisedBitmap = Bitmap.createBitmap(denoisedMat.cols(), denoisedMat.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(denoisedMat, denoisedBitmap);

            return denoisedBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
