package com.app.learnau;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BranchAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> branch;

    public BranchAdapter(ArrayList<String> branch, Context context) {
        this.branch = branch;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.branches, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        ViewHolder myHolder = (ViewHolder) holder;
        myHolder.name.setText(branch.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (pos) {
                    case 0:
                        context.startActivity(new Intent(context, CSEActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, EceActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, EEE.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, MECH.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context, CIVIL.class));
                        break;
                    default:
                        context.startActivity(new Intent(context, LogInActivity.class));
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        {
            return branch.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.branchName);

        }
    }
}

