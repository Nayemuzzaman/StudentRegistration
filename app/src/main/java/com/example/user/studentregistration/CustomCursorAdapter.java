package com.example.user.studentregistration;



import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
       //view & adapter
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.row_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        TextView textViewPersonName = (TextView) view.findViewById(R.id.tv_person_name);
        textViewPersonName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView textViewPersonEmail = (TextView) view.findViewById(R.id.tv_person_email);
        textViewPersonEmail.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

        TextView textViewPersonPhone = (TextView) view.findViewById(R.id.tv_person_phone);
        textViewPersonPhone.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));

        TextView textViewPersonAddress = (TextView) view.findViewById(R.id.tv_person_address);
        textViewPersonAddress.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));


    }
}
