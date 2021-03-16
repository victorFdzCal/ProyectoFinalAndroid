package com.example.proyectofinal.utilidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.Blob;
import java.sql.SQLException;

public class ImagenesBlobBitmap {
    public static final int ancho = 100;
    public static final int alto = 100;

    //Blob a Bitmap
    public static Bitmap blobToBitmap(Blob b, int width, int height){
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width,height,config);
        try {
            int blobLenght = (int) b.length();
            byte[] blobAsBytes = b.getBytes(1,blobLenght);
            bitmap = BitmapFactory.decodeByteArray(blobAsBytes, 0,blobAsBytes.length);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bitmap;
    }

    //Blob a byte[]
    public static byte[] blobToByte(Blob b){
        int blobLenght = 0;
        byte[] blobAsBytes = new byte[0];
        try {
            blobLenght = (int) b.length();
            blobAsBytes = b.getBytes(1,blobLenght);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blobAsBytes;
    }

    //byte[] a bitmap
    public static Bitmap byteToBitmap(byte[] b){
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(200,200,config);
        try{
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
        }catch (Exception e) {

        }
        return bitmap;
    }
}
