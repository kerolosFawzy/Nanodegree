package com.massive.readbook.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.massive.readbook.Item;
import com.massive.readbook.R;
import com.massive.readbook.utiles.constants;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookDetial extends Fragment {
    @BindView(R.id.bookTitle)
    TextView Title;
    @BindView(R.id.publishedDate)
    TextView publishedDate;
    @BindView(R.id.ThePublisher)
    TextView ThePublisher;
    @BindView(R.id.Author)
    TextView Author;
    @BindView(R.id.Number)
    TextView Number;
    @BindView(R.id.Describition)
    TextView Describition;
    @BindView(R.id.BookDetialView)
    ImageView BookDetialView;
    @BindView(R.id.ViewBook)
    Button ViewBook;

    private Item MyInfo;
    String Error="Result not Found";

    public static BookDetial newInstance(Item book) {
        Bundle args = new Bundle();
        args.putSerializable(constants.MY_KEY, book);
        BookDetial fragment = new BookDetial();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.book_detials, container, false);
        ButterKnife.bind(this, rootview);
        try {
            MyInfo = (Item) getArguments().getSerializable(constants.MY_KEY);
            Title.setText(MyInfo.volumeInfo.title);
            publishedDate.setText(MyInfo.volumeInfo.publishedDate);
            try {
                Number.setText(String.valueOf(MyInfo.volumeInfo.pageCount));
            }catch (Exception e){
                Number.setText(Error);
            }

            try {
                ThePublisher.setText(MyInfo.volumeInfo.publisher);
            } catch (Exception e) {
                ThePublisher.setText(Error);
            }
            try {
                Author.setText(MyInfo.volumeInfo.authors.get(0));
            } catch (Exception e) {
                Author.setText(Error);
            }
            try {
                Describition.setText(MyInfo.volumeInfo.description);
            } catch (Exception e) {
                Describition.setText(Error);
            }

            Glide.with(getActivity()).load(MyInfo.volumeInfo.imageLinks.smallThumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(BookDetialView);
            try{
                ViewBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bookLink = MyInfo.volumeInfo.previewLink;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bookLink));
                        startActivity(browserIntent);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootview;
    }


}
