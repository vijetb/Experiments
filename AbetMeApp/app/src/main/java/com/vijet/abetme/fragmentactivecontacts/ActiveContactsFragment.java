package com.vijet.abetme.fragmentactivecontacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.vijet.abetme.R;
import com.vijet.abetme.data.DataStore;
import com.vijet.abetme.util.KeysUtils;

public class ActiveContactsFragment extends Fragment {
    private static final String TAG = "ServiceFragment";
    //Data store
    private DataStore mDataStore;
    //activateServiceLayout
    private Switch mSwitchService;
    private TextView mTextViewServiceActivatedTag;


    public ActiveContactsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataStore = new DataStore(ActiveContactsFragment.this.getActivity().getApplicationContext());
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
        mSwitchService.setOnCheckedChangeListener(activateServiceListener);

        final boolean isServiceActive = (Boolean) mDataStore.getDataFromStore(KeysUtils.SERVICE_ACTIVATE_KEY);
        updateActivateServiceSwitch(isServiceActive);


    }



    /**
     * This callback is invoked when the service button is toggle
     */
    private CompoundButton.OnCheckedChangeListener activateServiceListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
            mDataStore.storeData(KeysUtils.SERVICE_ACTIVATE_KEY,isChecked);
            updateActivateServiceSwitch(isChecked);
        }
    };

    private void updateActivateServiceSwitch(final boolean isServiceActive) {
        mSwitchService.post(new Runnable() {
            @Override
            public void run() {
                mSwitchService.setChecked(isServiceActive);
                if(isServiceActive){
                    mTextViewServiceActivatedTag.setText(R.string.tv_activated);
                    mTextViewServiceActivatedTag.setTextColor(getResources().getColor(R.color.green));
                    return;
                }
                mTextViewServiceActivatedTag.setText(R.string.tv_deactivated);
                mTextViewServiceActivatedTag.setTextColor(getResources().getColor(R.color.red));
            }
        });
    }

}
