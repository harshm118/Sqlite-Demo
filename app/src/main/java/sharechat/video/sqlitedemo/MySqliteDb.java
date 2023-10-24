package sharechat.video.sqlitedemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MySqliteDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactDb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACT = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";


    public MySqliteDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        db.execSQL("CREATE TABLE " + TABLE_CONTACT +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_NUMBER + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACT);
        onCreate(db);

    }


    public void addContact(String name,String number){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_NUMBER,number);

        db.insert(TABLE_CONTACT,null,values);
    }


    public ArrayList<Contact> fatchContact(){


        SQLiteDatabase db=this.getReadableDatabase();
       @SuppressLint("Recycle") Cursor cursor= db.rawQuery("SELECT * FROM "+TABLE_CONTACT,null);

       ArrayList<Contact>contacts=new ArrayList<>();
       while (cursor.moveToNext()){
           Contact contact=new Contact();
           contact.id=cursor.getInt(0);
           contact.name=cursor.getString(1);
           contact.number=cursor.getString(2);
           contacts.add(contact);

       }
       return contacts;
    }

    public void updatecontect(Contact contact1){


        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NUMBER,contact1.number);
        cv.put(KEY_NAME,contact1.name);
        database.update(TABLE_CONTACT,cv,KEY_ID+" = "+contact1.id,null);
    }
    public void delete(int id){

        SQLiteDatabase database=this.getWritableDatabase();

        database.delete(TABLE_CONTACT,KEY_ID+" = ? ",new String[]{String.valueOf(id)});

    }
}
