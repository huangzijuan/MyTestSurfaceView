package com.example.mytestsurfaceview;

import android.util.Log;

public class FPSUtils {
    private static long mFrameCount = 0L;
    private static long mTimeLastSample;
    private static float mFps = 0.0F;
    private static int FPS = 0;

    public static void doFps() {
        ++mFrameCount;
        long now = System.currentTimeMillis();
        long delta = now - mTimeLastSample;
        if (delta >= 1000L) {
            mFps = (float) mFrameCount / ((float) delta / 1000.0F);
            FPS = Math.round(mFps);
            Log.e("hzjhzj", "FPS: " + FPS);

            mTimeLastSample = now;
            mFrameCount = 0L;
        }

    }

    
    private static long lastOneHundredFrameTimeStamp = 0;
    private static int currentFrameCnt = 0;

    public static void fps() {
        if (++currentFrameCnt == 100) {
            currentFrameCnt = 0;
            long tmp = System.nanoTime();
            Log.e("FPSUtil", "FPS : " + (1000.0f * 1000000.0f / ((tmp - lastOneHundredFrameTimeStamp) / 100.0f)));
            lastOneHundredFrameTimeStamp = tmp;
        }
    }
}
