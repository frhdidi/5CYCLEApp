package com.example.test5cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class HomePageFragment extends Fragment {

    ImageView recycle;
    ImageView reduce;
    ImageView reuse;
    ImageView repair;
    ImageView refuse;

    //fragment of homepage
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);

        recycle = v.findViewById(R.id.recycle);
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Recycle.class);
                startActivity(intent);
            }
        });

        reduce = v.findViewById(R.id.reduce);
        reduce.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Reduce.class);
                startActivity(intent);
            }
        });

        reuse = v.findViewById(R.id.reuse);
        reuse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Reuse.class);
                startActivity(intent);
            }
        });

        repair = v.findViewById(R.id.repair);
        repair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Repair.class);
                startActivity(intent);
            }
        });

        refuse = v.findViewById(R.id.refuse);
        refuse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Refuse.class);
                startActivity(intent);
            }
        });



        return v;
}
}