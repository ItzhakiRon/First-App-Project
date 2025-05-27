package com.example.weaponz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class FragmentGallery extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentGallery() {
        // Required empty public constructor
    }

    public static FragmentGallery newInstance(String param1, String param2) {
        FragmentGallery fragment = new FragmentGallery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.weapon1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.weapon2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.weapon3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.weapon4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.weapon5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.weapon6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        // Second ImageSlider
        ImageSlider imageSlider2 = view.findViewById(R.id.imageSlider2);
        ArrayList<SlideModel> slideModels2 = new ArrayList<>();

        slideModels2.add(new SlideModel(R.drawable.weapon7, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.weapon8, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.weapon9, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.weapon10, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.weapon11, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.weapon12, ScaleTypes.FIT));

        imageSlider2.setImageList(slideModels2, ScaleTypes.FIT);

        return view;
    }
}
