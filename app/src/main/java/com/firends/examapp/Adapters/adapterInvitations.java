package com.firends.examapp.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firends.examapp.Model.Invitastin;

import java.util.ArrayList;

public class adapterInvitations extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public adapterInvitations(Context context, ArrayList<Invitastin> Invitations){

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
