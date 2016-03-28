package jp.wasabeef.picasso.transformations;

/**
 * Copyright (C) 2015 Wasabeef, molexx
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
import android.graphics.Rect;
import android.util.Log;
import com.squareup.picasso.Transformation;

/**
 * Crops a rectangle, allowing its dimensions and positioning to be specified by a combination of:
 * width/height in pixels
 * width/height as a ratio of the source image
 * aspect ratio
 * offset from left/top in pixels
 * horizontal and vertical gravity
 *
 * If aspect ratio is provided then both width and height should not be provided or the ratio wil
 * be
 * ignored.
 * If neither width or height are provided then the aspect ratio is used to crop the largest
 * possible image.
 *
 * Constructors accepting width and height expect pixels if the values are ints and ratio of source
 * image if the
 * values are floats.
 */
public class CropTransformation implements Transformation {
  private static final String TAG = "PicassoTransformation";

  public enum GravityHorizontal {
    LEFT,
    CENTER,
    RIGHT
  }

  public enum GravityVertical {
    TOP,
    CENTER,
    BOTTOM
  }

  private float mAspectRatio;
  private int mLeft;
  private int mTop;
  private int mWidth;
  private int mHeight;
  private float mWidthRatio;
  private float mHeightRatio;
  private GravityHorizontal mGravityHorizontal = GravityHorizontal.CENTER;
  private GravityVertical mGravityVertical = GravityVertical.CENTER;

  /**
   * Crops to the given size and offset in pixels.
   * If either width or height is zero then the original image's dimension is used.
   *
   * @param left offset in pixels from the left edge of the source image
   * @param top offset in pixels from the top of the source image
   * @param width in pixels
   * @param height in pixels
   */
  public CropTransformation(int left, int top, int width, int height) {
    mLeft = left;
    mTop = top;
    mWidth = width;
    mHeight = height;
  }

