package com.app.learnau.CSE.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learnau.UploadActivity;
import com.app.learnau.R;
import com.app.learnau.ViewPdf;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AiSyllabus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AiSyllabus extends Fragment {
    private RecyclerView recyclerview;
    private ImageView add;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AiSyllabus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AiSyllabus.
     */
    // TODO: Rename and change types and number of parameters
    public static AiSyllabus newInstance(String param1, String param2) {
        AiSyllabus fragment = new AiSyllabus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ai_syllabus, container, false);
        recyclerview =  v.findViewById(R.id.recyclerview3);


        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("My_Documents").child("Ai syllabus") , model.class)
                .build();
        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<model, AiSyllabus.MyViewholder>(options) {
            @NonNull
            @Override
            public AiSyllabus.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);
                return new MyViewholder(view);
            }
            @Override
            protected void onBindViewHolder(@NonNull AiSyllabus.MyViewholder holder, int position, @NonNull model model) {
                holder.fileName.setText(model.getFilename());

                holder.fileName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.fileName.getContext(), ViewPdf.class);
                        intent.putExtra("filename", model.getFilename());
                        intent.putExtra("fileurl", model.getFileurl());
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        holder.fileName.getContext().startActivity(intent);
                    }
                });
            }

        };
        firebaseRecyclerAdapter.startListening();
        recyclerview.setAdapter(firebaseRecyclerAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add = view.findViewById(R.id.addbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),UploadActivity.class);
                startActivity(intent);
            }
        });
    }
    public static class MyViewholder extends RecyclerView.ViewHolder{
        TextView fileName;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.textviewitem3);
        }
    }
}