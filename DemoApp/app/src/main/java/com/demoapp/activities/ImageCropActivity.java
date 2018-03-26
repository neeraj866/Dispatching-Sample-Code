package com.demoapp.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demoapp.R;
import com.demoapp.interfaces.TakeImageListener;
import com.demoapp.utilities.Utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.togoto.imagezoomcrop.cropoverlay.CropOverlayView;
import io.togoto.imagezoomcrop.photoview.IGetImageBounds;
import io.togoto.imagezoomcrop.photoview.PhotoView;


public class ImageCropActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "ImageCropActivity";

    private PhotoView mImageView;
    private CropOverlayView mCropOverlayView;

    private ContentResolver mContentResolver;

    private static TakeImageListener setImageListener;

    private final Bitmap.CompressFormat mOutputFormat = Bitmap.CompressFormat.JPEG;

    //Temp file to save cropped image
    private String mImagePath;
    private Uri mSaveUri = null;
    private Uri mImageUri = null;


    //File for capturing camera images
    private File mFileTemp;
    public static final int REQUEST_CODE_PICK_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final String ERROR_MSG = "error_msg";
    public static final String ERROR = "error";
    private String ts;

    public void callBack(TakeImageListener setImageListener) {
        this.setImageListener = setImageListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_crop);
        mContentResolver = getContentResolver();
        mImageView = (PhotoView) findViewById(R.id.iv_photo);
        mCropOverlayView = (CropOverlayView) findViewById(R.id.crop_overlay);
        ImageView mImSubmit = (ImageView) findViewById(R.id.btn_done);
        ImageView mImCancel = (ImageView) findViewById(R.id.cancel);
        mImSubmit.setOnClickListener(this);
        mImCancel.setOnClickListener(this);
        Long tsLong = System.currentTimeMillis() / 1000;
        ts = tsLong.toString();
        mImageView.setImageBoundsListener(new IGetImageBounds() {
            @Override
            public Rect getImageBounds() {
                return mCropOverlayView.getImageBounds();
            }
        });

        createTempFile();
        if (savedInstanceState == null || !savedInstanceState.getBoolean("restoreState")) {
            String action = getIntent().getStringExtra("ACTION");
            if (null != action) {
                switch (action) {
                    case "action-camera":
                        getIntent().removeExtra("ACTION");
                        takePic();
                        return;
                    case "action-gallery":
                        getIntent().removeExtra("ACTION");
                        pickImage();
                        return;
                }
            }
        }
        mImagePath = mFileTemp.getPath();
        mSaveUri = Utils.getImageUri(mImagePath);
        mImageUri = Utils.getImageUri(mImagePath);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init() {
        Log.e("orientation", getCameraPhotoOrientation(this, mImageUri, mFileTemp.getAbsolutePath()) + "");
        Bitmap bitmap = getBitmap(mImageUri);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);

        float minScale = mImageView.setMinimumScaleToFit(drawable);
        mImageView.setMaximumScale(minScale * 3);
        mImageView.setMediumScale(minScale * 2);
        mImageView.setScale(minScale);
        mImageView.setImageDrawable(drawable);


    }

    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    private void saveUploadCroppedImage() {
        boolean saved = saveOutput();
        if (saved) {
            //USUALLY Upload image to server here
            Intent intent = new Intent();
            intent.putExtra("image-path", mImagePath);
            setResult(RESULT_OK, intent);
            setImageListener.getImage(mImagePath);
            finish();
        } else {
        }
    }

    private void createTempFile() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), getResources().getString(R.string.app_name) + ts + ".jpg");
        } else {
            mFileTemp = new File(getFilesDir(), getResources().getString(R.string.app_name) + ts + ".jpg");
        }
    }


    private void takePic() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);
            } else {
                /*
                 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
	        	 */
                mImageCaptureUri = Uri.parse("AllianceGoCrop");
            }
            // takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            //takePictureIntent.putExtra("return-data", true);
            // takePictureIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PICTURE);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Can't take picture", e);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("restoreState", true);
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        try {
            startActivityForResult(intent, REQUEST_CODE_PICK_GALLERY);
        } catch (ActivityNotFoundException e) {
        }
    }

    private static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        createTempFile();
        if (requestCode == REQUEST_CODE_TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap capturedBitmap = (Bitmap) result.getExtras().get("data");
                    Log.e("realPath", ":: " + getRealPathFromURI(getImageUri(getApplicationContext(), capturedBitmap)));

                    FileOutputStream fOutputStream = new FileOutputStream(mFileTemp);

                    assert capturedBitmap != null;
                    capturedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOutputStream);

                    fOutputStream.flush();
                    fOutputStream.close();

                    MediaStore.Images.Media.insertImage(getContentResolver(), mFileTemp.getAbsolutePath(), mFileTemp.getName(), mFileTemp.getName());
                } catch (IOException e) {

                    Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                mImagePath = mFileTemp.getPath();
                setImageListener.getImage(mImagePath);
                finish();
                mSaveUri = Utils.getImageUri(mImagePath);
                mImageUri = Utils.getImageUri(mImagePath);
                init();
            } else if (resultCode == RESULT_CANCELED) {
                userCancelled();
                return;
            } else {
                errored("Error while opening the image file. Please try again.");
                return;
            }

        } else if (requestCode == REQUEST_CODE_PICK_GALLERY) {
            if (resultCode == RESULT_CANCELED) {
                userCancelled();
                return;
            } else if (resultCode == RESULT_OK) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(result.getData()); // Got the bitmap .. Copy it to the temp file for cropping
                    FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    Uri selectedImageUri = result.getData();
                    String s = getRealPathFromURI(selectedImageUri);
                    Log.e("realPath", ":: " + s);
                    mImagePath = mFileTemp.getPath();
                    setImageListener.getImage(mImagePath);
                    finish();
                    mSaveUri = Utils.getImageUri(mImagePath);
                    mImageUri = Utils.getImageUri(mImagePath);
                    init();
                } catch (Exception e) {
                    errored("Error while opening the image file. Please try again.");
                    return;
                }
            } else {
                errored("Error while opening the image file. Please try again.");
                return;
            }

        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }


    private Bitmap getBitmap(Uri uri) {
        InputStream in = null;
        Bitmap returnedBitmap = null;
        try {
            in = mContentResolver.openInputStream(uri);
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            if (in != null) {
                in.close();
            }
            int scale = 1;
            int IMAGE_MAX_SIZE = 1024;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            in = mContentResolver.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in, null, o2);
            if (in != null) {
                in.close();
            }

            //First check
            ExifInterface ei = new ExifInterface(uri.getPath());
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    returnedBitmap = rotateImage(bitmap, 90);
                    //Free up the memory
                    bitmap.recycle();
                    bitmap = null;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    returnedBitmap = rotateImage(bitmap, 180);
                    //Free up the memory
                    bitmap.recycle();
                    bitmap = null;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    returnedBitmap = rotateImage(bitmap, 270);
                    //Free up the memory
                    bitmap.recycle();
                    bitmap = null;
                    break;
                default:
                    returnedBitmap = bitmap;
            }
            return returnedBitmap;
        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private boolean saveOutput() {
        Bitmap croppedImage = mImageView.getCroppedImage();
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = mContentResolver.openOutputStream(mSaveUri);
                if (outputStream != null) {
                    croppedImage.compress(mOutputFormat, 100, outputStream);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            } finally {
                closeSilently(outputStream);
            }
        } else {
            Log.e(TAG, "not defined image url");
            return false;
        }
        croppedImage.recycle();
        return true;
    }

    public void closeSilently(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (Throwable t) {
            // do nothing
        }
    }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void userCancelled() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void errored(String msg) {
        Intent intent = new Intent();
        intent.putExtra(ERROR, true);
        if (msg != null) {
            intent.putExtra(ERROR_MSG, msg);
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_done:
                saveUploadCroppedImage();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }
}