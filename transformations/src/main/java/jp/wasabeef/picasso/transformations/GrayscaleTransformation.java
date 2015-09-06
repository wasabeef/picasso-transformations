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
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import com.squareup.picasso.Transformation;

public class GrayscaleTransformation implements Transformation {

  @Override public Bitmap transform(Bitmap source) {

    int width = source.getWidth();
    int height = source.getHeight();

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(bitmap);
    ColorMatrix saturation = new ColorMatrix();
    saturation.setSaturation(0f);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(saturation));
    canvas.drawBitmap(source, 0, 0, paint);
    source.recycle();

    return bitmap;
  }

  @Override public String key() {
    return "GrayscaleTransformation()";
  }
}
