package com.bytedance.android.lesson.restapi.solution;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.ExifInterface;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.bytedance.android.lesson.restapi.solution.Utils.MEDIA_TYPE_IMAGE;
import static com.bytedance.android.lesson.restapi.solution.Utils.MEDIA_TYPE_VIDEO;
import static com.bytedance.android.lesson.restapi.solution.Utils.getOutputMediaFile;

public class Solution2Q2Activity extends AppCompatActivity {


    private SurfaceView mSurfaceView;
    private Camera mCamera;

    private int CAMERA_TYPE = Camera.CameraInfo.CAMERA_FACING_BACK;

    private boolean isRecording = false;

    private int rotationDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_solution2_q2);

        mSurfaceView = findViewById(R.id.img);
        //todo 给SurfaceHolder添加Callback
        mCamera = getCamera(CAMERA_TYPE);
        rotationDegree = getCameraDisplayOrientation(CAMERA_TYPE);;
        mCamera.setDisplayOrientation(rotationDegree);
        mSurfaceView = findViewById(R.id.img);
        final SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mCamera.setPreviewDisplay(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCamera.startPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        });

        findViewById(R.id.btn_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 拍一张照片
                mCamera.takePicture(null, null, mPicture);
            }
        });

        findViewById(R.id.btn_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 录制，第一次点击是start，第二次点击是stop
                //mMediaRecorder = new MediaRecorder();
                if (isRecording) {
                    //todo 停止录制
                    Solution2Q2Activity.this.releaseMediaRecorder();
                    isRecording = false;
                } else {
                    //todo 录制
                    mMediaRecorder = new MediaRecorder();
                    mCamera.unlock();
                    mMediaRecorder.setCamera(mCamera);
                    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                    mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
                    mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
                    mMediaRecorder.setPreviewDisplay(mSurfaceView.getHolder().getSurface());
                    mMediaRecorder.setOrientationHint(rotationDegree);
                    try {
                        mMediaRecorder.prepare();
                        mMediaRecorder.start();
                    } catch (IOException e) {
                        Solution2Q2Activity.this.releaseMediaRecorder();
                        e.printStackTrace();
                    }
                    isRecording = true;
                }
            }
        });

        findViewById(R.id.btn_facing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 切换前后摄像头
                switch (CAMERA_TYPE) {
                    case Camera.CameraInfo.CAMERA_FACING_BACK: {
                        mCamera = Solution2Q2Activity.this.getCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
                        break;
                    }
                    case Camera.CameraInfo.CAMERA_FACING_FRONT: {
                        mCamera = Solution2Q2Activity.this.getCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
                        break;
                    }
                    default:
                        break;
                }
                rotationDegree = Solution2Q2Activity.this.getCameraDisplayOrientation(CAMERA_TYPE);
                ;
                mCamera.setDisplayOrientation(rotationDegree);
                try {
                    mCamera.setPreviewDisplay(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCamera.startPreview();
            }
        });

        findViewById(R.id.btn_zoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 调焦，需要判断手机是否支持
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            Log.d("TAG", "onAutoFocus: success");
                        } else {
                            Log.d("TAG", "onAutoFocus: fail");
                        }
                    }
                });

            }
        });
    }

    public Camera getCamera(int position) {
        CAMERA_TYPE = position;
        if (mCamera != null) {
            releaseCameraAndPreview();
        }
        Camera cam = Camera.open(position);

        //todo 摄像头添加属性，例是否自动对焦，设置旋转方向等

        return cam;
    }


    private static final int DEGREE_90 = 90;
    private static final int DEGREE_180 = 180;
    private static final int DEGREE_270 = 270;
    private static final int DEGREE_360 = 360;

    private int getCameraDisplayOrientation(int cameraId) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = DEGREE_90;
                break;
            case Surface.ROTATION_180:
                degrees = DEGREE_180;
                break;
            case Surface.ROTATION_270:
                degrees = DEGREE_270;
                break;
            default:
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % DEGREE_360;
            result = (DEGREE_360 - result) % DEGREE_360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + DEGREE_360) % DEGREE_360;
        }
        return result;
    }


    private void releaseCameraAndPreview() {
        //todo 释放camera资源
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    Camera.Size size;

    private void startPreview(SurfaceHolder holder) {
        //todo 开始预览
    }


    private MediaRecorder mMediaRecorder;

    private boolean prepareVideoRecorder() {
        //todo 准备MediaRecorder

        return true;
    }


    private void releaseMediaRecorder() {
        //todo 释放MediaRecorder
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        mMediaRecorder.release();
        mMediaRecorder = null;
        mCamera.lock();
    }


//    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
//        @Override
//        public void onPictureTaken(byte[] data, Camera camera) {
//            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//            if (pictureFile == null) {
//                return;
//            }
//            try {
//                FileOutputStream fos = new FileOutputStream(pictureFile);
//                fos.write(data);
//                fos.close();
//            } catch (IOException e) {
//                Log.d("mPicture", "Error accessing file: " + e.getMessage());
//            }
//
//            mCamera.startPreview();
//        }
//    };
private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (pictureFile == null) {
            return;
        }
        try {
//                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                bmp=rotateBmp(bmp,90);
            FileOutputStream fos = new FileOutputStream(pictureFile);
//                bmp.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.write(data);
            fos.close();
            Toast.makeText(Solution2Q2Activity.this, "写入成功！", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("mPicture", "Error accessing file: " + e.getMessage());
        }
        try {
            ExifInterface exif = new ExifInterface(pictureFile.getAbsolutePath());
            switch (rotationDegree) {
                case DEGREE_90:
                    exif.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(ExifInterface.ORIENTATION_ROTATE_90));
                    break;
                case DEGREE_180:
                    exif.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(ExifInterface.ORIENTATION_ROTATE_180));
                    break;
                case DEGREE_270:
                    exif.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(ExifInterface.ORIENTATION_ROTATE_270));
                    break;
                default:
                    break;
            }
            exif.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mCamera.startPreview();
    }
};


    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = Math.min(w, h);

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

}
