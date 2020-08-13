package com.example.mytestsurfaceview;

import android.app.Activity;
import android.content.Context;  
import android.graphics.Canvas;  
import android.graphics.Color;  
import android.graphics.Paint;  
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;  
  
public class TestSurfaceView extends Activity {  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(new MyView(this));  
    }  
      
    class MyView extends SurfaceView implements SurfaceHolder.Callback,Runnable{  
        SurfaceHolder holder=null;  
        Paint paint;  
        public MyView(Context context) {  
            super(context);  
            // TODO Auto-generated constructor stub  
            holder=getHolder();  
            holder.addCallback(this);  
            paint=new Paint(Paint.ANTI_ALIAS_FLAG);  
            paint.setColor(Color.RED);  
              
            this.setFocusable(true);  
        }  
  
        @Override  
        public void surfaceChanged(SurfaceHolder holder, int format, int width,  
                int height) {  
            // TODO Auto-generated method stub  
              
        }  
  
        @Override  
        public void surfaceCreated(SurfaceHolder holder) {  
            // TODO Auto-generated method stub  
            Thread t=new Thread(this);  
            t.start();  
        }  
  
        @Override  
        public void surfaceDestroyed(SurfaceHolder holder) {  
            // TODO Auto-generated method stub  
            isRunning=false;  
        }  
  
        @Override  
        protected void onDraw(Canvas canvas) {  
            // TODO Auto-generated method stub
            Log.e("hzjhzj", "testSurfaceView ondraw");
            canvas=holder.lockCanvas();  
            //刷屏    
            canvas.drawColor(Color.BLACK);  
              
            canvas.drawCircle(x, y, 10, paint);  
              
            holder.unlockCanvasAndPost(canvas);  
        }

        private void paint(Paint paint) {
            FPSUtils.doFps();
            Canvas canvas=holder.lockCanvas();  
            //刷屏    
            canvas.drawColor(Color.BLACK);  
              
            canvas.drawCircle(x, y, 10, paint);  
              
            holder.unlockCanvasAndPost(canvas);  
        }  
          
        boolean isRunning=true;  
        @Override  
        public void run() {  
            // TODO Auto-generated method stub  
            while (isRunning) {  
//              onDraw(null);  
                paint(paint);  
                move();  
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  
            }  
        }  
          
        private int x,y;  
        private void move(){  
            x+=2;  
            y+=2;  
        }  
    }  
      
}  
