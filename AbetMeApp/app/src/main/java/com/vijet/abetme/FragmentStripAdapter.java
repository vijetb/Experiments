package com.vijet.abetme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vijet.abetme.fragmentactivecontacts.ActiveContactsFragment;
import com.vijet.abetme.fragmentcontacts.ContactsFragment;
import com.vijet.abetme.fragmentservice.ServiceFragment;

/**
 * Adapter class to setup the tabs for navigation
 */
public class FragmentStripAdapter extends FragmentPagerAdapter {

    private final Integer NO_OF_TABS = 3;

    public FragmentStripAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment attachingFragment = null;
        switch(position){
            case 0: attachingFragment = new ServiceFragment();
                    break;
            case 1: attachingFragment = new ActiveContactsFragment();
                    break;
            case 2: attachingFragment = new ContactsFragment();
                    break;
        }
        return attachingFragment;
    }

    @Override
    public int getCount() {
        return NO_OF_TABS;
    }

}
