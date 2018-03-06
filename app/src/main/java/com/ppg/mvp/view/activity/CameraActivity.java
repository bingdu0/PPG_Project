package com.ppg.mvp.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

import java.io.IOException;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Create by Donny.
 * 2017/12/19
 * Description：
 */
@RuntimePermissions
public class CameraActivity extends BaseActivity{


    private Camera camera;
    private boolean isPreview = false;
    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_camera;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        CameraActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
    }

    public void inint(){
        SurfaceView mSurfaceView = findViewById(R.id.surface_view);
        SurfaceHolder mSurfaceHolder = mSurfaceView.getHolder();

        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);

        mSurfaceHolder.addCallback(mSurfaceCallback);
    }

    @Override
    protected void initListener() {

    }

    private SurfaceHolder.Callback mSurfaceCallback = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                camera = Camera.open();//打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera
                setCameraDisplayOrientation(CameraActivity.this,0,camera);
                camera.setPreviewDisplay(surfaceHolder);//通过SurfaceView显示取景画面
                camera.startPreview();//开始预览
                isPreview = true;//设置是否预览参数为真
            } catch (IOException e) {
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if(camera != null){
                if(isPreview){//正在预览
                    camera.stopPreview();
                    camera.release();
                }
            }
        }
    };

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {

        Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        //获取摄像头信息
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        //获取摄像头当前的角度
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            //前置摄像头
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else {
            // back-facing  后置摄像头
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        inint();
}

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CameraActivityPermissionsDispatcher.onRequestPermissionsResult( CameraActivity.this,requestCode,grantResults);
    }
}
