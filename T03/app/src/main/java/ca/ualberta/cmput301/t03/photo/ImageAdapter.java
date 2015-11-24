package ca.ualberta.cmput301.t03.photo;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import ca.ualberta.cmput301.t03.R;

/**
 * Modified from http://developer.android.com/guide/topics/ui/layout/gridview.html
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private PhotoGallery model;

    public ImageAdapter(Context context, PhotoGallery model) {
        this.context = context;
        this.model = model;
    }

    public int getCount() {
        return model.getPhotos().size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);

            imageView.setLayoutParams(new GridView.LayoutParams(parent.getWidth()/2, parent.getWidth()/2));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(model.getPhotos().get(position).getPhoto());
        return imageView;
    }
}