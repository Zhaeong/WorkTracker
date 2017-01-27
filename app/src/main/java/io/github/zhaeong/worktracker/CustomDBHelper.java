package io.github.zhaeong.worktracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by owen_ on 2017-01-24.
 * Custom implementation of database helper
 */

public class CustomDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TaskItems.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TASKS_TABLE_NAME = "TASKS";
    public static final String TASKS_COL_ID = "_id";
    public static final String TASKS_COL_NAME = "TaskName";
    public static final String TASKS_COL_DESC = "TaskDesc";


    public static final String TABLE_CREATE_STATEMENT =
            "CREATE TABLE " +
            TASKS_TABLE_NAME +
            "( " + TASKS_COL_ID + " integer PRIMARY KEY, " +
            TASKS_COL_NAME +
            " TEXT, " +
            TASKS_COL_DESC +
            " TEXT )";

    public CustomDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TASKS_TABLE_NAME);
        onCreate(db);
    }

    public boolean addTask (String taskName, String taskDesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASKS_COL_NAME, taskName);
        contentValues.put(TASKS_COL_DESC, taskDesc);
        long pk = db.insert(TASKS_TABLE_NAME, null, contentValues);

        return true;
    }

    public boolean updateTask (Long task_id, String taskName, String taskDesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASKS_COL_NAME, taskName);
        contentValues.put(TASKS_COL_DESC, taskDesc);
        db.update(TASKS_TABLE_NAME, contentValues, "_id  = ? ", new String[] { Long.toString(task_id) } );
        return true;
    }

    public Cursor getTask(Long task_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "select * from " + TASKS_TABLE_NAME + " where _id = " + task_id.toString();
        Cursor result = db.rawQuery( sqlQuery, null );
        if(result.getCount() > 0) {
            result.moveToFirst();
        }
        return result;
    }

    public Integer deleteTask (Long task_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TASKS_TABLE_NAME,
                "_id = ? ",
                new String[] { Long.toString(task_id) });
    }

    public Cursor getAllTasks()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + TASKS_TABLE_NAME, null );
    }
/*
    public ArrayList<TaskObject> getAlltasks()
    {
        ArrayList<TaskObject> TOlist = new ArrayList<TaskObject>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TASKS_TABLE_NAME, null );
        try {
            res.moveToFirst();

            while (!res.isAfterLast()) {

                String taskName = res.getString(res.getColumnIndex(TASKS_COL_NAME));
                String taskDesc = res.getString(res.getColumnIndex(TASKS_COL_DESC));

                TaskObject to = new TaskObject(taskName, taskDesc);
                TOlist.add(to);

                res.moveToNext();
            }
        }
        finally {
            res.close();
        }


        return TOlist;
    }
*/

}
