package com.me.android.recording;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;



/**
 * Created by admin on 2017/3/4.
 */

public class RecordFragment extends Fragment {
    private static final String ARG_RECORD_ID="record_id";
    private static  final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE=0;

    private Record mRecord;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static RecordFragment newInstance(UUID recordId){
        Bundle args=new Bundle();
        args.putSerializable(ARG_RECORD_ID,recordId);
        RecordFragment fragment=new RecordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mRecord=new Record();
    //    UUID recordID=(UUID)getActivity().getIntent().getSerializableExtra(RecordActivity.EXTEA_RECORD_ID);
        UUID recordID=(UUID)getArguments().getSerializable(ARG_RECORD_ID);
        mRecord=RecordLab.get(getActivity()).getRecord(recordID);

    }

    @Override
    public void onPause() {
        super.onPause();
        RecordLab.get(getActivity()).updateRecord(mRecord);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_record,container,false);
        mTitleField=(EditText)v.findViewById(R.id.record_title);
       mTitleField.setText(mRecord.getmTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRecord.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

                mDateButton=(Button)v.findViewById(R.id.record_date);
                mDateButton.setText(mRecord.getmDate().toString());
              //  mDateButton.setEnabled(false);
                mDateButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager=getFragmentManager();
                      //  DatePickerFragment datePickerFragment=new DatePickerFragment();
                        DatePickerFragment datePickerFragment=DatePickerFragment.newInstance(mRecord.getmDate());
                        datePickerFragment.setTargetFragment(RecordFragment.this,REQUEST_DATE);
                        datePickerFragment.show(fragmentManager,DIALOG_DATE);

                    }
                });
                mSolvedCheckBox=(CheckBox)v.findViewById(R.id.record_solved);
                mSolvedCheckBox.setChecked(mRecord.ismSolved());
                mSolvedCheckBox.setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener(){

                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                                mRecord.setmSolved(isChecked);
                            }
                        });


                return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {


        if(resultCode!= Activity.RESULT_OK){
            return;
        }

        if(requestCode==REQUEST_DATE){
            Date date=(Date)intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mRecord.setmDate(date);
            mDateButton.setText(mRecord.getmDate().toString());
}

    }
}
