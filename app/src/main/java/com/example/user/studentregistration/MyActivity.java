package com.example.user.studentregistration;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements CustomCursorAdapter.OnItemClickListener, CustomCursorAdapter.OnRecycleViewItemClickListener, CustomCursorAdapter.OnRecycleViewLongItemClickListener{

    private CustomCursorAdapter customAdapter;
    private PersonDatabaseHelper databaseHelper;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private final int PERMISSION_REQUEST_CALL_PHONE = 1;
    private RecyclerView listView;
    List<Person> listPerson;

    private static final String TAG = MyActivity.class.getSimpleName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_student);
        listPerson= new ArrayList<>();
        databaseHelper = new PersonDatabaseHelper(this);

        listView = (RecyclerView) findViewById(R.id.list_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);

        // Database query

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                listPerson = getStudentList(databaseHelper.getAllData());
                customAdapter = new CustomCursorAdapter(MyActivity.this, listPerson);
                customAdapter.setOnRecycleViewItemClickListener(MyActivity.this);
                customAdapter.setOnRecycleViewLongItemClickListener(MyActivity.this);
                customAdapter.setOnItemClickListener(MyActivity.this);
                listView.setAdapter(customAdapter);;
            }
        });
    }

    public void onClickEnterData(View btnAdd) {
        startActivityForResult(new Intent(this, EnterDataActivity.class), ENTER_DATA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {

            databaseHelper.insertData(data.getExtras().getString("tag_person_name"), data.getExtras().getString("tag_person_Email"), data.getExtras().getString("tag_person_Phone"), data.getExtras().getString("tag_person_Address"));
            listPerson.clear();
            listPerson = getStudentList(databaseHelper.getAllData());
            customAdapter = new CustomCursorAdapter(MyActivity.this, listPerson);
            customAdapter.setOnRecycleViewItemClickListener(MyActivity.this);
            customAdapter.setOnRecycleViewLongItemClickListener(MyActivity.this);
            customAdapter.setOnItemClickListener(MyActivity.this);
            listView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
        }
    }

    public List<Person> getStudentList(Cursor cursor1){
        List<Person> studentArrayList = new ArrayList<>();
        Cursor cursor = cursor1;
        while (cursor.moveToNext()){
            studentArrayList.add(new Person(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))),
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))),
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))),
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))),
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))));
        }
        return studentArrayList;
    }

    @Override
    public void onItemClick(View v, List<Person> model, int position) {
        String number = "tel:" + model.get(position).getPerson_phone().trim();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));

        //zyrif: Check if we have permission to call. If not, then request for it.
        if(ActivityCompat.checkSelfPermission(MyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MyActivity.this, "App doesn't have CALL_PHONE permission", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(MyActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CALL_PHONE);
        }
        else
            startActivity(callIntent);
    }

    @Override
    public void onRecycleViewItemClick(View v, List<Person> model, int position) {
        Toast.makeText(getApplicationContext(), "Number: "+model.get(position).get_id(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRecycleViewLongItemClick(View v, List<Person> model, int position) {

    }
}