package com.firends.examapp.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firends.examapp.Adapters.AdapterFriendAnswer;
import com.firends.examapp.Controllers.DataBaseManager;
import com.firends.examapp.R;

import java.util.List;

public class FriendsAnswers extends AppCompatActivity {
    public static RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
    public List<Object> friendAnswersList;
    DataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_answers);

        manager = new DataBaseManager();
        manager.getFriendAnswers(this);

        RecyclerView mRecyclerView = findViewById(R.id.Receycl_FriendsAnswers);


        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView.
        mRecyclerView.setHasFixedSize(true);

        // Specify a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Specify an adapter.
        adapter = new AdapterFriendAnswer(this, manager.getFriendAnswers(this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
