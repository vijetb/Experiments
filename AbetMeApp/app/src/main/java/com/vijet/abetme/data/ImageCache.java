package com.vijet.abetme.data;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.LruCache;

/**
 * This class holds Bitmap Caches of the contacts
 */
public class ImageCache {
    /**
     * Logger for this class
     */
    private static final String TAG = "ImageCache";
    /**
     * LruCache for stroing Images
     */
    private LruCache<String,Bitmap> mImageCache;


    private ImageCache(){
        // 4 MB Is default size of the Cache
        int MAX_CACHE_SIZE = 4 * 1024 * 1024;
        int cacheSize = MAX_CACHE_SIZE;

        if(Runtime.getRuntime().freeMemory() < cacheSize){
            cacheSize = (int)Runtime.getRuntime().freeMemory()/8;
        }

        mImageCache = new LruCache<String, Bitmap>(cacheSize){
              protected int sizeOf(String key, Bitmap value){
                    return value.getByteCount();
              }
        };
    }

    /**
     * This method is used to store the image in the Cache. It will be called in AsyncTask while loading
     * the contact image
     * @param id - the id of the contact
     * @param contactBitmap - contact bitmap
     */
    public void addBitmapToCache(final String id, final Bitmap contactBitmap){
        Log.d(TAG, "id->" + id);
        Log.d(TAG, "bitmap->" + contactBitmap);
        if(null == id || null == contactBitmap || null == mImageCache ){
            Log.i(TAG,"Wrong input to store Cache");
            return;
        }
        mImageCache.put(id,contactBitmap);
    }

    /**
     * This method is used to load image from the cache
     * @param id - id of the contact`
     * @return bitmap of the contact if exists, false otherwise
     */
    public Bitmap loadImageFromCache(final String id){
        return ((null == id)?null:mImageCache.get(id));
    }


    /**
     * Factory method that has to be used by all the fragments to access ImageCache
     * @return ImageCache
     */
    public static ImageCache getInstance(final FragmentManager fragmentManager){
        DummyFragment dummyFragment = (DummyFragment) fragmentManager.findFragmentByTag(TAG);
        if(null == dummyFragment){
            dummyFragment = new DummyFragment();
            fragmentManager.beginTransaction().add(dummyFragment,TAG).commitAllowingStateLoss();
            // Update the Image Cache
            ImageCache imageCache = new ImageCache();
            dummyFragment.setObject(imageCache);
        }
        return (ImageCache)dummyFragment.getmObject();
    }

    /**
     * Non UI Fragment that is used to keep Single Instance of imageCache
     */
    public static class DummyFragment extends Fragment{
        private Object mObject;

        public DummyFragment(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        public void setObject(Object object){
            mObject = object;
        }

        public Object getmObject(){
            return mObject;
        }
    }

}
