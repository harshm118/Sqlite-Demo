package sharechat.video.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySqliteDb mySqliteDb=new MySqliteDb(this);

//        mySqliteDb.addContact("Harsh","6352873392");
//        mySqliteDb.addContact("Dhara","9328333899");
//        mySqliteDb.addContact("Ankit","8754655644");
//        mySqliteDb.addContact("Rekha","9316761911");

//        Contact contact=new Contact();
//        contact.id=1;
//        contact.number="9106645324";
//        contact.name="Harsh Maniya";
//
//
//        mySqliteDb.updatecontect(contact);

        mySqliteDb.delete(2);

        ArrayList<Contact>arrayList=mySqliteDb.fatchContact();

        for (int i=0;i<arrayList.size();i++){

            Log.e("CONTECT_INFO", "Name: "+arrayList.get(i).name + " Number: "+arrayList.get(i).number);
        }

    }
}