package com.example.do_or_do_not;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    //private static final String COLUMN_TASKDESC = "task_description";
    // private static final String COLUMN_TASKPRIORITY = "task_priority";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, name TEXT, date TEXT, status INTEGER )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertTask(String name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);      //todo check here
        contentValues.put("date", date);
        contentValues.put("status", 0);
        //  contentValues.put("COLUMN_TASKPRIORITY", priority);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateTaskStatus(String id, Integer status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);

        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{id});
        return true;
    }

    public boolean updateTask(String id, String name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("date", date);
        //contentValues.put("COLOMN_TASKDESC", description);

        db.update(TABLE_NAME, contentValues, "id =? ", new String[]{id});
        return true;
    }

    public boolean deleteTask(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ? ", new String[]{id});
        return true;
    }

    public Cursor getTodayTask() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE date(`date`) = date('now', 'localtime') order by id desc", null);
        return res;

    }

    public Cursor getTomorrowTask() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE date(`date`) = date('now', '+1 day', 'localtime')  order by id desc", null);
        return res;

    }

    public Cursor getSingleTask(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE id = '" + id + "' order by id desc", null);
        return res;

    }


    public Cursor getUpcomingTask() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE date(date) > date('now', '+1 day', 'localtime') order by id desc", null);
        return res;

    }


}