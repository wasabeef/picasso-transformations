package jp.wasabeef.picasso.transformations;

/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.squareup.picasso.Transformation;

public class CropTransformation implements Transformation {

  public enum CropType {
    TOP,
    CENTER,
    BOTTOM
  }

  private int mWidth;
  private int mHeight;
  private CropType mCropType = CropType.CENTER;

  public CropTransformation() {
  }

  public CropTransformation(int width, int height) {
    this(width, height, CropType.CENTER);
  }

  public CropTransformation(int width, int height, CropType cropType) {
    mWidth = width;
    mHeight = height;
    mCropType = cropType;
  }

  @Override public Bitmap transform(Bitmap source) {
    mWidth = mWidth == 0 ? source.getWidth() : mWidth;
    mHeight = mHeight == 0 ? source.getHeight() : mHeight;

    float scaleX = (float) mWidth / source.getWidth();
    float scaleY = (float) mHeight / source.getHeight();
    float scale = Math.max(scaleX, scaleY);

    float scaledWidth = scale * source.getWidth();
    float scaledHeight = scale * source.getHeight();
    float left = (mWidth - scaledWidth) / 2;
    float top = getTop(scaledHeight);
    RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

    Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, source.getConfig());
    Canvas canvas = new Canvas(bitmap);
    canvas.drawBitmap(source, null, targetRect, null);
    source.recycle();

    return bitmap;
  }

  @Override public String key() {
    return "CropTransformation(width=" + mWidth + ", height=" + mHeight + ", cropType=" + mCropType
        + ")";
  }

  private float getTop(float scaledHeight) {
    switch (mCropType) {
      case TOP:
        return 0;
      case CENTER:
        return (mHeight - scaledHeight) / 2;
      case BOTTOM:
        return mHeight - scaledHeight;
      default:
        return 0;
    }
  }
}
