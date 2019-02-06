package com.intandif.viewtoimageorpdf;

import android.graphics.Bitmap;

public interface ActionListeners {
    public void convertedWithSuccess(Bitmap bitmap, String filePath);
    public void convertedWithError(String error);

}
