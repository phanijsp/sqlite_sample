package com.example.sqlite_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView messagesview;
    Button SaveButton,ViewButton;
    SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        messagesview = (TextView) findViewById(R.id.allmessagesview);
        SaveButton = (Button) findViewById(R.id.savebutton);
        ViewButton = (Button) findViewById(R.id.viewbutton);
        sqLiteHelper = new SQLiteHelper(this, "Names.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS NAMES(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)");
        messagesview.setMovementMethod(new ScrollingMovementMethod());
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteHelper.queryData("INSERT INTO NAMES(name) VALUES('"+editText.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"Saved Succesfully !",Toast.LENGTH_SHORT).show();
            }
        });
        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messagesview.setText("ALL MESSAGES : ");
                Cursor cursor = sqLiteHelper.getData("SELECT * FROM NAMES");
                while (cursor.moveToNext()) {
                    messagesview.append("\n\n"+cursor.getString(1));
                }
            }
        });
    }
}
