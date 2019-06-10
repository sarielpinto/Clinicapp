package com.example.clinicapp.Mostrar_solo_los_nombre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicapp.R;
import com.example.clinicapp.Mostrar_solo_los_nombre.User2;
import com.example.clinicapp.Mostrar_solo_los_nombre.UserAdapter2;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {
    public RecyclerView recyclerListView;
    public UserAdapter2 myAdapter;
    public  static TextView textViewEmptyView;

    public ProgressBar myProgressBar;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        // creating layout
        creatingLayouts();
    }
    public void creatingLayouts(){
        myProgressBar=(ProgressBar) findViewById(R.id.loader);
        textViewEmptyView = (TextView) findViewById(R.id.tvEmptyView);

        recyclerListView=(RecyclerView) findViewById(R.id.recylerview_list);
        recyclerListView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter= new UserAdapter2(this);
        updateAdapter();
        recyclerListView.setAdapter(myAdapter);
    }

    //add new user to database
    /*public void btnAddOnClick(View v) {

        String name = editTextName.getText().toString().trim();
        String country=editTextCountry.getText().toString().trim();
        double weight=Double.parseDouble(editTextWeight.getText().toString().trim());
        User user= new User(name, country, weight);

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Please enter name",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(country)) {
            Toast.makeText(getApplicationContext(), "Please enter country",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        updateDatabase(user);
*/


    // adding new user to end  the user using on firebase database
    public void updateDatabase(User2 user){

        databaseReference.child("users").push().setValue(user);

        updateAdapter();

    }

    //update adapter
    public void updateAdapter(){

        final List<User2> listUsers= new ArrayList<>();
        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listUsers.add(dataSnapshot.getValue(User2.class));
                displayUsers(listUsers);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Canceled",Toast.LENGTH_SHORT).show();
            }
        });


    }


    //display the user on Adapter
    public void displayUsers(List<User2> ls){
        myProgressBar.setVisibility(View.GONE);
        textViewEmptyView.setVisibility(View.GONE);
        recyclerListView.setVisibility(View.VISIBLE);
        myAdapter.setData(ls);
        myAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}