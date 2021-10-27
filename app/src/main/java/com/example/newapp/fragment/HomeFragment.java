package com.example.newapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newapp.R;
import com.example.newapp.adapter.HomeAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private final ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"热门", "iOS", "Android", "前端", "后端", "设计"};
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        slidingTabLayout = view.findViewById(R.id.slidingTabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (String title : mTitles) {
            mFragments.add(VideoFragment.newInstance(title));
        }


        //预加载Fragment,当点击tab 切换fragment 不预加载会Crash
        viewPager.setOffscreenPageLimit(mFragments.size());

        viewPager.setAdapter(new HomeAdapter(getChildFragmentManager(), mTitles, mFragments));


        slidingTabLayout.setViewPager(viewPager);
    }
}