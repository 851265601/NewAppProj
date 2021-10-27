package com.example.newapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.entity.VideoEntity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private List<VideoEntity> datas;

    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    public VideoAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //把item 绑定这个viewholder
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //绑定 数据到viewholder中

        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
        vh.tv_title.setText(videoEntity.getVtitle());
        vh.tv_author.setText(videoEntity.getAuthor());
        vh.tv_collect.setText(String.valueOf(videoEntity.getVideoSocialEntity().getCollectnum()));
        vh.tv_comment.setText(String.valueOf(videoEntity.getVideoSocialEntity().getCommentnum()));
        vh.tv_dz.setText(String.valueOf(videoEntity.getVideoSocialEntity().getLikenum()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tv_title;
    TextView tv_author;
    TextView tv_comment;
    TextView tv_collect;
    TextView tv_dz;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.title);
        tv_author = itemView.findViewById(R.id.author);
        tv_comment = itemView.findViewById(R.id.comment);
        tv_collect = itemView.findViewById(R.id.collect);
        tv_dz = itemView.findViewById(R.id.dz);

    }

    @Override
    public void onClick(View v) {

    }
}



