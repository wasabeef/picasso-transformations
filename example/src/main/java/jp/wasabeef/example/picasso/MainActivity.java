package jp.wasabeef.example.picasso;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.example.picasso.MainAdapter.Type;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    List<Type> dataSet = new ArrayList<>();
    dataSet.add(Type.Mask);
    dataSet.add(Type.NinePatchMask);
    dataSet.add(Type.CropCenterTop);
    dataSet.add(Type.CropCenterCenter);
    dataSet.add(Type.CropCenterBottom);
    dataSet.add(Type.CropSquare);
    dataSet.add(Type.CropCircle);
    dataSet.add(Type.ColorFilter);
    dataSet.add(Type.Grayscale);
    dataSet.add(Type.RoundedCorners);
    dataSet.add(Type.Blur);
    dataSet.add(Type.Toon);
    dataSet.add(Type.Sepia);
    dataSet.add(Type.Contrast);
    dataSet.add(Type.Invert);
    dataSet.add(Type.Pixel);
    dataSet.add(Type.Sketch);
    dataSet.add(Type.Swirl);
    dataSet.add(Type.Brightness);
    dataSet.add(Type.Kuawahara);
    dataSet.add(Type.Vignette);

    dataSet.add(Type.CropLeftTop);
    dataSet.add(Type.CropLeftCenter);
    dataSet.add(Type.CropLeftBottom);
    dataSet.add(Type.CropRightTop);
    dataSet.add(Type.CropRightCenter);
    dataSet.add(Type.CropRightBottom);
    dataSet.add(Type.Crop169CenterCenter);
    dataSet.add(Type.Crop43CenterCenter);
    dataSet.add(Type.Crop31CenterCenter);
    dataSet.add(Type.Crop31CenterTop);
    dataSet.add(Type.CropSquareCenterCenter);
    dataSet.add(Type.CropQuarterCenterCenter);
    dataSet.add(Type.CropQuarterCenterTop);
    dataSet.add(Type.CropQuarterBottomRight);
    dataSet.add(Type.CropHalfWidth43CenterCenter);

    recyclerView.setAdapter(new MainAdapter(this, dataSet));
  }
}
