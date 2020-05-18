package com.example.patienthealthmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "patientData";
    private static final String TABLE_CONTACTS = "temperature";
    private static final String KEY_ID = "id";
    private static final String KEY_TEMP = "temperature";
    private static final String KEY_HUM = "hum";
    private static final String KEY_BPS = "bps";
    private static final String KEY_BP = "bp";
    private static final String KEY_TIME = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEMP + " TEXT,"
                + KEY_HUM + " TEXT," + KEY_BPS + " TEXT," + KEY_BP + " TEXT," + KEY_TIME + " DEFAULT CURRENT_TIMESTAMP" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addContact(Temp contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEMP, contact.gettemp()); // Contact Name
        values.put(KEY_HUM, contact.gethum()); // Contact Phone
        values.put(KEY_BPS, contact.getbps());
        values.put(KEY_BP, contact.getbp());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
//Fetches all the rows in a list
    public List<Temp> getAllContacts() {
        //create empty list
        List<Temp> contactList = new ArrayList<Temp>();
        // Select All data
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
       // cursor used when we have to run query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //making object of temp class so we can use the temp functions
                Temp contact = new Temp();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.settemp(cursor.getString(1));
                contact.sethum(cursor.getString(2));
                contact.setbps(cursor.getString(3));
                contact.setbp(cursor.getString(4));

                contact.settime(cursor.getString(5));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
//Fetch all the rows in a 2D stirng array
    public String[][] getContacts(){
         String[][] DATA_TO_SHOW = new String[getContactsCount()][6] ;
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM-HH:mm:ss");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //i is the row and it will keep adding 1 to it
                //saving id at column 0
                DATA_TO_SHOW[i][0]=cursor.getString(0);
                //saving temp at column 1
                DATA_TO_SHOW[i][1]=cursor.getString(1);
                DATA_TO_SHOW[i][2]=cursor.getString(2);
                DATA_TO_SHOW[i][3]=cursor.getString(3);
                DATA_TO_SHOW[i][4]=cursor.getString(4);
                DATA_TO_SHOW[i][5]=cursor.getString(5);

                i=i+1;
            } while (cursor.moveToNext());
        }

        // return contact list
        return DATA_TO_SHOW;
    }


    // Getting contacts Count
    // counts number of rows
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();

        // return count
        return cursor.getCount();
    }




}
