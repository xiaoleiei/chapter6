package com.ss.android.mediaplayersample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * @author tianye.xy@bytedance.com
 * 2019/1/9
 */
public class DetailPlayerActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {
    private static final String TAG = "ItsSebb";

    private StandardGSYVideoPlayer detailPlayer;
    private TextView textView;
    private Bundle bundle;
    private String videoUrl;
    private String imgUrl;
    private String info;
    private String title;

//    private String url = "https://res.exexm.com/cw_145225549855002";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_detail_player);

        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        textView = findViewById(R.id.info_textview);
        bundle = getIntent().getExtras();

        try{
            info = bundle.getString("info");
            title = bundle.getString("title");
            videoUrl = bundle.getString("video_url");
            imgUrl = bundle.getString("img_url");
        }
        catch(Exception e){
            Log.d(TAG,"wrong!");
        }

        //增加title
//        detailPlayer.getTitleTextView().setVisibility(View.GONE);
//        detailPlayer.getBackButton().setVisibility(View.GONE);

        detailPlayer.getTitleTextView().setText(title);
        detailPlayer.getBackButton().setVisibility(View.GONE);

        textView.setText(info);

        initVideoBuilderMode();
    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
//        loadCover(imageView, imgUrl);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(videoUrl)
                .setCacheWithPlay(true)
                .setVideoTitle(title)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(true)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }


    /**
     * 是否启动旋转横屏，true表示启动
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    private void loadCover(ImageView imageView, String url) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(3000000)
                                .centerCrop()
                                .error(R.mipmap.xxx2)
                                .placeholder(R.mipmap.xxx1))
                .load(url)
                .into(imageView);
    }

}
