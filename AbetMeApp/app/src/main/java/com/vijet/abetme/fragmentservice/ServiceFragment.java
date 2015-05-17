package com.vijet.abetme.fragmentservice;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.vijet.abetme.MainActivity;
import com.vijet.abetme.R;
import com.vijet.abetme.data.DataStore;
import com.vijet.abetme.util.KeysUtils;

import org.w3c.dom.Text;

import java.lang.reflect.Method;

public class ServiceFragment extends Fragment {
    private static final String TAG = "ServiceFragment";
    /**
     * Notification Id
     */
    private static final Integer NOTIFICATION_ID = 11;

    //Data store
    private DataStore mDataStore;
    //activateServiceLayout
    private Switch mSwitchService;
    private TextView mTextViewServiceActivatedTag;

    //Phone Modes
    private Spinner mSpinner;

    //SMS Service
    private RelativeLayout mRelativeLayoutSmsService;
    private Switch mSwitchSmsService;

    //SMS EXTENDED Service
    private RelativeLayout mRelativeLayoutSmsExtendedLayout;
    private TextView mTextViewSMSLetterCount;
    private EditText mEditTextSMSText;

    //WA SERVICE
    private RelativeLayout mRelativeLayoutWAService;
    private Switch mSwitchWAService;
    private RelativeLayout mRelativeLayoutWAExtendedLayout;
    private EditText mEditTextWAText;

    // UNKNOWN CONTACTS
    private RelativeLayout mRelativeLayoutUnknownContactsService;
    private Switch mSwitchUnknownContactsService;

