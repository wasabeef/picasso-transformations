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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import com.squareup.picasso.Transformation;

public class BlurTransformation implements Transformation {

  private static int MAX_RADIUS = 25;
  private static int DEFAULT_DOWN_SAMPLING = 1;

  private Context mContext;

  private int mRadius;
  private int mSampling;

  public BlurTransformation(Context context) {
    this(context, MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
  }

  public BlurTransformation(Context context, int radius) {
    this(context, radius, DEFAULT_DOWN_SAMPLING);
  }

  public BlurTransformation(Context context, int radius, int sampling) {
    mContext = context.getApplicationContext();
    mRadius = radius;
    mSampling = sampling;
  }

  @Override public Bitmap transform(Bitmap source) {

    int scaledWidth = source.getWidth() / mSampling;
    int scaledHeight = source.getHeight() / mSampling;

    Bitmap bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(bitmap);
    canvas.scale(1 / (float) mSampling, 1 / (float) mSampling);
    Paint paint = new Paint();
    paint.setFlags(Paint.FILTER_BITMAP_FLAG);
    canvas.drawBitmap(source, 0, 0, paint);

    RenderScript rs = RenderScript.create(mContext);
    Allocation input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
        Allocation.USAGE_SCRIPT);
    Allocation output = Allocation.createTyped(rs, input.getType());
    ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

    blur.setInput(input);
    blur.setRadius(mRadius);
    blur.forEach(output);
    output.copyTo(bitmap);

    source.recycle();
    rs.destroy();

    return bitmap;
  }

  @Override public String key() {
    return "BlurTransformation(radius=" + mRadius + ", sampling=" + mSampling + ")";
  }
}
