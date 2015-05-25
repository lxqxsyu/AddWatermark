package com.example.addwatermark;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class ImageUtil {

	public static Bitmap createWaterMaskImage(
			Context Context, Bitmap src, Bitmap watermark,
			int paddingLeft, int paddingTop) {
		if (src == null) {
			return null;
		}
		//创建一个bitmap
		Bitmap newb = Bitmap.createBitmap(
				src.getWidth(), src.getHeight(), Config.ARGB_8888);
				// 创建一个新的和SRC长度宽度一样的位图
		//将该图片作为画布
		Canvas cv = new Canvas(newb);
		//在画布 0，0坐标上开始绘制原始图片
		cv.drawBitmap(src, 0, 0, null);
		//在画布上绘制水印图片
		cv.drawBitmap(watermark, paddingLeft, paddingTop, null);
		// 保存
		cv.save(Canvas.ALL_SAVE_FLAG);
		// 存储
		cv.restore();
		return newb;
	}
	
	/**
	 * 给图片添加文字
	 * @param context
	 * @param bitmap
	 * @param text
	 * @return
	 */
	public static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text) {
		Resources resources = context.getResources();
		float scale = resources.getDisplayMetrics().density;
		//bitmap = scaleWithWH(bitmap, 300 * scale, 300 * scale);
		android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

		// set default bitmap config if none
		if (bitmapConfig == null) {
			bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
		}
		// resource bitmaps are imutable,
		// so we need to convert it to mutable one
		bitmap = bitmap.copy(bitmapConfig, true);

		Canvas canvas = new Canvas(bitmap);
		// new antialised Paint
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// text color - #3D3D3D
		paint.setColor(Color.RED);
		paint.setTextSize((int) (18 * scale));
		paint.setDither(true); // 获取跟清晰的图像采样
		paint.setFilterBitmap(true);// 过滤一些
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		int x = 30;
		int y = 150;
		canvas.drawText(text, x * scale, y * scale, paint);
		return bitmap;
	}
	
	/**
	 * 缩放图片
	 * @param src
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float scaleWidth = (float) (w / width);
            float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(scaleWidth, scaleHeight);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }
}