    //Input manger
    private InputMethodManager mInputMethodManger;
    public ServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataStore = new DataStore(ServiceFragment.this.getActivity().getApplicationContext());
        View rootView = inflater.inflate(R.layout.fragment_service_tab, container, false);
        initView(rootView);
        return rootView;
    }

    /**
     * This method captures all the UI ids and set the listeners
     */
    private void initView(final View rootView){
        mSwitchService = (Switch) rootView.findViewById(R.id.switch_activate_service);
        mTextViewServiceActivatedTag = (TextView) rootView.findViewById(R.id.tv_activate_status);
        mSwitchService.setOnCheckedChangeListener(activateServiceOrUnknownContactsListener);

        final boolean isServiceActive = (Boolean) mDataStore.getDataFromStore(KeysUtils.SERVICE_ACTIVATE_KEY);
        updateActivateServiceSwitch(isServiceActive);

        // PHONE MODES
        mSpinner = (Spinner) rootView.findViewById(R.id.phone_mode_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),R.array.phone_modes, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Set either default or value got from shared Preferences
        mSpinner.post(new Runnable() {
            @Override
            public void run() {
                mSpinner.setSelection((Integer)mDataStore.getDataFromStore(KeysUtils.PHONE_MODE_SELECTION_KEY));
            }
        });
        mSpinner.setOnItemSelectedListener(phoneModeSelectedListener);
        mSpinner.setAdapter(adapter);

        //SMS SERVICE
        mRelativeLayoutSmsService = (RelativeLayout)rootView.findViewById(R.id.smsLayout);
        mSwitchSmsService = (Switch)rootView.findViewById(R.id.switch_activate_sms_service);
        mRelativeLayoutSmsExtendedLayout = (RelativeLayout)rootView.findViewById(R.id.smsExtendedLayout);
        mTextViewSMSLetterCount = (TextView)rootView.findViewById(R.id.smsLetterCount);
        mEditTextSMSText = (EditText)rootView.findViewById(R.id.smsSavedText);
        mSwitchSmsService.setOnCheckedChangeListener(activateSMSWAListener);
        mRelativeLayoutSmsService.setOnClickListener(layoutClickListener);
        mEditTextSMSText.addTextChangedListener(smsEditorWatcher);

        //WA Service
        mRelativeLayoutWAService = (RelativeLayout)rootView.findViewById(R.id.waLayout);
        mSwitchWAService = (Switch)rootView.findViewById(R.id.switch_activate_wa_service);
        mRelativeLayoutWAExtendedLayout = (RelativeLayout)rootView.findViewById(R.id.waExtendedLayout);
        mEditTextWAText = (EditText)rootView.findViewById(R.id.waSavedText);
        mSwitchWAService.setOnCheckedChangeListener(activateSMSWAListener);
        mRelativeLayoutWAService.setOnClickListener(layoutClickListener);

        // Unknown Contacts
        mSwitchUnknownContactsService = (Switch) rootView.findViewById(R.id.switch_activate_unknown_contacts_service);
        mSwitchUnknownContactsService.setOnCheckedChangeListener(activateServiceOrUnknownContactsListener);

        mSwitchUnknownContactsService.post(new Runnable() {
            @Override
            public void run() {
                mSwitchUnknownContactsService.setChecked((Boolean) mDataStore.getDataFromStore(KeysUtils.UNKNOWN_SERVICE_ACTIVATE_KEY));
            }
        });

         mInputMethodManger= (InputMethodManager) rootView.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);


    }

    /**
     * This callback is invoked when the service button is toggle
     */
    private CompoundButton.OnCheckedChangeListener activateServiceOrUnknownContactsListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

            switch(buttonView.getId()){
                case R.id.switch_activate_service:
                    mDataStore.storeData(KeysUtils.SERVICE_ACTIVATE_KEY,isChecked);
                    updateActivateServiceSwitch(isChecked);
                    break;
                case R.id.switch_activate_unknown_contacts_service:
                    mDataStore.storeData(KeysUtils.UNKNOWN_SERVICE_ACTIVATE_KEY,isChecked);
                    break;
            }
        }
    };

    /**
     * Update the serviceActivation layout and show the notification accordingly
     * @param isServiceActive
     */
    private void updateActivateServiceSwitch(final boolean isServiceActive) {
        mSwitchService.post(new Runnable() {
            @Override
            public void run() {
                mSwitchService.setChecked(isServiceActive);
                if(isServiceActive){
                    mTextViewServiceActivatedTag.setText(R.string.tv_activated);
                    mTextViewServiceActivatedTag.setTextColor(getResources().getColor(R.color.green));
                    showNotification(mSwitchService.getContext());
                    return;
                }
                mTextViewServiceActivatedTag.setText(R.string.tv_deactivated);
                mTextViewServiceActivatedTag.setTextColor(getResources().getColor(R.color.red));
                NotificationManager mNotificationManager = (NotificationManager) mSwitchService.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.cancel(NOTIFICATION_ID);
            }
        });
    }

    /**
     * this method shows the notification and associates a Pending Intent with it
     * @param appContext
     */
    private void showNotification(final Context appContext) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(appContext.getApplicationContext());
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setOngoing(true);
        mBuilder.setContentTitle(appContext.getResources().getString(R.string.notification_big_text));
        mBuilder.setContentText(appContext.getResources().getString(R.string.notification_small_text));
        Intent resultIntent = new Intent(appContext, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(appContext);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
    /**
     * This callback will be invoked after a phone mode is selected
     */
    AdapterView.OnItemSelectedListener phoneModeSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if((Boolean)mDataStore.getDataFromStore(KeysUtils.SERVICE_ACTIVATE_KEY)) {
                mDataStore.storeData(KeysUtils.PHONE_MODE_SELECTION_KEY, position);
                final AudioManager phoneMode = (AudioManager) view.getContext().getSystemService(Context.AUDIO_SERVICE);
                switch (position) {
                    case 0:break;
                    case 1:
                        phoneMode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        break;
                    case 2:
                        phoneMode.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                        break;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent){}
    };

    /**
     * This method is callback when a WA or SMS service switch is changed.
     */
    private CompoundButton.OnCheckedChangeListener activateSMSWAListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
            Animation collapseExpandAnimation = null;

            switch(buttonView.getId()){
                case R.id.switch_activate_sms_service:
                    mDataStore.storeData(KeysUtils.SMS_SERVICE_ACTIVE_KEY,isChecked);
                    if(true == isChecked && mRelativeLayoutSmsExtendedLayout.getVisibility() == View.GONE){
                        collapseExpandAnimation = expand(mRelativeLayoutSmsExtendedLayout,true);
                        saveOrRestoreSMSWAText(false,true);
                    }
                    else if((false == isChecked && mRelativeLayoutSmsExtendedLayout.getVisibility() == View.GONE) ||
                            (mRelativeLayoutSmsExtendedLayout.getVisibility() == View.VISIBLE && true == isChecked)){
                        return;
                    }
                    else if(false == isChecked && mRelativeLayoutSmsExtendedLayout.getVisibility() == View.VISIBLE){
                        mRelativeLayoutSmsExtendedLayout.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutSmsExtendedLayout,false);
                        saveOrRestoreSMSWAText(true,true);
                    }
                    mRelativeLayoutSmsExtendedLayout.setAnimation(collapseExpandAnimation);
                    break;
                case R.id.switch_activate_wa_service:
                    mDataStore.storeData(KeysUtils.WA_SERVICE_ACTIVE_KEY,isChecked);
                    if(true == isChecked && mRelativeLayoutWAExtendedLayout.getVisibility() == View.GONE){
                        collapseExpandAnimation = expand(mRelativeLayoutWAExtendedLayout,true);
                        saveOrRestoreSMSWAText(false,false);
                    }
                    else if((false == isChecked && mRelativeLayoutWAExtendedLayout.getVisibility() == View.GONE) ||
                            (mRelativeLayoutWAExtendedLayout.getVisibility() == View.VISIBLE && true == isChecked)){
                        return;
                    }
                    else if(false == isChecked && mRelativeLayoutWAExtendedLayout.getVisibility() == View.VISIBLE){
                        mRelativeLayoutWAExtendedLayout.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutWAExtendedLayout,false);
                        saveOrRestoreSMSWAText(true,false);
                    }
                    mRelativeLayoutWAExtendedLayout.setAnimation(collapseExpandAnimation);
                    break;
                }
            collapseExpandAnimation.start();
        }
    };


    /**
     * This callback is for Layout when the user clicks on the main layout.
     * This will animate the layout.
     */
    View.OnClickListener layoutClickListener = new View.OnClickListener(){
        Animation expandCollapseAnimation = null;
        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.smsLayout:
                    if(mRelativeLayoutSmsExtendedLayout.getVisibility() == View.VISIBLE){
                        mRelativeLayoutSmsExtendedLayout.setVisibility(View.GONE);
                        expandCollapseAnimation = expand(mRelativeLayoutSmsExtendedLayout,false);
                        saveOrRestoreSMSWAText(true,true);
                    }else{
                        mRelativeLayoutSmsExtendedLayout.setVisibility(View.VISIBLE);
                        expandCollapseAnimation = expand(mRelativeLayoutSmsExtendedLayout,true);
                        saveOrRestoreSMSWAText(false,true);
                    }
                    mRelativeLayoutSmsExtendedLayout.setAnimation(expandCollapseAnimation);
                    break;
                case R.id.waLayout:
                    if(mRelativeLayoutWAExtendedLayout.getVisibility() == View.VISIBLE){
                        mRelativeLayoutWAExtendedLayout.setVisibility(View.GONE);
                        expandCollapseAnimation = expand(mRelativeLayoutWAExtendedLayout,false);
                        saveOrRestoreSMSWAText(true,false);
                    }else{
                        mRelativeLayoutWAExtendedLayout.setVisibility(View.VISIBLE);
                        expandCollapseAnimation = expand(mRelativeLayoutWAExtendedLayout,true);
                        saveOrRestoreSMSWAText(false,false);
                    }
                    mRelativeLayoutWAExtendedLayout.setAnimation(expandCollapseAnimation);
                    break;
            }
            expandCollapseAnimation.start();

            if(mRelativeLayoutSmsExtendedLayout.getVisibility() == View.GONE || mRelativeLayoutWAExtendedLayout.getVisibility() == View.GONE){
                mInputMethodManger.hideSoftInputFromWindow(mEditTextSMSText.getWindowToken(),0);
                mInputMethodManger.hideSoftInputFromWindow(mEditTextWAText.getWindowToken(),0);
            }

        }


    };
    /**
     * This method is used for translate animation for the SMS and WA layout.
     */
    public Animation expand(final View v, final boolean expand) {
        try {
            Method m = v.getClass().getDeclaredMethod("onMeasure", int.class,
                    int.class);
            m.setAccessible(true);
            m.invoke(v,
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(
                            ((View) v.getParent()).getMeasuredWidth(),
                            View.MeasureSpec.AT_MOST));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final int initialHeight = v.getMeasuredHeight();
        if (expand) {
            v.getLayoutParams().height = 0;
        } else {
            v.getLayoutParams().height = initialHeight;
        }
        v.setVisibility(View.VISIBLE);
        final Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int newHeight = 0;
                if (expand) {
                    newHeight = (int) (initialHeight * interpolatedTime);
                } else {
                    newHeight = (int) (initialHeight * (1 - interpolatedTime));
                }
                v.getLayoutParams().height = newHeight;
                v.requestLayout();
                if (interpolatedTime == 1 && !expand)
                    v.setVisibility(View.GONE);
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration(300);
        a.setInterpolator(new AccelerateInterpolator());
        return a;

    }

    /**
     * This callback is given for the EditText when the user types the sms. It keeps the count of the
     * No of characters typed and shows how many sms the messages will be sent.
     */
    TextWatcher smsEditorWatcher = new TextWatcher(){
        /**
         * Maximum Number of Charaters per SMS.
         */
        final Integer MAX_NO_OF_LETTERS_PER_SMS = 160;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            mTextViewSMSLetterCount.post(new Runnable() {
                @Override
                public void run() {
                    mTextViewSMSLetterCount.setText((Math.abs((s.length()%MAX_NO_OF_LETTERS_PER_SMS) - MAX_NO_OF_LETTERS_PER_SMS)) + "/ " + (int)(s.length()/MAX_NO_OF_LETTERS_PER_SMS));
                }
            });
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    /**
     * This method is used to load/ save the sms/wa text.
     * @param isSave true if data is to be saved, false for restore
     * @param isSMS true if datasaved is SMS, false for WA
     */
    private void saveOrRestoreSMSWAText(final boolean isSave, final boolean isSMS){
        //For SMS
        if(isSMS){
            if(isSave){
                mDataStore.storeData(KeysUtils.SMS_SERVICE_TEXT_ACTIVE_KEY,mEditTextSMSText.getText().toString());
                return;
            }
            mEditTextSMSText.setText((String)mDataStore.getDataFromStore(KeysUtils.SMS_SERVICE_TEXT_ACTIVE_KEY));
            return;
        }
        //WA
        if(isSave){
            mDataStore.storeData(KeysUtils.WA_SERVICE_TEXT_ACTIVE_KEY,mEditTextWAText.getText().toString());
            return;
        }
        mEditTextWAText.setText((String)mDataStore.getDataFromStore(KeysUtils.WA_SERVICE_TEXT_ACTIVE_KEY));
        return;
    }

}
