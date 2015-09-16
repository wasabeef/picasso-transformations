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
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Transformation;
import jp.wasabeef.picasso.transformations.internal.Util;

public class MaskTransformation implements Transformation {

  private Context mContext;
  private int mMaskId;

  public MaskTransformation(Context context, int maskId) {
    mContext = context;
    mMaskId = maskId;
  }

  @Override public Bitmap transform(Bitmap source) {

    int width = source.getWidth();
    int height = source.getHeight();

    Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    Drawable drawable = Util.getMaskDrawable(mContext, mMaskId);
    drawable.setBounds(0, 0, width, height);
    Canvas canvas = new Canvas(mask);
    drawable.draw(canvas);

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    Paint paint = new Paint();
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

    canvas = new Canvas(bitmap);
    canvas.drawBitmap(mask, new Rect(0, 0, width, height), new Rect(0, 0, width, height), null);
    canvas.drawBitmap(source, 0, 0, paint);

    source.recycle();

    return bitmap;
  }

  @Override public String key() {
    return "MaskTransformation(maskId=" + mMaskId + ")";
  }
}
