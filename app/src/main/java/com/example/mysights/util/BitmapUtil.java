package com.example.mysights.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapUtil {

    public static byte[] bitmap2byteArray(Bitmap bmp) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            //  bmp.recycle();
            return byteArray;
        } catch (IOException e) {
            Log.e(BitmapUtil.class.getName(),e.getLocalizedMessage());
            return new byte[0];
        }
    }
}
