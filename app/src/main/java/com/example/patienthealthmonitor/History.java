package com.example.patienthealthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnModel;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class History extends AppCompatActivity {
    private static final String[] TABLE_HEADERS = { "S.No","Temp","Hum","BPM","BP","Time" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        DBHelper db = new DBHelper(this);
        String[][] abc = db.getContacts();


        Log.d("Reading: ", "Reading all contacts..");
        TableView<String[]> tableView = (TableView<String[]>) findViewById(R.id.table);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(6);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        columnModel.setColumnWeight(3, 1);
        columnModel.setColumnWeight(4, 1);
        columnModel.setColumnWeight(5, 1);
        columnModel.setColumnWeight(6, 3);
        tableView.setColumnModel(columnModel);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this,TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, abc));








    }
}
