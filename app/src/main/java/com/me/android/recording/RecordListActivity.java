package com.me.android.recording;


;import android.support.v4.app.Fragment;

/**
 * Created by admin on 2017/3/5.
 */

public class RecordListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){ //实现父类抽象方法
        return new RecordListFragment();
    }
}
