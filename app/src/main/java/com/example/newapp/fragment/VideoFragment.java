package com.example.newapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.LoginActivity;
import com.example.newapp.adapter.VideoAdapter;
import com.example.newapp.api.Api;
import com.example.newapp.api.ApiConfig;
import com.example.newapp.api.TtitCallBack;
import com.example.newapp.entity.VideoEntity;
import com.example.newapp.entity.VideoListResponse;
import com.example.newapp.util.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends BaseFragment {


    private String title;

    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        //单例模式，上面实例化完成fragment之后, 要对本地的变量进行赋值，不能直接进行赋值，需要使用当前fragment 进行变量的赋值
        fragment.title = title;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

//        List<VideoEntity> videoEntityList = new ArrayList<VideoEntity>();
//
//        for (int i = 0; i < 10; i++) {
//            VideoEntity videoEntity = new VideoEntity();
//            videoEntity.setVtitle("this is a title" + i);
//            videoEntity.setAuthor("Name" + i);
//            VideoEntity.VideoSocialEntity videoSocialEntity = new VideoEntity.VideoSocialEntity();
//            videoSocialEntity.setCollectnum(i);
//            videoSocialEntity.setCommentnum(i);
//            videoSocialEntity.setLikenum(i);
//            videoEntity.setVideoSocialEntity(videoSocialEntity);
//
//
//            videoEntityList.add(videoEntity);
//        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        GetVideoList(recyclerView);

        return v;
    }

    private void GetVideoList(RecyclerView recyclerView) {
        String token = getStringFromSp("token");

        if (!StringUtils.IsStringEmpty(token)) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("token", token);
            Api.config(ApiConfig.NEWS_LIST, hashMap).GetRequest(getActivity(), new TtitCallBack() {
                @Override
                public void OnSuccess(String res) {


                    //showToastSync(res);
                    //转化JSon 数据
                    VideoListResponse videoListResponse = new Gson().fromJson(res, VideoListResponse.class);
                    if (videoListResponse != null && videoListResponse.getCode() == 0) {
                        //不对空， 并且Code 为0 说明请求成功
                        List<VideoEntity> videoEntityList = videoListResponse.getPage().getList();

                        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), videoEntityList);
                        recyclerView.setAdapter(videoAdapter);
                    }

                }

                @Override
                public void onFailure(Exception exception) {

                }
            });


        } else {
            Navigate(LoginActivity.class);

        }


    }
}