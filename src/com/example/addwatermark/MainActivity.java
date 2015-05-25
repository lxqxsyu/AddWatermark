package com.example.addwatermark;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private ImageView mSourImage;
	private ImageView mWartermarkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
    }
    
    private void initView(){
    	
    	mSourImage = (ImageView) findViewById(R.id.sour_pic);
    	mWartermarkImage = (ImageView) findViewById(R.id.wartermark_pic);
    	
    	
    	Bitmap sourBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sour_pic);
    	mSourImage.setImageBitmap(sourBitmap);
    	
    
    	Bitmap watermarkBitmap = 
    			ImageUtil.createWaterMaskImage(this, sourBitmap, 
    					BitmapFactory.decodeResource(getResources(), R.drawable.weixin),
    					10, 10);
    	
    	Bitmap textBitmap = ImageUtil.drawTextToBitmap(this, watermarkBitmap, "一段测试文字");
    	mWartermarkImage.setImageBitmap(textBitmap);
    }
}
