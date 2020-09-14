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

import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter;

/**
 * contrast value ranges from 0.0 to 4.0, with 1.0 as the normal level
 */
public class ContrastFilterTransformation extends GPUFilterTransformation {

  private final float mContrast;

  public ContrastFilterTransformation(Context context) {
    this(context, 1.0f);
  }

  public ContrastFilterTransformation(Context context, float contrast) {
    super(context, new GPUImageContrastFilter());
    mContrast = contrast;
    GPUImageContrastFilter filter = getFilter();
    filter.setContrast(mContrast);
  }

  @Override
  public String key() {
    return "ContrastFilterTransformation(contrast=" + mContrast + ")";
  }
}
