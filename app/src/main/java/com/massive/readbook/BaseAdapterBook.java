package com.massive.readbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


public class BaseAdapterBook extends BaseAdapter {

    Context context;
    ArrayList<Item> books;
    LayoutInflater Inflater;

    public BaseAdapterBook(Context myContext, ArrayList<Item> myBooks) {
        this.context = myContext;
        this.books = myBooks;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cameView, ViewGroup parent) {
        viewHolder viewHolder;
        if (cameView == null) {
            Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cameView = Inflater.inflate(R.layout.row, null);
            viewHolder = new viewHolder(cameView);
            cameView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) cameView.getTag();
        }

        try {
            Glide.with(context)
                    .load(books.get(position).volumeInfo.imageLinks.smallThumbnail)
                    .error(R.drawable.ic_warning_black_48dp)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cameView;
    }

    public class viewHolder {

        protected ImageView imageView;

        public viewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.BookImageView);
        }
    }
}
