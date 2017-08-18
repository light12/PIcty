package fr.pictycompany.picty;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class PhotosFragment extends Fragment {
    private RecyclerView mListPhotos;
    private ImageAdapter myImageAdapter;
    private Cursor mCursor;
    private ArrayList<String> itemList;

    public static PhotosFragment newInstance() {
        PhotosFragment fragment = new PhotosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerviewphotos, container, false);

        itemList = new ArrayList<>();
        mCursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            itemList.add(mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            mCursor.moveToNext();
        }
        mCursor.close();

        mListPhotos = (RecyclerView) view.findViewById(R.id.listPhotos);
        mListPhotos.setLayoutManager(new GridLayoutManager(getContext(), 4));
        myImageAdapter = new ImageAdapter(getContext(), itemList);
        mListPhotos.setAdapter(myImageAdapter);

        return view;
    }
}
