package com.intandif.viewtoimageorpdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class ViewToImage {
    Context context;
    ActionListeners listeners;
    String folderName = "DevelopersFolder";
    String fileName = "myFile";
    View view;
    Bitmap bitmap = null;
    String filePath = null;


    public ViewToImage(Context context, View view) {
        this.context = context;
        this.view = view;
        convert();
    }


    public ViewToImage(Context context, View view, ActionListeners listeners) {
        this.context = context;
        this.listeners = listeners;
        this.view = view;
        convert();
    }

    public ViewToImage(Context context, View view, String folderName, String fileName, ActionListeners listeners) {
        this.context = context;
        this.listeners = listeners;
        this.folderName = folderName;
        this.fileName = fileName;
        this.view = view;
        convert();
    }

    public ViewToImage(Context context, View view, String fileName, ActionListeners listeners) {
        this.context = context;
        this.listeners = listeners;
        this.fileName = fileName;
        this.view = view;
        convert();
    }


    private void convert() {
        Bitmap bitmap = getBitmapFromView(view, view.getWidth(), view.getHeight());

        if (fileName.equals("myFile")) {
            saveTheImage(bitmap, null);
        } else {
            saveTheImage(bitmap, fileName);
        }


    }

    private Bitmap getBitmapFromView(View view, int width, int height) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bitmap);
        view.layout(0, 0, view.getLayoutParams().width, view.getLayoutParams().height);
        view.draw(mCanvas);
        return bitmap;
    }


    private void saveTheImage(Bitmap finalBitmap, String fileName) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/" + folderName);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int randomNumber = 10000;
        randomNumber = generator.nextInt(randomNumber);

        if (fileName == null) {
            fileName = "image-" + randomNumber + ".jpg";
        } else {
            fileName = fileName + ".jpg";
        }
        File file = new File(myDir, fileName);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(context, "Image is Saved in /" + folderName + " in your Device!", Toast.LENGTH_SHORT).show();
            filePath = fileName;
            if (listeners != null) {
                listeners.convertedWithSuccess(bitmap, filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listeners != null) {
                listeners.convertedWithError(e.getMessage());
            }
        }
    }
}
