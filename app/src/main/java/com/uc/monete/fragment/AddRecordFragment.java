package com.uc.monete.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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
import com.uc.monete.layouts.LoadingPageActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddRecordFragment extends Fragment implements TextWatcher, AdapterView.OnItemSelectedListener  {
    Toolbar toolbar;

    TextInputLayout amount, memo;
    TextView date;
    Spinner spinnertag, spinnertype;
    String hist_user_id, hist_amount, hist_type, hist_tag, hist_date, hist_memo, hist_cur_balance;
    Integer int_bal, int_amount;


    public AddRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Add Record");
        View v = inflater.inflate(R.layout.fragment_add_record, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnertype = view.findViewById(R.id.spinner_inputtype);
        date = view.findViewById(R.id.input_date);
        amount = view.findViewById(R.id.input_amount);
        spinnertag = view.findViewById(R.id.spinner_tags);
        memo = view.findViewById(R.id.input_memo);

        amount.getEditText().addTextChangedListener(this);
        memo.getEditText().addTextChangedListener(this);

        hist_user_id ="1";
        hist_cur_balance ="";

        //SPINNER TAG
        final ImageView tag_img =  view.findViewById(R.id.fr_tag_image);
        final Spinner spinnerTag = view.findViewById(R.id.spinner_tags);
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
        final Spinner spinnerType = (Spinner) view.findViewById(R.id.spinner_inputtype);
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.inputType));
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        //INPUT CURR BALANCE
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(hist_amount != null && getResources().getStringArray(R.array.inputType)[0].equals(spinnerType.getItemAtPosition(position).toString())){
                    //income
                    int_amount = Integer.parseInt(hist_amount);
                    hist_cur_balance = String.valueOf(int_amount);

                } else if (hist_amount != null && getResources().getStringArray(R.array.inputType)[1].equals(spinnerType.getItemAtPosition(position).toString())){
                    //expense
                    int_amount = Integer.parseInt(hist_amount);
                    hist_cur_balance = "-" + int_amount;
                    Log.d("balance", hist_cur_balance);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //TEXTVIEW DATE
        TextView dateRecord = view.findViewById(R.id.input_date);

        SimpleDateFormat dateF = new SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault());
        String date = dateF.format(Calendar.getInstance().getTime());

        dateRecord.setText(date);

        Button btn_addrecord = view.findViewById(R.id.fr_addrecord);
        Button btn_discardrecord = view.findViewById(R.id.fr_discardrecord);

        btn_addrecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //SEND DATA
                String activityType = "addRecord";

                BackgroundWorker backgroundWorker = new BackgroundWorker(view.getContext());
                backgroundWorker.execute(activityType,hist_amount, hist_type, hist_tag, hist_date, hist_memo, hist_cur_balance);

                Fragment fragment = new HistoryFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

//                Intent intent = new Intent(getActivity(), LoadingPageActivity.class);
//                startActivity(intent);

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
