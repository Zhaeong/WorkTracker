package io.github.zhaeong.tasktracker.TaskConstructs;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import io.github.zhaeong.tasktracker.MainActivity;
import io.github.zhaeong.tasktracker.R;

/**
 * A custom adaptor for tasks
 * Created by Owen on 2017-01-24.
 */

public class TaskAdapter extends CursorAdapter{

    private ToggleButton mTaskActivationToggle;

    Context context;

    public TaskAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_task_row, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {
        // Find fields to populate in inflated template

        TextView taskRowname = (TextView)view.findViewById(R.id.taskName);
        TextView taskRowHours = (TextView)view.findViewById(R.id.taskHours);

        Long nTimeElapsed = cursor.getLong(cursor.getColumnIndexOrThrow(CustomDBHelper.TASKS_ELAPSED));

        // Extract properties from cursor
        String taskName = cursor.getString(cursor.getColumnIndexOrThrow(CustomDBHelper.TASKS_COL_NAME));
        taskRowHours.setText(MainActivity.convertLongToString(nTimeElapsed));

        taskRowname.setText(taskName);
    }


}
