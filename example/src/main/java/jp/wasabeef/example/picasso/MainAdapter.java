package jp.wasabeef.example.picasso;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.CropTransformation;
import jp.wasabeef.picasso.transformations.GrayscaleTransformation;
import jp.wasabeef.picasso.transformations.MaskTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import jp.wasabeef.picasso.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by Wasabeef on 2015/01/11.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

  private Context mContext;
  private List<Type> mDataSet;

  enum Type {
    Mask,
    NinePatchMask,
    CropLeftTop,
    CropLeftCenter,
    CropLeftBottom,
    CropCenterTop,
    CropCenterCenter,
    CropCenterBottom,
    CropRightTop,
    CropRightCenter,
    CropRightBottom,
    CropSquareCenterCenter,
    Crop169CenterCenter,
    Crop43CenterCenter,
    Crop31CenterCenter,
    Crop31CenterTop,
    CropQuarterCenterCenter,
    CropQuarterCenterTop,
    CropQuarterBottomRight,
    CropHalfWidth43CenterCenter,
    CropSquare,
    CropCircle,
    ColorFilter,
    Grayscale,
    RoundedCorners,
    Blur,
    Toon,
    Sepia,
    Contrast,
    Invert,
    Pixel,
    Sketch,
    Swirl,
    Brightness,
    Kuawahara,
    Vignette
  }

  public MainAdapter(Context context, List<Type> dataSet) {
    mContext = context;
    mDataSet = dataSet;
  }

  @Override public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
    switch (mDataSet.get(position)) {
      case Mask: {
        int width = Utils.dip2px(mContext, 133.33f);
        int height = Utils.dip2px(mContext, 126.33f);
        Picasso.with(mContext)
            .load(R.drawable.check)
            .resize(width, height)
            .centerCrop()
            .transform((new MaskTransformation(mContext, R.drawable.mask_starfish)))
            .into(holder.image);
        break;
      }
      case NinePatchMask: {
        int width = Utils.dip2px(mContext, 150.0f);
        int height = Utils.dip2px(mContext, 100.0f);
        Picasso.with(mContext)
            .load(R.drawable.check)
            .resize(width, height)
            .centerCrop()
            .transform(new MaskTransformation(mContext, R.drawable.chat_me_mask))
            .into(holder.image);
        break;
      }
      case CropLeftTop:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
                CropTransformation.GravityVertical.TOP))
            .into(holder.image);
        break;
      case CropLeftCenter:
        Picasso.with(mContext).load(R.drawable.demo)
            // 300, 100, CropTransformation.GravityHorizontal.LEFT, CropTransformation.GravityVertical.CENTER))
            .transform(new CropTransformation(300, 100)).into(holder.image);
        break;
      case CropLeftBottom:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
                CropTransformation.GravityVertical.BOTTOM))
            .into(holder.image);
        break;
      case CropCenterTop:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.TOP))
            .into(holder.image);
        break;
      case CropCenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100))
            .into(holder.image);
        break;
      case CropCenterBottom:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.BOTTOM))
            .into(holder.image);
        break;
      case CropRightTop:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                CropTransformation.GravityVertical.TOP))
            .into(holder.image);
        break;
      case CropRightCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case CropRightBottom:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                CropTransformation.GravityVertical.BOTTOM))
            .into(holder.image);
        break;
      case Crop169CenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation((float) 16 / (float) 9,
                CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case Crop43CenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation((float) 4 / (float) 3,
                CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case Crop31CenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case Crop31CenterTop:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.TOP))
            .into(holder.image);
        break;
      case CropSquareCenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation(1, CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case CropQuarterCenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation((float) 0.5, (float) 0.5,
                CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case CropQuarterCenterTop:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation((float) 0.5, (float) 0.5,
                CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.TOP))
            .into(holder.image);
        break;
      case CropQuarterBottomRight:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation((float) 0.5, (float) 0.5,
                CropTransformation.GravityHorizontal.RIGHT,
                CropTransformation.GravityVertical.BOTTOM))
            .into(holder.image);
        break;
      case CropHalfWidth43CenterCenter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropTransformation((float) 0.5, 0, (float) 4 / (float) 3,
                CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER))
            .into(holder.image);
        break;
      case CropSquare:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropSquareTransformation())
            .into(holder.image);
        break;
      case CropCircle:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new CropCircleTransformation())
            .into(holder.image);
        break;
      case ColorFilter:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
            .into(holder.image);
        break;
      case Grayscale:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new GrayscaleTransformation())
            .into(holder.image);
        break;
      case RoundedCorners:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new RoundedCornersTransformation(30, 0,
                RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
            .into(holder.image);
        break;
      case Blur:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new BlurTransformation(mContext, 25, 1))
            .into(holder.image);
        break;
      case Toon:
        Picasso.with(mContext)
            .load(R.drawable.demo)
            .transform(new ToonFilterTransformation(mContext))
            .into(holder.image);
        break;
      case Sepia:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new SepiaFilterTransformation(mContext))
            .into(holder.image);
        break;
      case Contrast:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new ContrastFilterTransformation(mContext, 2.0f))
            .into(holder.image);
        break;
      case Invert:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new InvertFilterTransformation(mContext))
            .into(holder.image);
        break;
      case Pixel:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new PixelationFilterTransformation(mContext, 20))
            .into(holder.image);
        break;
      case Sketch:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new SketchFilterTransformation(mContext))
            .into(holder.image);
        break;
      case Swirl:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
            .into(holder.image);

        break;
      case Brightness:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new BrightnessFilterTransformation(mContext, 0.5f))
            .into(holder.image);
        break;
      case Kuawahara:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new KuwaharaFilterTransformation(mContext, 25))
            .into(holder.image);
        break;
      case Vignette:
        Picasso.with(mContext)
            .load(R.drawable.check)
            .transform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f))
            .into(holder.image);
        break;
    }
    holder.title.setText(mDataSet.get(position).name());
  }

  @Override public int getItemCount() {
    return mDataSet.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;

    ViewHolder(View itemView) {
      super(itemView);
      image = (ImageView) itemView.findViewById(R.id.image);
      title = (TextView) itemView.findViewById(R.id.title);
    }
  }
}
