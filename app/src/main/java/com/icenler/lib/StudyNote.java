/**************************************************************************************************/
/**
 * >>> Shader 之图形渲染
 *     - BitmapShader:      图像渲染
 *     - LinearGradient:    线性渐变
 *     - RadialGradient:    环形渐变
 *     - SweepGradient:     扇形渐变
 *     - ComposeShader:     混合渲染，适用于组合操作
 *     使用： mPaint.setShader(XXXShader)
 *
 *     Example：配合 Matrix 实现扇形动态渐变
 *
         Paint paint = new Paint();
         paint.setAntiAlias(true);
         paint.setStrokeWidth(8);
         paint.setStyle(Paint.Style.STROKE);

         int[] f = {Color.parseColor("#00A8D7A7"), Color.parseColor("#ffA8D7A7")};
         float[] p = {.0f, 1.0f};

         SweepGradient sweepGradient = new SweepGradient(rectF.centerX(), rectF.centerX(), f, p);
         Matrix matrix = new Matrix();
         sweepGradient.getLocalMatrix(matrix);
         matrix.postRotate(startAngle, rectF.centerX(), rectF.centerY());
         sweepGradient.setLocalMatrix(matrix);
         paint.setShader(sweepGradient);

         canvas.drawArc(rectF,0, 360, true, paint);
 * */


/**
 * >>> ViewConfiguration 之控件常用配置
 *      - 使用：ViewConfiguration.get(context)
 *
 * >>> VelocityTracker 之手势速度检测
 *      - 使用：VelocityTracker.obtain().addMovement(event)
 *
 * >>> Scroller 之模拟滑动
 *      - 使用：配合 computeScroll() + scrollTo(x, y) 实现控件滑动效果
 *          Scroller.startScroll(startX, startY, dx, dy, duration)
 * */


/**
 * >>> MeasureSpec 之控件测量
 *      * MeasureSpec 由大小和模式组成
 *      - AT_MOST：      对应 wrap_content
 *      - EXACTLY：      对应 match_parent
 *      - UNSPECIFIED：  具体数值
 * */

/**
 * SimpleDraweeView 属性使用：
     com.facebook.drawee.view.SimpleDraweeView
     android:id="@+id/my_image_view"
     android:layout_width="20dp"
     android:layout_height="20dp"
     fresco:fadeDuration="300"

     //设置图片的缩放类型
     fresco:actualImageScaleType="focusCrop"

     //设置图片加载成功前显示的图片也可以是背景色
     fresco:placeholderImage="@color/wait_color"
     fresco:placeholderImageScaleType="fitCenter"

     //图片加载失败的时候显示的图片
     fresco:failureImage="@drawable/error"
     fresco:failureImageScaleType="centerInside"

     //可设置加载失败点击重新加载，这是重新加载失败显示的图片
     fresco:retryImage="@drawable/retrying"
     fresco:retryImageScaleType="centerCrop"

     //显示进度条
     fresco:progressBarImage="@drawable/progress_bar"
     fresco:progressBarImageScaleType="centerInside"
     fresco:progressBarAutoRotateInterval="1000"

     //设置背景图的可以使颜色和图片
     fresco:backgroundImage="@color/blue"

     //设置图片加载完成覆盖的图片
     fresco:overlayImage="@drawable/watermark"
     fresco:pressedStateOverlayImage="@color/red"

     //将图片设置为圆形
     fresco:roundAsCircle="false"

     //设置图片4个角的圆角半径
     fresco:roundedCornerRadius="1dp"
     fresco:roundTopLeft="true"
     fresco:roundTopRight="false"
     fresco:roundBottomLeft="false"
     fresco:roundBottomRight="true"
     fresco:roundWithOverlayColor="@color/corner_color"
     fresco:roundingBorderWidth="2dp"
     fresco:roundingBorderColor="@color/border_color"
 * */
/**************************************************************************************************/