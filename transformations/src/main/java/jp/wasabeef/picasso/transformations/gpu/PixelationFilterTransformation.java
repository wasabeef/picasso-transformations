package jp.wasabeef.picasso.transformations.gpu;

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

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;

public class PixelationFilterTransformation implements Transformation {

    private Context mContext;

    private GPUImagePixelationFilter mFilter = new GPUImagePixelationFilter();
    private float mPixel;

    public PixelationFilterTransformation(Context context) {
        mContext = context;
    }

    public PixelationFilterTransformation(Context context, float pixel) {
        mContext = context;
        mPixel = pixel;
        mFilter.setPixel(mPixel);
    }

    @Override
    public Bitmap transform(Bitmap source) {

        GPUImage gpuImage = new GPUImage(mContext);
        gpuImage.setImage(source);
        gpuImage.setFilter(mFilter);
        Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();

        source.recycle();

        return bitmap;
    }

    @Override
    public String key() {
        return "PixelationFilterTransformation(pixel=" + mPixel + ")";
    }
}
