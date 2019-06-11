package com.example.clinicapp.Mostrar_solo_los_nombre;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicapp.Contact.*;
import com.example.clinicapp.R;
import com.example.clinicapp.especialistas.Contact2;
import com.example.clinicapp.pruebadefirebase.fire;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import com.example.clinicapp.Contact;
public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.MyViewHolder> {
Contact2 contact2;
Contact contact;
    private final Context mContext;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<User2> userList = new ArrayList<>();

    public UserAdapter2(Context context) {
        mContext = context;
    }

    public void setData(List<User2> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public UserAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item2, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final UserAdapter2.MyViewHolder holder, final int position) {
        final User2 user = userList.get(position);

        holder.myTextView.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, fire.class);
                intent.putExtra("Hola",userList.get(position).toString());
                //intent.putExtra("valor",contact.getEspecialidad());
                //intent.putExtra("valor2",contact.getName());
                mContext.startActivity(intent);
                Toast.makeText(mContext, " " + userList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });


        // delete user from firebase database based upon the key
       /* holder.myButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Query deleteQuery = databaseReference.child("users").orderByChild("name").equalTo(userList.get(position).toString());
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User userTemp = snapshot.getValue(User.class);
                            if (user.getName().equals(userTemp.getName())) {
                                databaseReference.child("users").child(snapshot.getKey().toString()).removeValue();
                                userList.remove(position);
                                notifyDataSetChanged();
                                if (userList.size() == 0) {
                                    fire.textViewEmptyView.setVisibility(View.VISIBLE);
                                }
                                break;

                            }

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView myTextView;


        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.tvName);


        }
    }
}