  /**
   * Crops to the given width and height (in pixels) using the given gravity.
   * If either width or height is zero then the original image's dimension is used.
   *
   * @param width in pixels
   * @param height in pixels
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical position of the cropped area within the larger source image
   */
  public CropTransformation(int width, int height, GravityHorizontal gravityHorizontal,
      GravityVertical gravityVertical) {
    mWidth = width;
    mHeight = height;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  /**
   * Crops to the given width and height (in pixels), defaulting gravity to CENTER/CENTER.
   * If either width or height is zero then the original image's dimension is used.
   *
   * @param width in pixels
   * @param height in pixels
   */
  public CropTransformation(int width, int height) {
    this(width, height, GravityHorizontal.CENTER, GravityVertical.CENTER);
  }

  /**
   * Crops to a ratio of the source image's width/height.
   *
   * e.g. (0.5, 0.5, LEFT, TOP) will crop a quarter-sized box from the top left of the original.
   *
   * If widthRatio or heightRatio are zero then 100% of the original image's dimension will be
   * used.
   *
   * @param widthRatio width of the target image relative to the width of the source image; 1 =
   * 100%
   * @param heightRatio height of the target image relative to the height of the source image; 1 =
   * 100%
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical position of the cropped area within the larger source image
   */
  public CropTransformation(float widthRatio, float heightRatio,
      GravityHorizontal gravityHorizontal, GravityVertical gravityVertical) {
    mWidthRatio = widthRatio;
    mHeightRatio = heightRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  /**
   * Crops to a ratio of the source image's width/height, defaulting to CENTER/CENTER gravity.
   *
   * e.g. (0.5, 0.5) will crop a quarter-sized box from the middle of the original.
   *
   * If widthRatio or heightRatio are zero then 100% of the original image's dimension will be
   * used.
   *
   * @param widthRatio width of the target image relative to the width of the source image; 1 =
   * 100%
   * @param heightRatio height of the target image relative to the height of the source image; 1 =
   * 100%
   */
  public CropTransformation(float widthRatio, float heightRatio) {
    this(widthRatio, heightRatio, GravityHorizontal.CENTER, GravityVertical.CENTER);
  }

  /**
   * Crops to the desired aspectRatio driven by either width OR height in pixels.
   * If one of width/height is greater than zero the other is calculated
   * If width and height are both zero then the largest area matching the aspectRatio is returned
   * If both width and height are specified then the aspectRatio is ignored
   *
   * If aspectRatio is 0 then the result will be the same as calling the version without
   * aspectRatio.
   *
   * @param width in pixels, one of width/height should be zero
   * @param height in pixels, one of width/height should be zero
   * @param aspectRatio width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
   * square
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical position of the cropped area within the larger source image
   */
  public CropTransformation(int width, int height, float aspectRatio,
      GravityHorizontal gravityHorizontal, GravityVertical gravityVertical) {
    mWidth = width;
    mHeight = height;
    mAspectRatio = aspectRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  /**
   * Crops to the desired aspectRatio driven by either width OR height as a ratio to the original
   * image's dimension.
   * If one of width/height is greater than zero the other is calculated
   * If width and height are both zero then the largest area matching the aspectRatio is returned
   * If both width and height are specified then the aspectRatio is ignored
   *
   * If aspectRatio is 0 then the result will be the same as calling the version without
   * aspectRatio.
   *
   * e.g. to get an image with a width of 50% of the source image's width and cropped to 16:9 from
   * the center/center:
   * CropTransformation(0.5, (float)0, (float)16/9, CENTER, CENTER);
   *
   * @param widthRatio width of the target image relative to the width of the source image; 1 =
   * 100%
   * @param heightRatio height of the target image relative to the height of the source image; 1 =
   * 100%
   * @param aspectRatio width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
   * square
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical position of the cropped area within the larger source image
   */
  public CropTransformation(float widthRatio, float heightRatio, float aspectRatio,
      GravityHorizontal gravityHorizontal, GravityVertical gravityVertical) {
    mWidthRatio = widthRatio;
    mHeightRatio = heightRatio;
    mAspectRatio = aspectRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  /**
   * Crops to the largest image that will fit the given aspectRatio.
   * This will effectively chop off either the top/bottom or left/right of the source image.
   *
   * @param aspectRatio width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
   * square
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical position of the cropped area within the larger source image
   */
  public CropTransformation(float aspectRatio, GravityHorizontal gravityHorizontal,
      GravityVertical gravityVertical) {
    mAspectRatio = aspectRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  @Override public Bitmap transform(Bitmap source) {
    if (Log.isLoggable(TAG, Log.VERBOSE)) Log.v(TAG, "transform(): called, " + key());

    if (mWidth == 0 && mWidthRatio != 0) {
      mWidth = (int) ((float) source.getWidth() * mWidthRatio);
    }
    if (mHeight == 0 && mHeightRatio != 0) {
      mHeight = (int) ((float) source.getHeight() * mHeightRatio);
    }

    if (mAspectRatio != 0) {
      if (mWidth == 0 && mHeight == 0) {
        float sourceRatio = (float) source.getWidth() / (float) source.getHeight();

        if (Log.isLoggable(TAG, Log.VERBOSE)) {
          Log.v(TAG,
              "transform(): mAspectRatio: " + mAspectRatio + ", sourceRatio: " + sourceRatio);
        }

        if (sourceRatio > mAspectRatio) {
          // source is wider than we want, restrict by height
          mHeight = source.getHeight();
        } else {
          // source is taller than we want, restrict by width
          mWidth = source.getWidth();
        }
      }

      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG, "transform(): before setting other of h/w: mAspectRatio: " + mAspectRatio
            + ", set one of width: " + mWidth + ", height: " + mHeight);
      }

      if (mWidth != 0) {
        mHeight = (int) ((float) mWidth / mAspectRatio);
      } else {
        if (mHeight != 0) {
          mWidth = (int) ((float) mHeight * mAspectRatio);
        }
      }

      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG,
            "transform(): mAspectRatio: " + mAspectRatio + ", set width: " + mWidth + ", height: "
                + mHeight);
      }
    }

    if (mWidth == 0) {
      mWidth = source.getWidth();
    }

    if (mHeight == 0) {
      mHeight = source.getHeight();
    }

    if (mGravityHorizontal != null) {
      mLeft = getLeft(source);
    }
    if (mGravityVertical != null) {
      mTop = getTop(source);
    }

    Rect sourceRect = new Rect(mLeft, mTop, (mLeft + mWidth), (mTop + mHeight));
    Rect targetRect = new Rect(0, 0, mWidth, mHeight);

    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG,
          "transform(): created sourceRect with mLeft: " + mLeft + ", mTop: " + mTop + ", right: "
              + (mLeft + mWidth) + ", bottom: " + (mTop + mHeight));
    }
    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "transform(): created targetRect with width: " + mWidth + ", height: " + mHeight);
    }

    Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "transform(): copying from source with width: " + source.getWidth() + ", height: "
          + source.getHeight());
    }
    canvas.drawBitmap(source, sourceRect, targetRect, null);

    source.recycle();

    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "transform(): returning bitmap with width: " + bitmap.getWidth() + ", height: "
          + bitmap.getHeight());
    }

    return bitmap;
  }

  @Override public String key() {
    return "CropTransformation(width=" + mWidth + ", height=" + mHeight + ", mWidthRatio="
        + mWidthRatio + ", mHeightRatio=" + mHeightRatio + ", mAspectRatio=" + mAspectRatio
        + ", gravityHorizontal=" + mGravityHorizontal + ", mGravityVertical=" + mGravityVertical
        + ")";
  }

  private int getTop(Bitmap source) {
    switch (mGravityVertical) {
      case TOP:
        return 0;
      case CENTER:
        return (source.getHeight() - mHeight) / 2;
      case BOTTOM:
        return source.getHeight() - mHeight;
      default:
        return 0;
    }
  }

  private int getLeft(Bitmap source) {
    switch (mGravityHorizontal) {
      case LEFT:
        return 0;
      case CENTER:
        return (source.getWidth() - mWidth) / 2;
      case RIGHT:
        return source.getWidth() - mWidth;
      default:
        return 0;
    }
  }
}
