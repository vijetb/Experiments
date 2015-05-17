package com.vijet.abetme;

import android.app.ActionBar;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vijet.abetme.data.IShareContactsCursor;
import com.vijet.abetme.fragmentcontacts.ContactsFragment;
import com.vijet.abetme.fragmentservice.ServiceFragment;
import com.vijet.abetme.fragmentsmenu.HelpFragment;
import com.vijet.abetme.views.SlidingTabLayout;


public class MainActivity extends FragmentActivity implements IShareContactsCursor {
    private final String TAG = "MainActivity";
    /**
     * Loader Id for LoadManager
     */
    private final int CONTACTS_LOADER_ID = 11;
    /**
     * Pager to setup the tabs
     */
    private ViewPager mPager;
    /**
     * Adapter to setup the tabs
     */
    private FragmentStripAdapter mFragmentStripAdapter;
    /**
     * ActionBar to setup the Tabs
     */
    private ActionBar mActionBar;
    /**
     * Cursor used by Contacts Fragment!
     */
    private Cursor mCursorContacts;
    /**
     * Action mode!
     */
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_main);
        loadContacts();
        init();
    }

    /**
     * This method is called only once and it sets up the UI for all the widgets for layout.
     * It sets up the pager title.
     */
    private void init(){
        mPager = (ViewPager)findViewById(R.id.viewPager);
        mFragmentStripAdapter = new FragmentStripAdapter(getSupportFragmentManager());
        mPager.setAdapter(mFragmentStripAdapter);
        mPager.setOnPageChangeListener(viewPagerChangeListener);

        mActionBar = getActionBar();

        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = mActionBar.newTab();
        tab1.setText(R.string.tab_1);
        tab1.setTabListener(actionBarTabListener);

        ActionBar.Tab tab2 = mActionBar.newTab();
        tab2.setText(R.string.tab_2);
        tab2.setTabListener(actionBarTabListener);

        ActionBar.Tab tab3 = mActionBar.newTab();
        tab3.setText(R.string.tab_3);
        tab3.setTabListener(actionBarTabListener);

        mActionBar.addTab(tab1);
        mActionBar.addTab(tab2);
        mActionBar.addTab(tab3);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.actionBarButtonHelp:
                Log.d(TAG, "HElp");
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(new HelpFragment(),"helpFragment")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // if you need transition
                        .addToBackStack("help")
                        .commit();
                break;
            case R.id.actionBarButtonAboutUs:
                Log.d(TAG, "AboutUs");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method loads the contacts from the loadmanager.
     */
    private void loadContacts(){
        getLoaderManager().initLoader(CONTACTS_LOADER_ID,null,loaderCallback);
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
            return new CursorLoader(MainActivity.this,mUriContactsTable,mProjectionsContactsTable,mSelectionContactsTable,mSelectionArgsContactsTable,
                    mSortOrderContactsTable);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.d(TAG,"inside onloadFinished");
            mCursorContacts = data;
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            Log.d(TAG,"inside onloaderReset");

        }
    };

    @Override
    public Cursor getCursor() {
        return mCursorContacts;
    }

    @Override
    public void setActionMode(ActionMode actionMode) {
        this.mActionMode = actionMode;
    }

    /**
     * Listner for Pager when the page is swiped among Fragments
     */
    ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {
        }

        @Override
        public void onPageSelected(int i) {
            mActionBar.setSelectedNavigationItem(i);
            if(mActionMode != null) {
                mActionMode.finish();
                mActionMode = null;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };

    /**
     * Listener for Action Bar tabs (SERVICE/ ACTIVE/ CONTACTS)
     */
    ActionBar.TabListener actionBarTabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
            mPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {}

        @Override
        public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {}
    };
}

