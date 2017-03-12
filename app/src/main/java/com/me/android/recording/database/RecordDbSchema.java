package com.me.android.recording.database;

/**
 * Created by admin on 2017/3/9.
 */

public class RecordDbSchema {

    //定义一个内部类 描速表结构
    public static final class RecrodTable{
        public static final String NANE="records";
        public static final class Cols{
            public static final  String UUID="uuId";
            public static final  String TITLE="title";
            public static final  String DATE="date";
            public static final  String SOLVED="solved";
        }
    }
}
