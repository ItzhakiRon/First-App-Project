package com.example.weaponz;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private static final String SHARED_PREFS_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";

    TextView textEmail;
    TextView textUsername;
    TextView textDate; // New TextView for displaying the creation date
    MyDatabaseHelper databaseHelper; // Database helper

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textEmail = view.findViewById(R.id.text_email);
        textUsername = view.findViewById(R.id.text_username);
        textDate = view.findViewById(R.id.text_date); // Initialize the new TextView

        databaseHelper = new MyDatabaseHelper(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS_NAME, getActivity().MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, "");
        String username = sharedPreferences.getString(KEY_USERNAME, "");
        textEmail.setText("Email - " + email);
        textUsername.setText("Username - " + username);

        String creationDate = databaseHelper.getCreationDate(email); // Retrieve the creation date
        textDate.setText("User date creation - " + creationDate); // Display the creation date

        TextView textView = view.findViewById(R.id.greeting_text);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 0 && hour < 12) {
            textView.setText("Good Morning");
        } else if (hour >= 12 && hour < 16) {
            textView.setText("Good Afternoon");
        } else {
            textView.setText("Good Night");
        }

        return view;
    }
}