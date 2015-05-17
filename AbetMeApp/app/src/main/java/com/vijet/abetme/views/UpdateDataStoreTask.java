package com.vijet.abetme.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.vijet.abetme.data.DataStore;
import com.vijet.abetme.util.KeysUtils;

/**
 * Created by BMH1014669 on 09/11/14.
 */
public class UpdateDataStoreTask extends AsyncTask<String,Void,String> {
    private Context mContext = null;
    private ProgressDialog mProgressDialog = null;

    public UpdateDataStoreTask(Context context){
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Updating Contacts");
        mProgressDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        DataStore tempDataStore = new DataStore(mContext);
        //String tempDataStore.getDataFromStore(KeysUtils.ACTIVE_CONTACTS_KEY);
      return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
