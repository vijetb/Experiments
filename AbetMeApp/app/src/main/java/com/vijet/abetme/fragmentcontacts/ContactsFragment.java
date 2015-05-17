package com.vijet.abetme.fragmentcontacts;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.vijet.abetme.MainActivity;
import com.vijet.abetme.R;
import com.vijet.abetme.data.DataStore;
import com.vijet.abetme.data.ImageCache;
import com.vijet.abetme.util.Constants;
import com.vijet.abetme.util.KeysUtils;
import com.vijet.abetme.util.StringUtility;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;


public class ContactsFragment extends Fragment{
    private static final String TAG = "ServiceFragment";
    /**
     * Loader Id for LoadManager
     */
    private final int CONTACTS_LOADER_ID = 11;

    private ListView mListViewContactsList = null;

    private Cursor mCursorContacts = null;
    private ContactsAdapter adapter = null;

    /**
     * Store checked position
     */
    private boolean[] checkedStatus = null;

    private ActionMode mActionMode = null;
    /**
     * Stores the LOOKUP_KEY of the selected contacts.
     */
    private HashSet<String> mSelectedContacts = new HashSet<String>();

    private Bitmap mLoadingBitmap = null;
    private Resources mResources = null;
    private ImageCache mImageCache = null;
    private DataStore mDataStore = null;


