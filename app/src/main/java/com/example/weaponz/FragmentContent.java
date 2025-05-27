package com.example.weaponz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentContent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;

    SearchView searchView;

    public FragmentContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentContent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentContent newInstance(String param1, String param2) {
        FragmentContent fragment = new FragmentContent();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        // Initialize your data list here
        // Example:
        androidData = new DataClass("Rifles", R.string.rifles, "Java", R.drawable.rifles_detail);
        dataList.add(androidData);
        // Add other DataClass items...

        androidData = new DataClass("Snipers", R.string.snipers, "Java", R.drawable.snipers_detail);
        dataList.add(androidData);

        androidData = new DataClass("Guns", R.string.guns, "Java", R.drawable.guns_detail);
        dataList.add(androidData);

        androidData = new DataClass("Knives", R.string.knives, "Java", R.drawable.knives_detail);
        dataList.add(androidData);

        androidData = new DataClass("Self Defense", R.string.selfdefense, "Java", R.drawable.defense_detail);
        dataList.add(androidData);

        adapter = new MyAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}