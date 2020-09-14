package jp.wasabeef.picasso.transformations.gpu;

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

import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter;

/**
 * Applies a Pixelation effect to the image.
 * <p>
 * The pixel with a default of 10.0.
 */
public class PixelationFilterTransformation extends GPUFilterTransformation {

  private final float mPixel;

  public PixelationFilterTransformation(Context context) {
    this(context, 10f);
  }

  public PixelationFilterTransformation(Context context, float pixel) {
    super(context, new GPUImagePixelationFilter());
    mPixel = pixel;
    GPUImagePixelationFilter filter = getFilter();
    filter.setPixel(mPixel);
  }

  @Override
  public String key() {
    return "PixelationFilterTransformation(pixel=" + mPixel + ")";
  }
}