    public ContactsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataStore = new DataStore(getActivity().getApplicationContext());
        mCursorContacts =  ((MainActivity)getActivity()).getCursor();
        //TODO : Change this to conditional statement
        if(null == mCursorContacts) {
            getActivity().getLoaderManager().initLoader(CONTACTS_LOADER_ID, null, loaderCallback);
        }else{
            checkedStatus = new boolean[mCursorContacts.getCount()];
        }
        mResources = getResources();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts_tab, container, false);
        initView(rootView);
        return rootView;
    }

    /**
     * This method captures all the UI ids and set the listeners
     */
    private void initView(final View rootView){
        mListViewContactsList = (ListView) rootView.findViewById(R.id.contactsList);
        adapter = new ContactsAdapter(ContactsFragment.this.getActivity().getApplicationContext(),mCursorContacts,checkedStatus);
        mListViewContactsList.setAdapter(adapter);
        mListViewContactsList.setMultiChoiceModeListener(multichoiceListener);
    }

    AbsListView.MultiChoiceModeListener multichoiceListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            Log.d(TAG, "onItemCheckedStateChanged");
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.d(TAG, "onCreateActionMode");
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_context_menu_contacts, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.d(TAG, "onPrepareActionMode");
            if(mSelectedContacts.isEmpty() && mActionMode != null){
                    mActionMode.finish();
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d(TAG, "onActionItemClicked");

            switch (item.getItemId()) {
                case R.id.menu1://ADD
                    Arrays.fill(checkedStatus,Boolean.FALSE);
                    mActionMode.finish();
                    adapter.notifyDataSetChanged();
                    if(mSelectedContacts.contains(Constants.ALL_CONTACTS_SELECTED)){
                        mDataStore.storeData(KeysUtils.ACTIVE_IS_ALL_CONTACTS_SELECTED,true);
                    }
                    new StoreAndLoadActiveContactsTask().execute(mSelectedContacts);
                    printToastMsg("Contacts added to Active lists!");
                    return true;
                case R.id.menu2://SELECT ALL
                    Log.d(TAG, "select all");
                    Arrays.fill(checkedStatus,Boolean.TRUE);
                    mSelectedContacts.clear();
                    mSelectedContacts.add(Constants.ALL_CONTACTS_SELECTED);
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.menu3:
                    Log.d(TAG, "cancel");
                    Arrays.fill(checkedStatus,Boolean.FALSE);
                    mActionMode.finish();
                    adapter.notifyDataSetChanged();

            }
            if(mSelectedContacts.isEmpty())
                mActionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.d(TAG, "onDestroyActionMode");
            mActionMode = null;
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSelectedContacts.clear();
        Arrays.fill(checkedStatus,Boolean.FALSE);
    }

    /**
     * Loader Callback, after loading give back the Cursor to the fragment
     */
    private LoaderManager.LoaderCallbacks<Cursor> loaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final Uri mUriContactsTable = ContactsContract.Contacts.CONTENT_URI;
        private final String[] mProjectionsContactsTable = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,ContactsContract.Contacts.LOOKUP_KEY};
        private final String mSelectionContactsTable = ContactsContract.Contacts.HAS_PHONE_NUMBER + "== 1";
        private final String[] mSelectionArgsContactsTable = null;
        private final String mSortOrderContactsTable = ContactsContract.Contacts.DISPLAY_NAME +" ASC";

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Log.d(TAG,"inside onCreateLoader");
            return new CursorLoader(ContactsFragment.this.getActivity(),mUriContactsTable,mProjectionsContactsTable,mSelectionContactsTable,mSelectionArgsContactsTable,
                    mSortOrderContactsTable);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.d(TAG,"inside onloadFinished");
            if(null != data && null == mCursorContacts){
                mCursorContacts = data;
                checkedStatus = new boolean[mCursorContacts.getCount()];
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            Log.d(TAG,"inside onloaderReset");
        }
    };

    /**
     * Method that prints the Toast Message
     * @param toastMsg
     */
    private void printToastMsg(final String toastMsg){
        Toast.makeText(ContactsFragment.this.getActivity(),toastMsg,Toast.LENGTH_SHORT).show();
    }

     /**
     * Adapter to show the contacts
     */
    private class ContactsAdapter extends CursorAdapter {
        private final String TAG = "ContactsAdapter";
        private LayoutInflater mLayoutInflater;
        private boolean[] selectedData = null;

        public ContactsAdapter(Context context, Cursor c, boolean[] selectedData) {
            super(context, c, true);
            this.selectedData = selectedData;
            mLayoutInflater = LayoutInflater.from(context);
            mImageCache = ImageCache.getInstance(getFragmentManager());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }

        @Override
        public View newView(final Context context, final Cursor cursor,final  ViewGroup parent) {
            return mLayoutInflater.inflate(R.layout.list_item_contact,null);
        }

        @Override
        public void bindView(View view, Context context, final Cursor cursor) {

            final String contactId = String.valueOf(cursor.getInt(0));
            final String contactName = cursor.getString(1);
            final String contactPicUri = cursor.getString(2);
            final String lookUpKey = cursor.getString(4);

                Log.d(TAG,"Id->"+contactId);
                Log.d(TAG,"Name->"+contactName);
                Log.d(TAG,"LookUP->"+lookUpKey);
                Log.d(TAG,"***********************************");




            CheckedTextView mCheckedTextViewContact = (CheckedTextView)view.findViewById(R.id.contactName);
            mCheckedTextViewContact.setText(contactName);
            ImageView mBadgeContactPicture = (ImageView)view.findViewById(R.id.contactPicture);
            mCheckedTextViewContact.setTag(cursor.getPosition());
            mCheckedTextViewContact.setChecked((null==checkedStatus)?false:checkedStatus[cursor.getPosition()]);

            mBadgeContactPicture.setImageResource(R.drawable.ic_launcher);
            if(null != contactPicUri){
                // load the images and try to set the Bitmap
                Bitmap contactImage = mImageCache.loadImageFromCache(contactId);
                if(null == contactImage){
                    Log.d(TAG,"Start the worker thread");
                    if(cancelPotentialWork(contactId,mBadgeContactPicture));
                    {
                        final BitmapWorkerTask task = new BitmapWorkerTask(mBadgeContactPicture);
                        final AsyncDrawable asyncDrawable = new AsyncDrawable(mResources, mLoadingBitmap, task);
                        mBadgeContactPicture.setImageDrawable(asyncDrawable);
                        task.execute(contactId);
                    }
                }else{
                    mBadgeContactPicture.setImageBitmap(contactImage);
                }
            }
            mCheckedTextViewContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                CheckedTextView touchedView = (CheckedTextView) v;
                touchedView.toggle();
                checkedStatus[(Integer) touchedView.getTag()] = touchedView.isChecked();


                if(touchedView.isChecked()){
                    mSelectedContacts.add(contactId);
                }else{
                    mSelectedContacts.remove(contactId);
                }
                Log.d(TAG,"No Of contacts: " + mSelectedContacts.toString());
                Log.d(TAG, "Count of selected Contacts:" + mSelectedContacts.size());
                if (mActionMode == null) {
                    mActionMode = getActivity().startActionMode(multichoiceListener);
                    ((MainActivity)getActivity()).setActionMode(mActionMode);
                    return;
                }
                mActionMode.invalidate();
            }
        });
            mCheckedTextViewContact.setLongClickable(true);
            mCheckedTextViewContact.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d(TAG, "LongPress");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, contactId);
                    intent.setData(uri);
                    ((MainActivity)getActivity()).startActivity(intent);
                     return true;
                }
            });

        }

    }


    /**
     * @param imageView Any imageView
     * @return Retrieve the currently active work task (if any) associated with this imageView.
     * null if there is no such task.
     */
    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    /**
     * Returns true if the current work has been canceled or if there was no work in
     * progress on this image view.
     * Returns false if the work in progress deals with the same data. The work is not
     * stopped in that case.
     */
    public static boolean cancelPotentialWork(Object data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final Object bitmapData = bitmapWorkerTask.data;
            if (bitmapData == null || !bitmapData.equals(data)) {
                bitmapWorkerTask.cancel(true);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * A custom Drawable that will be attached to the imageView while the work is in progress.
     * Contains a reference to the actual worker task, so that it can be stopped if a new binding is
     * required, and makes sure that only the last started worker process can bind its result,
     * independently of the finish order.
     */
    private static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }


    private class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap>{
        private Object data;
        private final WeakReference<ImageView> contactImageReference;

        public BitmapWorkerTask(ImageView mImage){
            contactImageReference = new WeakReference<ImageView>(mImage);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        /* WORKER THREAD*/
        @Override
        protected Bitmap doInBackground(String... params) {
            Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.valueOf(params[0]));
            InputStream contactPicInputStream = ContactsContract.Contacts.openContactPhotoInputStream(getActivity().getContentResolver(),uri);
            if(null == contactPicInputStream){
                return null;
            }
            Bitmap loadedBitMap = BitmapFactory.decodeStream(contactPicInputStream);
            if(loadedBitMap != null)
                mImageCache.addBitmapToCache(params[0],loadedBitMap);

            Log.d(TAG, "parasm" + params[0]);
            Log.d(TAG,"loadedBitmap" + loadedBitMap);
            return loadedBitMap;
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (isCancelled()) {
                return;
            }

            BitmapWorkerTask imageTask = getBitmapWorkerTask(contactImageReference.get());

            if(this == imageTask && null != bitmap){
                final ImageView contactImage = contactImageReference.get();
                contactImage.post(new Runnable() {
                    @Override
                    public void run() {
                        contactImage.setImageBitmap(bitmap);
                    }
                });

            }
        }
    }

    private class StoreAndLoadActiveContactsTask extends AsyncTask<HashSet,Void,Void>{

        private ProgressDialog mProgressDialog = null;
        private Context mContext = null;
        private DataStore mDataStore = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mContext = ContactsFragment.this.getActivity().getApplicationContext();
            mDataStore = new DataStore(mContext);
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("Updating Contacts");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(HashSet... params) {
            // TODO: Reduce this in one senctence
            StringBuilder activeContactsString = new StringBuilder((String)mDataStore.getDataFromStore(KeysUtils.ACTIVE_CONTACTS_KEY));
            StringBuilder stringToSaveContacts = new StringBuilder(StringUtility.getActiveContactsAsString(params[0], activeContactsString.toString()));
            mDataStore.storeData(KeysUtils.ACTIVE_CONTACTS_KEY,stringToSaveContacts.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(null != mProgressDialog && mProgressDialog.isShowing()){
                mProgressDialog.cancel();
                mProgressDialog = null;
            }
        }
    }


}
