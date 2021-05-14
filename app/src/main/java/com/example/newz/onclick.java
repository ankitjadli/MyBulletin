package com.example.newz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class onclick extends AppCompatActivity {

    private DatabaseReference mdatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick);

       final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("count");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("count");
        }

      final  String news_id;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                news_id= null;
            } else {
                news_id= extras.getString("news_id");
            }
        } else {
            news_id= (String) savedInstanceState.getSerializable("news_id");

        }

        mdatabse = FirebaseDatabase.getInstance().getReference().child(newString);

        mdatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String   image = dataSnapshot.child(news_id).child("image").getValue().toString();
                String   title = dataSnapshot.child(news_id).child("title").getValue().toString();
                String   description = dataSnapshot.child(news_id).child("desc").getValue().toString();
                TextView news_title = (TextView) findViewById(R.id.topic);
                ImageView news_Image = (ImageView) findViewById(R.id.imageeee);
                TextView news_description = (TextView) findViewById(R.id.descccc);

                news_title.setText(title);
                news_description.setText(description);
               Picasso.get().load(image).placeholder(R.drawable.ic_launcher_background).into(news_Image);
             //   Picasso.with().load(image).into(news_Image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
