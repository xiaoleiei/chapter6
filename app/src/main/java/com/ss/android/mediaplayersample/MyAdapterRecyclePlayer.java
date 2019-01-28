package com.ss.android.mediaplayersample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRecyclePlayer extends RecyclerView.Adapter<MyAdapterRecyclePlayer.MyViewHolderRv> {

    private List<Message> mDateSet;

    public static List<MyViewHolderRv> viewHolderRvs = new ArrayList<>();

    public class MyViewHolderRv extends RecyclerView.ViewHolder{

        public StandardGSYVideoPlayer getVideoPlayer() {
            return videoPlayer;
        }

        private StandardGSYVideoPlayer videoPlayer;
        private View root;

        private MyViewHolderRv(View v){
            super(v);
            videoPlayer = v.findViewById(R.id.rv_video_player);
            root = v;
        }
    }

    public MyAdapterRecyclePlayer(List<Message> messages){
        mDateSet = messages;
    }

    @Override
    public MyAdapterRecyclePlayer.MyViewHolderRv onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int id = R.layout.single_vid;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(id,parent,false);

        MyAdapterRecyclePlayer.MyViewHolderRv viewHolder = new MyAdapterRecyclePlayer.MyViewHolderRv(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapterRecyclePlayer.MyViewHolderRv viewHolder, int position){
        String urlVid = mDateSet.get(position).getUrlVideo();
        String urlImg = mDateSet.get(position).getUrlImg();
        String title = mDateSet.get(position).getTitle();

        View root = viewHolder.root;
        StandardGSYVideoPlayer videoPlayer = viewHolder.videoPlayer;

        ImageView imageView = new ImageView(root.getContext());
        Glide.with(imageView.getContext()).load(urlImg).into(imageView);

        videoPlayer.setUp(urlVid, true, title);
        videoPlayer.setThumbImageView(imageView);
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.setIsTouchWiget(true);

        try{
            viewHolderRvs.get(position);
        }catch (Exception e){
            viewHolderRvs.add(position,viewHolder);
        }

//        videoPlayer.startPlayLogic();
//        OrientationUtils orientationUtils = new OrientationUtils()
    }

    @Override
    public int getItemCount() {
        return mDateSet.size();
    }
}
