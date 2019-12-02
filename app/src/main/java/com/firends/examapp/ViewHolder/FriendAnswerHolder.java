package com.firends.examapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firends.examapp.R;

public class FriendAnswerHolder extends RecyclerView.ViewHolder {

        public TextView _UserName;
        public TextView Point;
    public FriendAnswerHolder(@NonNull View itemView) {
        super(itemView);

        _UserName=itemView.findViewById(R.id.id_userName);
        Point=itemView.findViewById(R.id.id_point);



    }
}
