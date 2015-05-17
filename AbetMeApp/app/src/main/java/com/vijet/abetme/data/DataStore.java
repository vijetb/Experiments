package com.vijet.abetme.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.vijet.abetme.util.KeysUtils;

import java.lang.reflect.Method;

/**
 * Created by BMH1014669 on 30/10/14.
 */
public final class DataStore {

    final

    private Context appContext;
    private SharedPreferences preferences;

    public DataStore(Context context){
        this.appContext = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(this.appContext.getApplicationContext());
    }

    public final Object getDataFromStore(final Integer KEY){
        Object returnData = null;

        switch(KEY){
            case KeysUtils.SERVICE_ACTIVATE_KEY: returnData = preferences.getBoolean(KEY.toString(),false);break;
            case KeysUtils.PHONE_MODE_SELECTION_KEY: returnData = preferences.getInt(KEY.toString(),0);break;// 0-> DEFAULT, 1->SILENT, 2->VIBRATE
            case KeysUtils.SMS_SERVICE_ACTIVE_KEY: returnData = preferences.getBoolean(KEY.toString(),false);break;
            case KeysUtils.SMS_SERVICE_TEXT_ACTIVE_KEY: returnData = preferences.getString(KEY.toString(),"");break;
            case KeysUtils.WA_SERVICE_ACTIVE_KEY: returnData = preferences.getBoolean(KEY.toString(),false);break;
            case KeysUtils.WA_SERVICE_TEXT_ACTIVE_KEY: returnData = preferences.getString(KEY.toString(),"");break;
            case KeysUtils.UNKNOWN_SERVICE_ACTIVATE_KEY: returnData = preferences.getBoolean(KEY.toString(),false);break;
            case KeysUtils.ACTIVE_CONTACTS_KEY: returnData = preferences.getString(KEY.toString(),"");break;
            case KeysUtils.ACTIVE_IS_ALL_CONTACTS_SELECTED: returnData = preferences.getBoolean(KEY.toString(),false);break;
        }

        return returnData;
    }

    public final void storeData(final Integer KEY, final Object value){
        SharedPreferences.Editor editor = preferences.edit();
        switch(KEY){
            case KeysUtils.SERVICE_ACTIVATE_KEY: editor.putBoolean(KEY.toString(),(Boolean)value);break;
            case KeysUtils.PHONE_MODE_SELECTION_KEY: editor.putInt(KEY.toString(),(Integer)value);break;// 0-> DEFAULT, 1->SILENT, 2->VIBRATE
            case KeysUtils.SMS_SERVICE_ACTIVE_KEY: editor.putBoolean(KEY.toString(),(Boolean)value);break;
            case KeysUtils.SMS_SERVICE_TEXT_ACTIVE_KEY: editor.putString(KEY.toString(),(String)value);break;
            case KeysUtils.WA_SERVICE_ACTIVE_KEY: editor.putBoolean(KEY.toString(),(Boolean)value);break;
            case KeysUtils.WA_SERVICE_TEXT_ACTIVE_KEY: editor.putString(KEY.toString(),(String)value);break;
            case KeysUtils.UNKNOWN_SERVICE_ACTIVATE_KEY: editor.putBoolean(KEY.toString(),(Boolean)value);break;
            case KeysUtils.ACTIVE_CONTACTS_KEY: editor.putString(KEY.toString(),(String)value);break;
            case KeysUtils.ACTIVE_IS_ALL_CONTACTS_SELECTED: editor.putBoolean(KEY.toString(),(Boolean)value);break;
        }
        editor.commit();
    }


}
