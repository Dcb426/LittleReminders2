package cs316.project_1.littlereminders;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.UUID;

import cs316.project_1.littlereminders.Database.DataSource;
import cs316.project_1.littlereminders.Database.DataStudent;


public class addNotification extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button add;
    public setNotification note;
    public EditText title;
    public  EditText text;
    public EditText amountt;
    public  EditText timmes;
    //private rDataScource mDataSource;
    DataSource mDataSource;
    public TextView txxs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);


        add = findViewById(R.id.add1);
        title = findViewById(R.id.addTitle);
        text = findViewById(R.id.addText);
        amountt = findViewById(R.id.amount);
        timmes = findViewById(R.id.tpd);
        txxs = findViewById(R.id.textView3);
        mDataSource = new DataSource(this);
        mDataSource.open();
        toastMessage("Database Created");

        /*try {
            mDataSource = new rDataScource(this);
            mDataSource.open();

        } catch (SQLiteException e) {
            e.printStackTrace();
        }*/

        add.setOnClickListener(new View.OnClickListener() {
             DataStudent d = new DataStudent();
            @Override
            public void onClick(View view) {

                if(Integer.parseInt(timmes.getText().toString()) > 0 && Integer.parseInt(timmes.getText().toString()) < 4 && Integer.parseInt(amountt.getText().toString()) > 0 ) {
                    d.setID(UUID.randomUUID().toString());
                    d.setstudentTitle(title.getText().toString());
                    d.setstudentText(text.getText().toString());
                    d.setAmount(Integer.parseInt(amountt.getText().toString()));
                    d.setTime(Integer.parseInt(timmes.getText().toString()));
                    try {
                        mDataSource.createStudent(d);
                        //txxs.setText("amount: " + mDataSource.getdataItemCount());
                        startAct();
                    } catch (SQLiteException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(addNotification.this,
                            "Invalid Notification", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void startAct() {

        Intent gohome = new Intent(this, navigation.class);
        startActivity(gohome);
    }

    @Override

    protected void onPause()

    {

        super.onPause();

        mDataSource.close();

    }



    @Override

    protected void onResume()

    {

        super.onResume();

        mDataSource.open();

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
