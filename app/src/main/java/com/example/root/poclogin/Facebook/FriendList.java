package com.example.root.poclogin.Facebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.poclogin.R;

import java.util.ArrayList;

public class FriendList extends AppCompatActivity {

    ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        Bundle bundle = getIntent().getExtras();
        final ArrayList<Friend> arraylist;
        assert bundle != null;
        arraylist = bundle.getParcelableArrayList("friends");

        ArrayList<String> values = new ArrayList<String>();
        for (Friend friend : arraylist){
            values.add(friend.getName());
        }
        
        listView = (ListView) findViewById(R.id.list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                Toast.makeText(getApplicationContext(),
                        "Id Friend :"+arraylist.get(position).getId()+",  Name Friend : " +arraylist.get(position).getName(), Toast.LENGTH_LONG)
                        .show();

            }

        });

    }
}
