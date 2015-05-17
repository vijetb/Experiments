package contentprovider.test.com.contentproviderexample;

import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by BMH1014669 on 09/11/14.
 */
public final class StringUtility {

    public final String getActiveContactsAsString(final HashSet<String> selectedContacts){
        StringBuilder contactsAsString = null;
        // [1111, 1280, 1234, 1789]
        if(null != selectedContacts){
            contactsAsString = new StringBuilder(selectedContacts.toString());
            contactsAsString.toString().replace('[','(');
            contactsAsString.toString().replace(']',')');
        }
        return contactsAsString.toString();
    }

    public final HashSet<String> getActiveContactsAsHashSet(final String activeContacts){
        HashSet<String> activeContactsSet = null;
        // [1111, 1280, 1234, 1789]
        if(null != activeContacts && !activeContacts.isEmpty()){
            StringTokenizer tokenizer = new StringTokenizer(activeContacts,",");
            activeContactsSet = new HashSet<String>();
            while(tokenizer.hasMoreTokens()){
                activeContactsSet.add(tokenizer.nextToken());
            }
        }
        return activeContactsSet;
    }

}
