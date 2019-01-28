package com.ss.android.mediaplayersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity implements MyAdapterDetailedPlayer.ListItemClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManger;
    private List<Message> mDataset;

    private static final String TAG = "ItsSebb";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
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

            mAdapter = new MyAdapterDetailedPlayer(mDataset,this);
            recyclerView.setAdapter(mAdapter);
        }
        catch (Exception e){
            Log.d(TAG,"Wrong!");
        }

    }

    @Override
    public void onListItemOnClicked(int clickedItemIndex){
        Message message = mDataset.get(clickedItemIndex);

        Bundle bundle = new Bundle();

        Intent intent = new Intent(this,DetailPlayerActivity.class);

        bundle.putString("info",message.getInfo());
        bundle.putString("title",message.getTitle());
        bundle.putString("video_url",message.getUrlVideo());

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
