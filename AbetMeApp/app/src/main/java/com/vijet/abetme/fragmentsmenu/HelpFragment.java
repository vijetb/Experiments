/**
 * @Author Vijet Badigannvar(bvijet@gmail.com)
 */
package com.vijet.abetme.fragmentsmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vijet.abetme.R;

import java.lang.reflect.Method;

/**
 * This class defines the Help Fragment for the application.This class is invoked when the user
 * clicks the action button and selects Help Button!
 */
public class HelpFragment extends Fragment {
    private static final String TAG = "HelpFragment";

    private RelativeLayout mRelativeLayoutTabs,mRelativeLayoutTabsDesc,mRelativeLayoutService,mRelativeLayoutServiceDesc,
            mRelativeLayoutPhoneMode,mRelativeLayoutPhoneModeDesc, mRelativeLayoutSMS,mRelativeLayoutSMSDesc,
            mRelativeLayoutWA,mRelativeLayoutWADesc, mRelativeLayoutShareOther,mRelativeLayoutShareOtherDesc;

    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView){
        mRelativeLayoutTabs = (RelativeLayout)rootView.findViewById(R.id.helpLayoutTab);
        mRelativeLayoutTabsDesc = (RelativeLayout)rootView.findViewById(R.id.helpLayoutdescTab);
        mRelativeLayoutTabs.setOnClickListener(groupLayoutClickListener);

        //SERVICE
        mRelativeLayoutService = (RelativeLayout)rootView.findViewById(R.id.helpLayoutService);
        mRelativeLayoutServiceDesc = (RelativeLayout)rootView.findViewById(R.id.helpLayoutServicedescTab);
        mRelativeLayoutService.setOnClickListener(groupLayoutClickListener);

        // Phone Mode

        mRelativeLayoutPhoneMode = (RelativeLayout)rootView.findViewById(R.id.helpLayoutPhoneMode);
        mRelativeLayoutPhoneModeDesc = (RelativeLayout)rootView.findViewById(R.id.helpLayoutPhoneModedescTab);
        mRelativeLayoutPhoneMode.setOnClickListener(groupLayoutClickListener);

        // SMS
        mRelativeLayoutSMS = (RelativeLayout)rootView.findViewById(R.id.helpLayoutSMS);
        mRelativeLayoutSMSDesc = (RelativeLayout)rootView.findViewById(R.id.helpLayoutSMSdescTab);
        mRelativeLayoutSMS.setOnClickListener(groupLayoutClickListener);

        // WA
        mRelativeLayoutWA = (RelativeLayout)rootView.findViewById(R.id.helpLayoutWA);
        mRelativeLayoutWADesc = (RelativeLayout)rootView.findViewById(R.id.helpLayoutWAdescTab);
        mRelativeLayoutWA.setOnClickListener(groupLayoutClickListener);

        // share others

        mRelativeLayoutShareOther = (RelativeLayout)rootView.findViewById(R.id.helpLayoutShareOthers);
        mRelativeLayoutShareOtherDesc = (RelativeLayout)rootView.findViewById(R.id.helpLayoutShareOthersdescTab);
        mRelativeLayoutShareOther.setOnClickListener(groupLayoutClickListener);
    }

    private View.OnClickListener groupLayoutClickListener = new View.OnClickListener(){
        Animation collapseExpandAnimation = null;
        @Override
        public void onClick(View v) {

            Log.d(TAG,"onCLikde");
            switch(v.getId()){
                case R.id.helpLayoutTab:
                    if(mRelativeLayoutTabsDesc.getVisibility() == View.GONE){
                        mRelativeLayoutTabsDesc.setVisibility(View.VISIBLE);
                        collapseExpandAnimation = expand(mRelativeLayoutTabsDesc,true);
                    }else{
                        mRelativeLayoutTabsDesc.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutTabsDesc,false);
                    }
                    mRelativeLayoutTabsDesc.setAnimation(collapseExpandAnimation);
                    break;
                case R.id.helpLayoutService:
                    if(mRelativeLayoutServiceDesc.getVisibility() == View.GONE){
                        mRelativeLayoutServiceDesc.setVisibility(View.VISIBLE);
                        collapseExpandAnimation = expand(mRelativeLayoutServiceDesc,true);
                    }else{
                        mRelativeLayoutServiceDesc.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutServiceDesc,false);
                    }
                    mRelativeLayoutServiceDesc.setAnimation(collapseExpandAnimation);
                    break;
                case R.id.helpLayoutPhoneMode:
                    if(mRelativeLayoutPhoneModeDesc.getVisibility() == View.GONE){
                        mRelativeLayoutPhoneModeDesc.setVisibility(View.VISIBLE);
                        collapseExpandAnimation = expand(mRelativeLayoutPhoneModeDesc,true);
                    }else{
                        mRelativeLayoutPhoneModeDesc.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutPhoneModeDesc,false);
                    }
                    mRelativeLayoutPhoneModeDesc.setAnimation(collapseExpandAnimation);
                    break;
                case R.id.helpLayoutSMS:
                    if(mRelativeLayoutSMSDesc.getVisibility() == View.GONE){
                        mRelativeLayoutSMSDesc.setVisibility(View.VISIBLE);
                        collapseExpandAnimation = expand(mRelativeLayoutSMSDesc,true);
                    }else{
                        mRelativeLayoutSMSDesc.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutSMSDesc,false);
                    }
                    mRelativeLayoutSMSDesc.setAnimation(collapseExpandAnimation);
                    break;

                case R.id.helpLayoutWA:
                    if(mRelativeLayoutWADesc.getVisibility() == View.GONE){
                        mRelativeLayoutWADesc.setVisibility(View.VISIBLE);
                        collapseExpandAnimation = expand(mRelativeLayoutWADesc,true);
                    }else{
                        mRelativeLayoutWADesc.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutWADesc,false);
                    }
                    mRelativeLayoutWADesc.setAnimation(collapseExpandAnimation);
                    break;
                case R.id.helpLayoutShareOthers:
                    if(mRelativeLayoutShareOtherDesc.getVisibility() == View.GONE){
                        mRelativeLayoutShareOtherDesc.setVisibility(View.VISIBLE);
                        collapseExpandAnimation = expand(mRelativeLayoutShareOtherDesc,true);
                    }else{
                        mRelativeLayoutShareOtherDesc.setVisibility(View.GONE);
                        collapseExpandAnimation = expand(mRelativeLayoutShareOtherDesc,false);
                    }
                    mRelativeLayoutShareOtherDesc.setAnimation(collapseExpandAnimation);
                    break;

            }
        }
    };


/*
    private class HelpExpandableListAdapter extends BaseExpandableListAdapter{

        private LayoutInflater layoutInflater = null;

        private final int NO_OF_GROUPS = 5;

        private final int NO_OF_CHILDS = 1;

        String[] parents = {"Parent-1","Parent-2","Parent-3","Parent-4","Parent-5"};
        String[] childs = {"Child-1","Child-2","Child-3","Child-4","Child-5"};
        String[] stringArray = null;



        public HelpExpandableListAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            return NO_OF_GROUPS;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return NO_OF_CHILDS;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parents[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.list_item_parent_help_fragment,parent);
            TextView parentview = (TextView) convertView.findViewById(R.id.tv_parent_item);
            parentview.setText(parents[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.list_item_child_help_fragment,parent);
            RelativeLayout parentLayout = (RelativeLayout) convertView.findViewById(R.id.expandableListChildLayout);
            TextView childview = (TextView) convertView.findViewById(R.id.tv_child_item);
            childview.setText(childs[groupPosition]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }





    }*/

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
}
