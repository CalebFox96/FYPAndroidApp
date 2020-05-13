package com.example.patienthealthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        DBHelper db = new DBHelper(this);


        Log.d("Reading: ", "Reading all contacts..");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        List<Temp> contacts = db.getAllContacts();
        DataPoint[] dp = new DataPoint[contacts.size()];
        DataPoint[] dp2 =new DataPoint[contacts.size()];
        DataPoint[] dp3 =new DataPoint[contacts.size()];
        DataPoint[] dp4 =new DataPoint[contacts.size()];
        List<DataPoint> dataPoints = new ArrayList<DataPoint>();
        int i=0;
        for (Temp cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Temp: " + cn.gettemp() + " ,Hum: " +
                    cn.gethum() + " ,Time: " + cn.getbps() + " ,Bps: " + cn.getbp() + " ,BP: " +
                    cn.gettime();
            dp[i] = new DataPoint(i,Float.parseFloat(cn.gettemp()));
            dp2[i] = new DataPoint(i,Integer.parseInt(cn.gethum()));
            dp3[i] = new DataPoint(i,Integer.parseInt(cn.getbps()));
            dp4[i] = new DataPoint(i,Integer.parseInt(cn.getbp()));

            i=i+1;
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(dp2);
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(dp3);
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(dp4);
        series.setColor(Color.RED);
        series.setTitle("Temp");
        series.setDrawDataPoints(true);
        series2.setDrawDataPoints(true);

        series2.setColor(Color.GREEN);
        series2.setTitle("Hum");
        series3.setColor(Color.BLUE);
        series3.setTitle("BPS");
        series3.setDrawDataPoints(true);
        series4.setColor(Color.BLACK);
        series4.setTitle("BP");
        series4.setDrawDataPoints(true);
        graph.addSeries(series);
        graph.getViewport().setScalable(true);

        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling

        graph.addSeries(series2);
        graph.addSeries(series3);
        graph.addSeries(series4);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

    }
    }


