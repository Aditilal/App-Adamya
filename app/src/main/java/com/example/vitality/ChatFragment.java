package com.example.vitality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatFragment extends Fragment {
    ImageView covidClick,depression_click,internet_click,stress_click;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chats,container,false);
        covidClick = v.findViewById(R.id.covidClick);
        depression_click = v.findViewById(R.id.depression_click);
        stress_click = v.findViewById(R.id.stress_click);
        internet_click = v.findViewById(R.id.internet_click);
        Run();
        return v;
    }
    public void Run()
    {
        covidClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getContext(),Covid19.class);
                startActivity(intent);
            }
        });
        depression_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getContext(),depression.class);
                startActivity(intent);
            }
        });
        stress_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getContext(),Stress.class);
                startActivity(intent);
            }
        });
        internet_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getContext(),Internet.class);
                startActivity(intent);
            }
        });

    }

}
