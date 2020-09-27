Change Log
==========

Version 2.4.0 *(2020-09-27)*
----------------------------

- minSdkVersion -> 21
- GPUImage -> 2.1.0

Version 2.3.1 *(2020-09-19)*
----------------------------

Bug Fix:
- Fix param of drawDiagonalFromTopLeftRoundRect

Version 2.3.0 *(2020-09-15)*
----------------------------

Update:
- Compile & Target SDK Version 27 -> 30
- GPUImage 1.4.1 -> 2.0.4
- Picasso 2.5.2 -> 2.8.0

Version 2.2.1 *(2018-02-13)*
----------------------------

Bug Fix:  
- Fix settings for proguard [#38](https://github.com/wasabeef/picasso-transformations/pull/38)  

Version 2.2.0 *(2018-02-02)*
----------------------------

Update:  
- Compile & Target SDK Version 25 -> 27  
- Build Tools 26.0.1 -> 27.0.3  
- Support Library 25.3.1 -> 27.0.2  

Version 2.1.2 *(2017-03-21)*
----------------------------

Bug Fix:
- BlurTransformation crashes on Android 4.2.

Version 2.1.1 *(2017-03-17)*
----------------------------

Update:
- Compile & Target SDK Version 23 -> 25
- Build Tools 23.0.2 -> 25.0.2
- Support Library 23.1.1 -> 25.3.0

Bug Fix:
- Additional resource cleanup in RSBlur.

Version 2.1.0 *(2016-03-28)*
----------------------------

Extended the CropTransformation by [@molexx](https://github.com/molexx)
  - Horizontal gravity
  - Crop to aspect ratio
  - Crop to width/height as a ratio of original image's width/height
  - Crop to exact area (specify left and top)

Version 2.0.0 *(2016-03-02)*
----------------------------

Say v8.RenderScript goodbye

Version 1.4.0 *(2016-02-28)*
----------------------------

Use FastBlur as a fallback upon RenderScript failure.

Version 1.3.1 *(2015-11-27)*
----------------------------

Change the renderscriptTargetApi down to 20.  
 Warning:Renderscript support mode is not currently supported with renderscript target 21+  

fix: memory leak.

Version 1.3.0 *(2015-11-10)*
----------------------------

add round corner type to RoundedCornersTransformation.  
Thanks to [kaelaela](https://github.com/kaelaela)

Version 1.2.1 *(2015-09-16)*
----------------------------

Optimization.

Version 1.2.0 *(2015-09-16)*
----------------------------

add new transformations MaskTransformation.
Thanks to [start141](https://github.com/start141)

Version 1.1.0 *(2015-09-06)*
----------------------------

Adjustment of default parameters.

Version 1.0.5 *(2015-07-24)*
----------------------------

add DownSampling to BlurTransform

Version 1.0.4 *(2015-04-28)*
----------------------------

update support-library

Version 1.0.3 *(2015-04-23)*
----------------------------

add: CropType(Top, Center, Bottom) for CropTransformation

Version 1.0.2 *(2015-03-09)*
----------------------------

fix: source isn't square, move viewport to center

Version 1.0.1 *(2015-01-21)*
----------------------------

fix: Blur Transformation now woking at Android 4.3
add: GPUImage to Gradle dependency 

Version 1.0.0 *(2015-01-14)*
----------------------------

Initial release.
