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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
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

    RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    RefreshLayout refreshLayout;
    private List<VideoEntity> datas = new ArrayList<>();
    private int pageNum = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        refreshLayout = v.findViewById(R.id.refreshLayout);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        videoAdapter = new VideoAdapter(getActivity(), datas);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                GetVideoList(true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNum++;
                GetVideoList(false);
            }
        });
        GetVideoList(true);

        recyclerView.setAdapter(videoAdapter);
        return v;
    }

    private void GetVideoList(final boolean isRefresh) {
        String token = getStringFromSp("token");
        // List<VideoEntity> videoEntityList = new ArrayList<VideoEntity>();
        if (!StringUtils.IsStringEmpty(token)) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("token", token);
            hashMap.put("page", pageNum);
            hashMap.put("limit", ApiConfig.PAGE_SIZE);
            //   params.put("categoryId", categoryId);
            Api.config(ApiConfig.NEWS_LIST, hashMap).GetRequest(getActivity(), new TtitCallBack() {
                @Override
                public void OnSuccess(String res) {
                    getActivity().runOnUiThread(

                            new Runnable() {

                                public void run() {

                                    if (isRefresh) {
                                        refreshLayout.finishRefresh(true);
                                    } else {
                                        refreshLayout.finishLoadMore(true);
                                    }

                                    //showToastSync(res);
                                    //转化JSon 数据
                                    VideoListResponse videoListResponse = new Gson().fromJson(res, VideoListResponse.class);
                                    if (videoListResponse != null && videoListResponse.getCode() == 0) {
                                        //不对空， 并且Code 为0 说明请求成功
                                        List<VideoEntity> list = videoListResponse.getPage().getList();
                                        if (list != null && list.size() > 0) {
                                            if (isRefresh) {
                                                datas = list;
                                            } else {
                                                datas.addAll(list);
                                            }
                                            videoAdapter.setDatas(list);
                                            videoAdapter.notifyDataSetChanged();
                                        } else {
                                            if (isRefresh) {

                                                showToast("暂时无数据");
                                            } else {
                                                showToast("没有更多数据");
                                            }
                                        }
//                        List<VideoEntity> MyvideoEntityList = videoListResponse.getPage().getList();
//
//                        for (VideoEntity items:MyvideoEntityList) {
//                            videoEntityList.add(items);
//                        }
                                        //因为在子线程中通过网络请求获取数据,需要在主线程 用dapter 更新recycleview


                                    }
                                }
                            }
                    );


                }

                @Override
                public void onFailure(Exception exception) {
                    //关闭加载动画
                    if (isRefresh) {
                        refreshLayout.finishRefresh(true);
                    } else {
                        refreshLayout.finishLoadMore(true);
                    }
                }
            });


        } else {
            Navigate(LoginActivity.class);

        }
        // return videoEntityList;

    }
}