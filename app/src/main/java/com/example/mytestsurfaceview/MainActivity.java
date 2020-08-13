package com.example.mytestsurfaceview;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLSurfaceView mGLSurfaceView = (GLSurfaceView) findViewById(R.id.glsv_main);
        mGLSurfaceView.setRenderer(new SunnyGLRender());
        mGLSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        HookUtils.hookGLSurfaceViewRender(getWindow().getDecorView());
    }
}
