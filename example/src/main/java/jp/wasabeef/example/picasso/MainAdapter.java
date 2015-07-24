package jp.wasabeef.example.picasso;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.CropTransformation;
import jp.wasabeef.picasso.transformations.GrayscaleTransformation;
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
        CropTop,
        CropCenter,
        CropBottom,
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

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        Transformation transformation = null;
        switch (mDataSet.get(position)) {
            case CropTop:
                transformation = new CropTransformation(300, 100, CropTransformation.CropType.TOP);
                break;
            case CropCenter:
                transformation = new CropTransformation(300, 100);
                break;
            case CropBottom:
                transformation =
                        new CropTransformation(300, 100, CropTransformation.CropType.BOTTOM);
                break;
            case CropSquare:
                transformation = new CropSquareTransformation();
                break;
            case CropCircle:
                transformation = new CropCircleTransformation();
                break;
            case ColorFilter:
                transformation = new ColorFilterTransformation(Color.argb(80, 255, 0, 0));
                break;
            case Grayscale:
                transformation = new GrayscaleTransformation();
                break;
            case RoundedCorners:
                transformation = new RoundedCornersTransformation(100, 0);
                break;
            case Blur:
                transformation = new BlurTransformation(mContext, 25, 4);
                break;
            case Toon:
                transformation = new ToonFilterTransformation(mContext);
                break;
            case Sepia:
                transformation = new SepiaFilterTransformation(mContext);
                break;
            case Contrast:
                transformation = new ContrastFilterTransformation(mContext, 2.0f);
                break;
            case Invert:
                transformation = new InvertFilterTransformation(mContext);
                break;
            case Pixel:
                transformation = new PixelationFilterTransformation(mContext, 20);
                break;
            case Sketch:
                transformation = new SketchFilterTransformation(mContext);
                break;
            case Swirl:
                transformation = new SwirlFilterTransformation(mContext, 0.5f, 1.0f,
                        new PointF(0.5f, 0.5f));
                break;
            case Brightness:
                transformation = new BrightnessFilterTransformation(mContext, 0.5f);
                break;
            case Kuawahara:
                transformation = new KuwaharaFilterTransformation(mContext, 25);
                break;
            case Vignette:
                transformation = new VignetteFilterTransformation(mContext,
                        new PointF(0.5f, 0.5f), new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f);
                break;
        }

        Picasso.with(mContext).load(R.drawable.demo)
                .transform(transformation).into(holder.image);
        holder.title.setText(mDataSet.get(position).name());
    }

    @Override
    public int getItemCount() {
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
