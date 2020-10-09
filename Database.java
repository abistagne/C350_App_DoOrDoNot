package com.example.do_or_do_not;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Todo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "todo_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASKNAME = "task_name";
    private static final String COLUMN_TASKDATE = "task_date";
    private static final String COLUMN_TASKDESC = "task_description";
    private static final String COLUMN_TASKPRIORITY = "task_priority";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, factory: null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
    String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASKNAME + "TEXT, " + COLUMN_TASKDATE +  " TEXT, " + COLUMN_TASKDESC + "TEXT, " + COLUMN_TASKPRIORITY + " TEXT);";
    db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME) ;
    onCreate(db);
    }
}