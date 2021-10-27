package com.example.newapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.adapter.VideoAdapter;
import com.example.newapp.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {


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
        List<VideoEntity> videoEntityList = new ArrayList<VideoEntity>();

        for (int i = 0; i < 10; i++) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setVtitle("this is a title" + i);
            videoEntity.setAuthor("Name" + i);
            VideoEntity.VideoSocialEntity videoSocialEntity = new VideoEntity.VideoSocialEntity();
            videoSocialEntity.setCollectnum(i);
            videoSocialEntity.setCommentnum(i);
            videoSocialEntity.setLikenum(i);
            videoEntity.setVideoSocialEntity(videoSocialEntity);


            videoEntityList.add(videoEntity);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), videoEntityList);
        recyclerView.setAdapter(videoAdapter);
        return v;
    }
}