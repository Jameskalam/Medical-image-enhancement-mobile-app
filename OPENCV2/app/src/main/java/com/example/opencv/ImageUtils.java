package com.example.opencv;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.graphics.Bitmap;

public class ImageUtils {

    public static Bitmap enhanceEdges(Bitmap inputBitmap) {
        try {
            // Convert Bitmap to Mat
            Mat inputMat = new Mat();
            Utils.bitmapToMat(inputBitmap, inputMat);

            // Convert image to grayscale
            Mat grayMat = new Mat();
            Imgproc.cvtColor(inputMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            // Edge Enhancement
            // Sobel operator
            Mat sobelx = new Mat();
            Mat sobely = new Mat();
            Imgproc.Sobel(grayMat, sobelx, CvType.CV_16S, 1, 0);
            Imgproc.Sobel(grayMat, sobely, CvType.CV_16S, 0, 1);
            Mat edgeEnhancedSobel = new Mat();
            Core.convertScaleAbs(sobelx, sobelx);
            Core.convertScaleAbs(sobely, sobely);
            Core.addWeighted(sobelx, 0.5, sobely, 0.5, 0, edgeEnhancedSobel);

            // Convert Mat to Bitmap
            Bitmap outputBitmap = Bitmap.createBitmap(edgeEnhancedSobel.cols(), edgeEnhancedSobel.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(edgeEnhancedSobel, outputBitmap);

            return outputBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





    






}
