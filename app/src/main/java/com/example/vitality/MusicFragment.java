package com.example.vitality;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MusicFragment extends Fragment {
    ListView listView;
    String[] items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_music, container, false);
        listView = v.findViewById(R.id.listViewSong);

        runtimePermission();
    return v;
    }

  public void runtimePermission()
  {
      Dexter.withContext(getContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
              .withListener(new PermissionListener() {
                  @Override
                  public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                    displaySongs();
                  }

                  @Override
                  public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                  }

                  @Override
                  public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                       permissionToken.continuePermissionRequest();
                  }
              }).check();
  }

  public ArrayList<File> findSong(File file) {
      ArrayList<File> arrayList = new ArrayList<>();
      File[] files = file.listFiles();

      for (File singlefile : files) {
          if (singlefile.isDirectory() && !singlefile.isHidden()) {
              arrayList.addAll(findSong(singlefile));
          } else {
              if (singlefile.getName().endsWith(".mp3") || singlefile.getName().endsWith(".wav")) {
                  arrayList.add(singlefile);
              }
          }
      }
      return arrayList;
  }
  void displaySongs()
  {
      final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());
       items = new String[mySongs.size()];
      for (int i=0;i<mySongs.size();i++)
      {
          items[i]=mySongs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
      }

//      ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,items);
//
//      listView.setAdapter(myAdapter);
      customAdapter customAdapter=new customAdapter();
      listView.setAdapter(customAdapter);

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String songName=(String) listView.getItemAtPosition(i);
        startActivity(new Intent(getActivity().getApplicationContext(),PlayerAcitivity.class).putExtra("songs",mySongs).putExtra("songname",songName).putExtra("pos",i));
    }
});

  }

  class customAdapter extends BaseAdapter
  {

      @Override
      public int getCount() {
          return items.length;
      }

      @Override
      public Object getItem(int i) {
          return null;
      }

      @Override
      public long getItemId(int i) {
          return 0;
      }

      @Override
      public View getView(int i, View view, ViewGroup viewGroup) {
          View myView = getLayoutInflater().inflate(R.layout.list_item,null);
          TextView textsong=myView.findViewById(R.id.textSongName);
          textsong.setSelected(true);
          textsong.setText(items[i]);
          return myView;
      }
  }
  }

