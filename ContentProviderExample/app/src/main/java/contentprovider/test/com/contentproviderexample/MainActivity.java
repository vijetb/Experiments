package contentprovider.test.com.contentproviderexample;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Loader;
import android.content.res.AssetFileDescriptor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.QuickContactBadge;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;


public class MainActivity extends Activity {

    private static final String TAG = "MAIN_ACTIVITY";

    QuickContactBadge mBadge;


    private Cursor mCursor;
    private ContactsAdapter mAdapter;
    ListView mListView;
    private ContactsAdapter adapter;

    ImageView mImage;
    private Context mContext;

    private Uri mURI = ContactsContract.Contacts.CONTENT_LOOKUP_URI;
    private String[] mProjections = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_FILE_ID, ContactsContract.Contacts.HAS_PHONE_NUMBER};
    private String mSelectionClause = ContactsContract.Contacts.HAS_PHONE_NUMBER + "== 1";
    private String[] mSelectionArgs = null;
    private String mSortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

    String lookupKey = "('1939r1611-4D2F4B4D','1939r1385-4B3527454F49.3789r1386-4B35273527454F4916')";
    String lookupId = "(1934,1708,1505,1521)";
    private final Uri mUriContactsTable = ContactsContract.Contacts.CONTENT_URI;
    private final String[] mProjectionsContactsTable = {ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
            ContactsContract.Contacts.HAS_PHONE_NUMBER, ContactsContract.Contacts.LOOKUP_KEY};
    //private final String mSelectionContactsTable = ContactsContract.Contacts.HAS_PHONE_NUMBER + "== 1" + " AND " + ContactsContract.Contacts.LOOKUP_KEY +
    private final String mSelectionContactsTable = ContactsContract.Contacts.HAS_PHONE_NUMBER + "== 1" + " AND " + ContactsContract.Contacts._ID +
            " IN " + lookupId;
    private final String[] mSelectionArgsContactsTable = null;
    private final String mSortOrderContactsTable = ContactsContract.Contacts.DISPLAY_NAME + " ASC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        init1();
        init2();
    }

    private void init1() {
        //mCursor = getContentResolver().query(mURI,mProjections,mSelectionClause,mSelectionArgs, null);
        mCursor = getContentResolver().query(mUriContactsTable, mProjectionsContactsTable, mSelectionContactsTable, mSelectionArgsContactsTable, mSortOrderContactsTable);

        Log.d(TAG, "RES: " + mUriContactsTable.getPath());
        Log.d(TAG, "TOTAL-CONTACTS: " + mCursor.getCount());
        while (mCursor.moveToNext()) {
            Log.d(TAG, "Id->" + mCursor.getString(0));
            Log.d(TAG, "Name->" + mCursor.getString(1));
            Log.d(TAG, "LookUP->" + mCursor.getString(4));
            Log.d(TAG, "***********************************");
        }

//

        //getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_LOOKUP_URI, true, new MyObserver(null));

//        String lookupkey1 = "1939r1611-4D2F4B4D";
//        Uri lookupUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupkey1);
//        Uri res = ContactsContract.Contacts.lookupContact(getContentResolver(), lookupUri);
//
//        Log.d(TAG, "NEW URI: " + res.getPath());
//
//        String[] projection = new String[] {ContactsContract.Contacts.DISPLAY_NAME};
//       Cursor mCursor2 = getContentResolver().query(res,projection,null,null, null);
//        mCursor2.moveToNext();
//
//        Log.d(TAG,"<- NEW NAME ->"+mCursor2.getString(0));

//        mCursor2.moveToNext();
//
//        Log.d(TAG,"<- NEW NAME ->"+mCursor2.getString(0));
        //res.getQuery();


    }


    public void init2() {
        HashSet<Integer> setData = new HashSet<Integer>();
        setData.add(1234);
        setData.add(1789);
        setData.add(1280);
        setData.add(1111);
        Log.d(TAG,"DATA->->->->->");
        Log.d(TAG,"DATA->" + setData.toString());
    }
}