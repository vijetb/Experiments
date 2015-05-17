package officeabet.vijet.com.experiment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BMH1014669 on 01/11/14.
 */
public class MyAdapter extends BaseAdapter {

    private ArrayList<DataModel> data;
    private Context mContext;
    private LayoutInflater mInfalter;
    public MyAdapter(ArrayList<DataModel> data, Context context){
        this.data = data;
        mContext = context;
        mInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = mInfalter.inflate(R.layout.list_item_contacts,null);
            holder.mBadge = (QuickContactBadge) convertView.findViewById(R.id.quickContactBadge);
            holder.mCheckedTextView = (CheckedTextView)convertView.findViewById(R.id.checkedTextView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mCheckedTextView.setText(data.get(position).Name);
        holder.mCheckedTextView.setChecked(data.get(position).isSelected);
        holder.mBadge.setImageResource(R.drawable.ic_launcher);
        return convertView;
    }

    class ViewHolder{
        public QuickContactBadge mBadge;
        public CheckedTextView mCheckedTextView;
    }


}
