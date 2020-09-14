package jp.wasabeef.picasso.transformations;

/**
 * Copyright (C) 2018 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
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
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Transformation;

import jp.wasabeef.picasso.transformations.internal.Utils;

public class MaskTransformation implements Transformation {

  private static final Paint mMaskingPaint = new Paint();
  private final Context mContext;
  private final int mMaskId;

  static {
    mMaskingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
  }

  /**
   * @param maskId If you change the mask file, please also rename the mask file, or Glide will get
   * the cache with the old mask. Because getId() return the same values if using the
   * same make file name. If you have a good idea please tell us, thanks.
   */
  public MaskTransformation(Context context, int maskId) {
    mContext = context.getApplicationContext();
    mMaskId = maskId;
  }

  @Override
  public Bitmap transform(Bitmap source) {
    int width = source.getWidth();
    int height = source.getHeight();

    Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    Drawable mask = Utils.getMaskDrawable(mContext, mMaskId);

    Canvas canvas = new Canvas(result);
    mask.setBounds(0, 0, width, height);
    mask.draw(canvas);
    canvas.drawBitmap(source, 0, 0, mMaskingPaint);

    source.recycle();

    return result;
  }

  @Override
  public String key() {
    return "MaskTransformation(maskId=" + mContext.getResources().getResourceEntryName(mMaskId)
      + ")";
  }
}
