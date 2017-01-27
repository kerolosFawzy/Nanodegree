package com.massive.readbook.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.massive.readbook.BaseAdapterBook;
import com.massive.readbook.Item;
import com.massive.readbook.R;
import com.massive.readbook.WebCall;
import com.massive.readbook.utiles.IcallBack;
import com.massive.readbook.utiles.NetworkUtils;
import com.massive.readbook.utiles.constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Minafaw on 27/01/2017.
 */

public class BaseFragment extends Fragment implements IcallBack {
    ArrayList<Item> books;
    public GridView mGridView;
    BaseAdapterBook baseAdapterBook;
    @BindView(R.id.MySearch)
    EditText mysearch;
    @BindView(R.id.SearchimageView)
    ImageView iv_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callNetWork(constants.BASEURL , "all");
    }

    private void callNetWork(String myurl , String search) {
        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            new WebCall(this, getActivity()).execute(myurl + search);
        } else {
            showErrormessage();
        }
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, null);
        ButterKnife.bind(this , view);
        mGridView = (GridView) view.findViewById(R.id.BooksGrid);
        bookMethod();

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNetWork(constants.BASEURL , mysearch.getText().toString());
            }
        });


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Fragment fragment = new BookDetial().newInstance(books.get(position));
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

            }
        });
        //View mEmptyView = LayoutInflater.from(getActivity()).inflate()
        //mGridView.setEmptyView();
        return view;
    }

    private void bookMethod() {
        if (books != null && mGridView != null) {
            baseAdapterBook = new BaseAdapterBook(getActivity(), books);
            mGridView.setAdapter(baseAdapterBook);
        }
    }

    public void showErrormessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("No Internet");
        builder.setMessage("Internet is required. Please Retry.");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                callNetWork(constants.BASEURL , "all");
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onPostExcuteCallBack(ArrayList<Item> ArrayList) {
        this.books = ArrayList;
        bookMethod();
    }
}
