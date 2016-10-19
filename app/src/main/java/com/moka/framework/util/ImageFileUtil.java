package com.moka.framework.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;


public class ImageFileUtil {

	public static final String TEST = "test ";

	private static final String IMAGE_DIRECTORY = "/image/";
	private static final String FILE_FORMAT_JPG = "jpg";

	private static ImageFileUtil instance;
	private Context context;

	private String innerParentPath;
	private String externalParentPath;

	private ImageFileUtil(Context context) {

		innerParentPath = context.getFilesDir().getPath() + IMAGE_DIRECTORY;
		externalParentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Moka/";
	}

	public String getInnerParentPath() {

		return innerParentPath;
	}

	public String getExternalParentPath() {

		return externalParentPath;
	}

	public String getParentPathFrom(ImageLocation imageLocation) {

		switch ( imageLocation ) {

			case INNER:
				return innerParentPath;
			case EXTERNAL:
				return externalParentPath;
			default:
				return externalParentPath;
		}
	}

	/**
	 */

	public boolean isExistInInner(String fileName) {
		File file = getFileOfInner(fileName);
		return file.exists();
	}

	/**
	 * About Inner
	 */

	public File getFileOfInner(String fileName) {

		if ( null != fileName )
			return new File(getDirectoryOfInner(), fileName);
		else
			return new File(getDirectoryOfInner(), "");
	}

	public Uri getUriOfInner(String fileName) {

		return Uri.fromFile(getFileOfInner(fileName));
	}

	public String getInnerFilePath(String fileName) {

		if ( !TextUtils.isEmpty(fileName) )
			return innerParentPath + fileName;
		else
			return null;
	}

	public File getDirectoryOfInner() {

		File directory = new File(getInnerParentPath());

		if ( !directory.exists() )
			directory.mkdirs();

		return directory;
	}

	/**
	 * About External
	 */

	public File getFileOfExternal(String fileName) {

		if ( null != fileName )
			return new File(getDirectoryOfExternal(), fileName);
		else
			return new File(getDirectoryOfExternal(), "");
	}

	public String getExternalFilePath(String fileName) {

		if ( !TextUtils.isEmpty(fileName) )
			return externalParentPath + fileName;
		else
			return null;
	}

	public File getDirectoryOfExternal() {

		File directory = new File(getExternalParentPath());

		if ( !directory.exists() )
			directory.mkdirs();

		return directory;
	}

	/**
	 * Factory and setter Method
	 */

	public void setContext(Context context) {

		this.context = context;
	}

	public static ImageFileUtil from(Context context) {

		if ( null == instance )
			instance = new ImageFileUtil(context);

		instance.setContext(context);
		return instance;
	}

	/**
	 * Util Method
	 */

	public static String generateFileNameToShow() {

		String str = DateUtil.formatDateToString(new Date(), "yyyyMMdd_HHmmss");
		return "moca_" + str + ".jpg";
	}

	public static String generateFileName() {

		long timestamp = DateUtil.getTimestampInSecond();
		UUID uuid = UUID.randomUUID();

		return String.format("%d-%s.%s", timestamp, uuid, FILE_FORMAT_JPG);
	}

	public static String getImageName(String imagePath) {

		return imagePath.substring(imagePath.lastIndexOf("/") + 1);
	}

	public static int getBitmapOfWidth(String filePath) {

		Bitmap bitmap = getBitmapByRotate(filePath);

		int width = 0;
		if ( null != bitmap ) {

			width = bitmap.getWidth();
			bitmap.recycle();
		}

		return width;
	}

	public static int getBitmapOfHeight(String filePath) {

		Bitmap bitmap = getBitmapByRotate(filePath);

		int height = 0;
		if ( null != bitmap ) {

			height = bitmap.getHeight();
			bitmap.recycle();
		}

		return height;
	}

	/**
	 * About Rotate
	 */

	public static Bitmap getBitmapByRotate(String imagePath) {

		ExifInterface exif = null;
		try {

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			Bitmap image = BitmapFactory.decodeFile(imagePath, options);

			exif = new ExifInterface(imagePath);
			int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
			int exifDegree = exifOrientationToDegrees(exifOrientation);

			return rotate(image, exifDegree);
		}
		catch ( IOException e ) {

			Log.wtf("getBitmapByRotate", "here?");
			e.printStackTrace();
			return null;
		}
	}

	private static int exifOrientationToDegrees(int exifOrientation) {

		if ( exifOrientation == ExifInterface.ORIENTATION_ROTATE_90 )
			return 90;
		else if ( exifOrientation == ExifInterface.ORIENTATION_ROTATE_180 )
			return 180;
		else if ( exifOrientation == ExifInterface.ORIENTATION_ROTATE_270 )
			return 270;
		return 0;
	}

	private static Bitmap rotate(Bitmap bitmap, int degrees) {

		if ( degrees != 0 && bitmap != null ) {

			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth(), (float) bitmap.getHeight());

			try {

				Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);

				if ( bitmap != converted ) {

					bitmap.recycle();
					bitmap = converted;
				}
			}
			catch ( OutOfMemoryError ex ) {
				// 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
				Log.wtf("rotate", "here?");
			}
		}
		return bitmap;
	}

}
