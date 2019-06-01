package com.example.rabia.androidgestures;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


//public class MainActivity extends Activity implements
//        GestureDetector.OnGestureListener,
//        GestureDetector.OnDoubleTapListener{
//
//    private static final String DEBUG_TAG = "Gestures";
//    private GestureDetectorCompat mDetector;
//
//    // Called when the activity is first created.
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        // Instantiate the gesture detector with the
//        // application context and an implementation of
//        // GestureDetector.OnGestureListener
//        mDetector = new GestureDetectorCompat(this,this);
//        // Set the gesture detector as the double tap
//        // listener.
//        mDetector.setOnDoubleTapListener(this);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        if (this.mDetector.onTouchEvent(event)) {
//            return true;
//        }
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean onDown(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onDown"+event.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public boolean onFling(MotionEvent event1, MotionEvent event2,
//                           float velocityX, float velocityY) {
//        Toast.makeText(getApplicationContext(),"onFling"+event1.toString()+event2.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onLongPress"+event.toString(),Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
//                            float distanceY) {
//        Toast.makeText(getApplicationContext(),"onScroll"+event1.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onShowPress"+event.toString(),Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onSingleTapUp"+event.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public boolean onDoubleTap(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onDoubleTap"+event.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public boolean onDoubleTapEvent(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onDoubleTapEvent"+event.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"onDown"+event.toString(),Toast.LENGTH_LONG).show();
//        return true;
//    }
//}

public class MainActivity extends Activity {
//    static final int REQUEST_IMAGE_CAPTURE = 1;
File photoFile = null;
    ImageView imageView;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
    }

    public void takeImage(View view){
        dispatchTakePictureIntent();
    }
//    private void dispatchTakePictureIntent() {
////        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
////            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
////        }
//    }


//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.example.android.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        Toast.makeText(getApplicationContext(),"path "+imageFileName,Toast.LENGTH_LONG).show();
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Toast.makeText(getApplicationContext(),"path "+storageDir,Toast.LENGTH_LONG).show();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
//            Toast.makeText(getApplicationContext(),"cc"+data,Toast.LENGTH_LONG).show();
////            Bundle extras = data.getExtras();

            Uri fileUri = Uri.fromFile(photoFile);
            Toast.makeText(getApplicationContext(),"cc"+fileUri,Toast.LENGTH_LONG).show();
//            Uri image=data.getData();
////            Bitmap imageBitmap = (Bitmap) extras.get("data");
////            imageView.setImageBitmap(image);
            galleryAddPic();
            imageView.setImageURI(fileUri);
        }
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Toast.makeText(getApplicationContext(),"Path is "+f,Toast.LENGTH_LONG).show();
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action=MotionEventCompat.getActionMasked(event);
//        switch (action){
//            case (MotionEvent.ACTION_DOWN) :
//                Toast.makeText(getApplicationContext(),"Down",Toast.LENGTH_LONG).show();
//                return true;
//            case (MotionEvent.ACTION_MOVE) :
//                Toast.makeText(getApplicationContext(),"MOve",Toast.LENGTH_LONG).show();
//                return true;
//            case (MotionEvent.ACTION_UP) :
//                Toast.makeText(getApplicationContext(),"up",Toast.LENGTH_LONG).show();
//                return true;
//            case (MotionEvent.ACTION_CANCEL) :
//                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();
//                return true;
//            case (MotionEvent.ACTION_OUTSIDE) :
//                Toast.makeText(getApplicationContext(),"Outside",Toast.LENGTH_LONG).show();
//                return true;
//            default :
//                return super.onTouchEvent(event);
//        }
//    }

}
