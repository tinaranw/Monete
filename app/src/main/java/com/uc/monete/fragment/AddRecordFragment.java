package com.uc.monete.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.monete.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddRecordFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnertag, spinnertype;
    public AddRecordFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_record, container, false);
        View v = inflater.inflate(R.layout.fragment_add_record, container, false);
        final ImageView tag_img =  v.findViewById(R.id.fr_tag_image);
        final Spinner spinnerTag = (Spinner) v.findViewById(R.id.spinner_tags);
        ArrayAdapter<String> adapterTag = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tags));
        adapterTag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTag.setAdapter(adapterTag);

        spinnerTag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(getResources().getStringArray(R.array.tags)[0].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[1].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_local_play_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[2].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_directions_car_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[3].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_restaurant_menu_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[4].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_flash_on_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[5].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_credit_card_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[6].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_card_giftcard_black_24dp);
                } else if (getResources().getStringArray(R.array.tags)[7].equals(spinnerTag.getItemAtPosition(position).toString())){
                    tag_img.setImageResource(R.drawable.ic_attach_money_black_24dp);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerType = (Spinner) v.findViewById(R.id.spinner_inputtype);
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.inputType));
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        TextView dateRecord = v.findViewById(R.id.input_date);

        SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        String date = dateF.format(Calendar.getInstance().getTime());

        dateRecord.setText(date);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_addrecord = view.findViewById(R.id.fr_addrecord);
        Button btn_discardrecord = view.findViewById(R.id.fr_discardrecord);

//        spinnertag = view.findViewById(R.id.spinner_tags);
//        spinnertype = view.findViewById(R.id.spinner_inputtype);
//
//        ArrayAdapter<CharSequence> adapterTag  = ArrayAdapter.createFromResource(view.getContext(), R.array.tags, android.R.layout.simple_spinner_item);
//        adapterTag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnertag.setAdapter(adapterTag);
//        spinnertag.setOnItemClickListener(view.getContext());




        btn_addrecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new HistoryFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_discardrecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
