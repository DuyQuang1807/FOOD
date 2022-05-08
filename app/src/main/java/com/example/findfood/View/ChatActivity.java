package com.example.findfood.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.findfood.Databases.DatabaseStore;
import com.example.findfood.HelperClasses.ChatsAdapter;
import com.example.findfood.R;
import com.example.findfood.model.Chat;
import com.example.findfood.model.Store;
import com.example.findfood.model.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    RecyclerView rcvuserchat;
    private ChatsAdapter chatsAdapter;
    private List<Store> mUsers;
    FirebaseUser fuser;
    DatabaseReference reference;
    DatabaseStore daoStore;
    private List<String> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rcvuserchat = findViewById(R.id.chatuser);
        rcvuserchat.setHasFixedSize(true);
        rcvuserchat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // lấy được user đang login
        daoStore = new DatabaseStore(getApplicationContext());
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        byChats();
        updateToken(FirebaseInstanceId.getInstance().getToken());
    }

    public void byChats() {
        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);

                    if (chat.getSender().equals(fuser.getUid())) {
                        if (!usersList.contains(chat.getReceiver())) {
                            usersList.add(chat.getReceiver());

                        }
                    }
                    if (chat.getReceiver().equals(fuser.getUid())) {
                        if (!usersList.contains(chat.getSender())) {
                            usersList.add(chat.getSender());
                        }
                    }

                }
                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void readChats() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Store");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Store store = snapshot.getValue(Store.class);
                    for (String id : usersList) {

                        if (store.getTokenstore().equalsIgnoreCase(id)) {
                            mUsers.add(store);
                        }
                    }
                }
                chatsAdapter = new ChatsAdapter(getApplicationContext(), mUsers, true);
                rcvuserchat.setAdapter(chatsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void updateToken(String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(fuser.getUid()).setValue(token1);
    }
}
