package jp.wasabeef.picasso.transformations.gpu;

/**
 * Copyright (C) 2020 Wasabeef
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
import android.graphics.PointF;

import java.util.Arrays;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageVignetteFilter;

/**
 * Performs a vignetting effect, fading out the image at the edges
 * The directional intensity of the vignetting,
 * with a default of x = 0.5, y = 0.5, start = 0, end = 0.75
 */
public class VignetteFilterTransformation extends GPUFilterTransformation {

  private final PointF mCenter;
  private final float[] mVignetteColor;
  private final float mVignetteStart;
  private final float mVignetteEnd;

  public VignetteFilterTransformation(Context context) {
    this(context, new PointF(0.5f, 0.5f), new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 0.75f);
  }

  public VignetteFilterTransformation(Context context, PointF center, float[] color, float start,
                                      float end) {
    super(context, new GPUImageVignetteFilter());
    mCenter = center;
    mVignetteColor = color;
    mVignetteStart = start;
    mVignetteEnd = end;
    GPUImageVignetteFilter filter = getFilter();
    filter.setVignetteCenter(mCenter);
    filter.setVignetteColor(mVignetteColor);
    filter.setVignetteStart(mVignetteStart);
    filter.setVignetteEnd(mVignetteEnd);
  }

  @Override
  public String key() {
    return "VignetteFilterTransformation(center=" + mCenter.toString() +
      ",color=" + Arrays.toString(mVignetteColor) +
      ",start=" + mVignetteStart + ",end=" + mVignetteEnd + ")";
  }
}
