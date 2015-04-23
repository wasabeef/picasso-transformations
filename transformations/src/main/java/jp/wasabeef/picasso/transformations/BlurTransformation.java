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

import com.squareup.picasso.Transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

public class BlurTransformation implements Transformation {

    private static int MAX_RADIUS = 25;

    private Context mContext;

    private int mRadius;

    public BlurTransformation(Context context) {
        this(context, MAX_RADIUS);
    }

    public BlurTransformation(Context context, int radius) {
        mContext = context;
        mRadius = radius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap outBitmap =
                Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        canvas.drawBitmap(source, 0, 0, null);

        RenderScript rs = RenderScript.create(mContext);
        Allocation overlayAlloc = Allocation.createFromBitmap(rs, outBitmap);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(mRadius);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(outBitmap);

        source.recycle();
        rs.destroy();

        return outBitmap;
    }

    @Override
    public String key() {
        return "BlurTransformation(radius=" + mRadius + ")";
    }
}
