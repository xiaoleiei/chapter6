package com.ss.android.mediaplayersample;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

public class RecycleViewPlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManger;
    private List<Message> mDataset;

    private static final String TAG = "ItsSebb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        mManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManger);

        try{
            InputStream inputStream = getAssets().open("data.xml");
            mDataset = PullParser.pull2xml(inputStream);
//            Log.d(TAG,mDataset.size()+"");

            mAdapter = new MyAdapterRecyclePlayer(mDataset);
            recyclerView.setAdapter(mAdapter);

//            Log.d(TAG,"no"+"");

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    int position = ((LinearLayoutManager) mManger).findFirstVisibleItemPosition();

                    Log.d(TAG,position+"");

                    try{
                        MyAdapterRecyclePlayer.viewHolderRvs.get(position+1).getVideoPlayer().startPlayLogic();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }
        catch (Exception e){
            Log.d(TAG,"Wrong!");
        }
    }
}
