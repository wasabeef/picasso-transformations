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

import android.content.Context;
import android.graphics.Bitmap;
import com.squareup.picasso.Transformation;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;

public class SketchFilterTransformation implements Transformation {

  private Context mContext;

  public SketchFilterTransformation(Context context) {
    mContext = context;
  }

  @Override public Bitmap transform(Bitmap source) {

    GPUImage gpuImage = new GPUImage(mContext);
    gpuImage.setImage(source);
    gpuImage.setFilter(new GPUImageSketchFilter());
    Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();

    source.recycle();

    return bitmap;
  }

  @Override public String key() {
    return "SketchFilterTransformation()";
  }
}
