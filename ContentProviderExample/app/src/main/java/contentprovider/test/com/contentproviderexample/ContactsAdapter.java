package contentprovider.test.com.contentproviderexample;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by BMH1014669 on 03/11/14.
 */
public class ContactsAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private static int count = 0;
    private Context mContext;
    public ContactsAdapter(Context context, Cursor c) {
        super(context, c, false);
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_list_view,null);
        ViewHolder holder = new ViewHolder();
        holder.mName = (CheckedTextView) view.findViewById(R.id.name);
        holder.mBadge = (ImageView) view.findViewById(R.id.quickBadge);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mName.setText(cursor.getString(1));

        int _Id = cursor.getInt(0);
        String picId = cursor.getString(3);
        if(null==picId) {
            holder.mBadge.setImageResource(R.drawable.ic_launcher);
        }else{
            //Uri contactURi = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, _Id);

            //InputStream is =ContactsContract.Contacts.openContactPhotoInputStream(mContext.getContentResolver(),contactURi);
            InputStream is = openPhoto(_Id);
            if(null == is){
                holder.mBadge.setImageResource(R.drawable.ic_launcher);
            }else{
                holder.mBadge.setImageBitmap(BitmapFactory.decodeStream(is));
            }


            /*
            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, _Id);
            Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
            try {
                AssetFileDescriptor fd =
                        mContext.getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
                holder.mBadge.setImageBitmap(BitmapFactory.decodeStream(fd.createInputStream()));
            } catch (IOException e) {
                holder.mBadge.setImageResource(R.drawable.ic_launcher);
            }
            */

            /*InputStream is  = openPhoto(_Id);
            if(null == is){
                holder.mBadge.setImageResource(R.drawable.ic_launcher);
            }else{
                holder.mBadge.setImageBitmap(BitmapFactory.decodeStream(is));
            }
            */
        }
    }

    private class ViewHolder{
        ImageView mBadge;
        CheckedTextView mName;
    }

    public InputStream openPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
        try {
            AssetFileDescriptor fd =
                    mContext.getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
            return fd.createInputStream();
        } catch (IOException e) {
            return null;
        }
    }
}
