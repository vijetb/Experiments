package com.vijet.abetme.util;

import com.vijet.abetme.data.DataStore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by BMH1014669 on 09/11/14.
 */
public final class StringUtility {

  public static String getActiveContactsAsString(final HashSet<String> selectedContacts, final String activeContactsString){
        StringBuilder activeContactsSavedString = null;
        if(null != activeContactsString){
            String[] activeContacts = activeContactsString.split(",");
            for(String contact :activeContacts){
                selectedContacts.add(contact);
            }
            activeContactsSavedString = new StringBuilder();
            Iterator<String> iterator = selectedContacts.iterator();
            while(iterator.hasNext()){
                activeContactsSavedString.append(iterator.next());
                activeContactsSavedString.append(",");
            }
        }
        return ((null == activeContactsSavedString) ? null : activeContactsSavedString.toString());
    }


}
