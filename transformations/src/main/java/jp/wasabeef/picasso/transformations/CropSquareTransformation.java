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
import com.squareup.picasso.Transformation;

public class CropSquareTransformation implements Transformation {

  private int mWidth;
  private int mHeight;

  @Override public Bitmap transform(Bitmap source) {
    int size = Math.min(source.getWidth(), source.getHeight());

    mWidth = (source.getWidth() - size) / 2;
    mHeight = (source.getHeight() - size) / 2;

    Bitmap bitmap = Bitmap.createBitmap(source, mWidth, mHeight, size, size);
    if (bitmap != source) {
      source.recycle();
    }

    return bitmap;
  }

  @Override public String key() {
    return "CropSquareTransformation(width=" + mWidth + ", height=" + mHeight + ")";
  }
}
