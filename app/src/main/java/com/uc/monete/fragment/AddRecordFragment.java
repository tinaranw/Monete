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

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.monete.R;
import com.uc.monete.api.BackgroundWorker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddRecordFragment extends Fragment implements TextWatcher, AdapterView.OnItemSelectedListener  {

    TextInputLayout amount, memo;
    TextView date;
    Spinner spinnertag, spinnertype;
    String hist_user_id, hist_amount, hist_type, hist_tag, hist_date, hist_memo, hist_cur_balance;

    public AddRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_record, container, false);

        spinnertype = v.findViewById(R.id.spinner_inputtype);
        date = v.findViewById(R.id.input_date);
        amount = v.findViewById(R.id.input_amount);
        spinnertag = v.findViewById(R.id.spinner_tags);
        memo = v.findViewById(R.id.input_memo);

        amount.getEditText().addTextChangedListener(this);
        memo.getEditText().addTextChangedListener(this);

        //SPINNER TAG
        final ImageView tag_img =  v.findViewById(R.id.fr_tag_image);
        final Spinner spinnerTag = (Spinner) v.findViewById(R.id.spinner_tags);
        ArrayAdapter<String> adapterTag = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tags));
        adapterTag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTag.setAdapter(adapterTag);

        //CHANGE IMAGE
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

        //SPINNER TYPE
        Spinner spinnerType = (Spinner) v.findViewById(R.id.spinner_inputtype);
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.inputType));
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        //TEXTVIEW DATE
        TextView dateRecord = v.findViewById(R.id.input_date);

        SimpleDateFormat dateF = new SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault());
        String date = dateF.format(Calendar.getInstance().getTime());

        dateRecord.setText(date);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Button btn_addrecord = view.findViewById(R.id.fr_addrecord);
        Button btn_discardrecord = view.findViewById(R.id.fr_discardrecord);



        btn_addrecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //SEND DATA

                String hist_user_id ="1";
                String hist_cur_balance ="270000";
                BackgroundWorker backgroundWorker = new BackgroundWorker(view.getContext());
                backgroundWorker.execute(hist_user_id,hist_amount, hist_type, hist_tag, hist_date, hist_memo, hist_cur_balance);


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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        hist_amount = amount.getEditText().getText().toString().trim();
        hist_type = spinnertype.getSelectedItem().toString();
        hist_tag = spinnertag.getSelectedItem().toString();
        hist_date = date.getText().toString().trim();
        hist_memo = memo.getEditText().getText().toString().trim();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
