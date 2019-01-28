package com.ss.android.mediaplayersample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapterDetailedPlayer extends RecyclerView.Adapter<MyAdapterDetailedPlayer.MyViewHolder>{

    private List<Message> mDataset;
    private ListItemClickListener mListListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (mListListener != null) {
                mListListener.onListItemOnClicked(pos);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemOnClicked(int clickedItemIndex);
    }

    public MyAdapterDetailedPlayer(List<Message> messages, ListItemClickListener listListener){
        mDataset = messages;
        mListListener = listListener;
    }

    @Override
    public MyAdapterDetailedPlayer.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int id = R.layout.single_img;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(id,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position){
        String url = mDataset.get(position).getUrlImg();
        Glide.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
