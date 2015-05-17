package officeabet.vijet.com.experiment;

import android.widget.ImageView;

/**
 * Created by BMH1014669 on 01/11/14.
 */
public class DataModel {
    public String Name;
    public boolean isSelected;
    public ImageView mImageView;

    public DataModel(String name,boolean isSelected, ImageView view) {
        this.Name = name;
        this.isSelected = isSelected;
        this.mImageView = view;
    }



}
