Picasso Transformations
======================
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-picasso--transformations-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1372)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/wasabeef/maven/picasso-transformations/images/download.svg)](https://bintray.com/wasabeef/maven/picasso-transformations/_latestVersion)

An Android transformation library providing a variety of image transformations for [Picasso](https://github.com/square/picasso).

Please feel free to use this.


#### Are you using Glide?
[Glide Transformations](https://github.com/wasabeef/glide-transformations)
 
# Demo

### Original Image
<img src="art/demo-org.jpg" width="30%">

### Transformations
<img src="art/demo.gif" width="50%">

# How do I use it?

## Step 1

#### Gradle
```groovy
repositories {
    jcenter()
    mavenCentral()  // GPUImage for Android
}

dependencies {
    compile 'jp.wasabeef:picasso-transformations:1.0.3@aar'
    compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.2.3@aar'
}
```

## Step 2

Set Picasso Transform.

```java
Picasso.with(mContext).load(R.drawable.demo)
        .transform(transformation).into((ImageView) findViewById(R.id.image));
```

## Advanced Step 3

You can set a multiple transformations.

```java
Picasso.with(mContext).load(R.drawable.demo)
                .transform(transformation)
                .transform(new CropCircleTransformation())
                .into(holder.image);
```

## Step 4

If you are using `BlurTransformation`.

```groovy
android {
    ...
    defaultConfig {
        ...
        renderscriptTargetApi 21
        renderscriptSupportModeEnabled true
    }
}
```

## Transformations

### Crop
`CropTransformation(Top, Center, Bottom)`, `CropCircleTransformation`, `CropSquareTransformation`

### Color
`ColorFilterTransformation`, `GrayscaleTransformation`

### Blur
`BlurTransformation`

### Filter (use [GPUImage](https://github.com/CyberAgent/android-gpuimage))
`ToonFilterTransformation`, `SepiaFilterTransformation`, `ContrastFilterTransformation`  
`InvertFilterTransformation`, `PixelationFilterTransformation`, `SketchFilterTransformation`  
`SwirlFilterTransformation`, `KuwaharaFilterTransformation`, `VignetteFilterTransformation`

### Other
`RoundedCornersTransformation`

Applications using Picasso Transformations
---

Please [ping](mailto:dadadada.chop@gmail.com) me or send a pull request if you would like to be added here.

Icon | Application
------------ | -------------
<img src="https://lh6.ggpht.com/6zKH_uQY1bxCwXL4DLo_uoFEOXdShi3BgmN6XRHlaJ-oA1svmq6y1PZkmO50nWQn2Lg=w300-rw" width="48" height="48" /> | [Ameba Ownd](https://play.google.com/store/apps/details?id=jp.co.cyberagent.madrid)

Developed By
-------
Daichi Furiya (Wasabeef) - <dadadada.chop@gmail.com>

<a href="https://twitter.com/wasabeef_jp">
<img alt="Follow me on Twitter"
src="https://raw.githubusercontent.com/wasabeef/art/master/twitter.png" width="75"/>
</a>

Thanks
-------

* Inspired by `Picasso Transformations` in [TannerPerrien](https://github.com/TannerPerrien).

License
-------

    Copyright 2015 Wasabeef

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
