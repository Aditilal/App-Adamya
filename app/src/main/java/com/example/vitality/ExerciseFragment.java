package com.example.vitality;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExerciseFragment extends Fragment {
    Button ajaneyasana,bridgePose,forwardFold,plankPose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_exercise,container,false);
        ajaneyasana = v.findViewById(R.id.ajaneyasana);
        bridgePose = v.findViewById(R.id.bridgePose);
        forwardFold = v.findViewById(R.id.forwardFold);
        plankPose = v.findViewById(R.id.plankPose);


        Open();
        return v;
    }
    public void Open()
    {
        ajaneyasana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/0MocS7WERnM")));
            }
        });
        bridgePose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/kkGY3xBnaGc")));
            }
        });
        forwardFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/fC9XQWc6ukk")));
            }
        });
        plankPose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Mc-38vuwE1Y")));
            }
        });
    }


}
