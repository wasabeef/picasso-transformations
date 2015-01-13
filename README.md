Picasso Transformations
======================
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
}

dependencies {
    compile 'jp.wasabeef:picasso-transformations:1.0.0'
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

## Transformations

### Crop
`CropTransformation`, `CropCircleTransformation`, `CropSquareTransformation`

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

Developed By
-------
Daichi Furiya (Wasabeef) - <dadadada.chop@gmail.com>

<a href="https://twitter.com/wasabeef_jp">
<img alt="Follow me on Twitter"
src="https://raw.githubusercontent.com/wasabeef/wasabeef.github.io/master/art/twitter.png" width="75"/>
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
