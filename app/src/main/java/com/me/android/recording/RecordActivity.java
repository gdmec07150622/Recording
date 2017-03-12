package com.me.android.recording;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class RecordActivity extends SingleFragmentActivity{
    //public static final String EXTEA_RECORD_ID="com.me.android.recording.record_id";
   private static final String EXTEA_RECORD_ID="com.me.android.recording.record_id";
    public static Intent newIntent(Context packageContext,UUID recordID){
        Intent intent=new Intent(packageContext,RecordActivity.class);
        intent.putExtra(EXTEA_RECORD_ID,recordID);
        return intent;
    }

    protected Fragment createFragment(){
       // return  new RecordFragment();
        UUID recordID=(UUID)getIntent().getSerializableExtra(EXTEA_RECORD_ID);
        return RecordFragment.newInstance(recordID);
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm=getSupportFragmentManager();
            Fragment fragment=fm.findFragmentById(R.id.fragment_container);
            if(fragment==null){
                fragment=new RecordFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
    */
}


