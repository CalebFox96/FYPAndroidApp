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

    // code to get the single contact
    Temp getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_TEMP,KEY_HUM,KEY_BPS,KEY_BP, KEY_TIME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Temp contact = new Temp(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5));
        /* return contact */
        return contact;
    }

    public List<Temp> getAllContacts() {
        List<Temp> contactList = new ArrayList<Temp>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
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

                DATA_TO_SHOW[i][0]=cursor.getString(0);
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

    // code to update the single contact
    public int updateContact(Temp contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEMP, contact.gettemp());
        values.put(KEY_HUM, contact.gethum());
        values.put(KEY_HUM, contact.getbps());
        values.put(KEY_HUM, contact.getbp());


        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Temp contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();

        // return count
        return cursor.getCount();
    }




}
