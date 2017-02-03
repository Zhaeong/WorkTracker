package io.github.zhaeong.worktracker.TaskConstructs;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Locale;

import io.github.zhaeong.worktracker.R;

/**
 * A custom adaptor for tasks
 * Created by Owen on 2017-01-24.
 */

public class TaskInfoAdapter extends CursorAdapter{
    Context context;

    public TaskInfoAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_taskinfo_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView taskRowName = (TextView)view.findViewById(R.id.taskName);

        TextView taskRowHours = (TextView)view.findViewById(R.id.taskHours);

        // Extract properties from cursor
        String taskName = cursor.getString(cursor.getColumnIndexOrThrow(CustomDBHelper.TASKS_COL_NAME));
        Long nTimeElapsed = cursor.getLong(cursor.getColumnIndexOrThrow(CustomDBHelper.TASKS_ELAPSED));


        taskRowName.setText(taskName);
        taskRowHours.setText(convertLongToString(nTimeElapsed));
    }

    static public String convertLongToString(long nTimeValue)
    {
        int nSeconds = (int) (nTimeValue / 1000) % 60;
        int nMinutes = (int) (nTimeValue / (1000 * 60)) % 60;
        int nHours = (int) (nTimeValue / (1000 *60 * 60));
        return String.format(Locale.CANADA, "%d h %d m %d s",nHours, nMinutes, nSeconds);
    }

/*
    public View getView(int position, View convertView, ViewGroup parent) {

        TaskObject taskObj = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task_row, parent, false);
        }

        TextView taskRowname = (TextView)convertView.findViewById(R.id.taskName);
        taskRowname.setText(taskObj.Name);

        return convertView;

    }*/
}
