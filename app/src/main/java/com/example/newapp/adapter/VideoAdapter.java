package com.example.newapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.entity.VideoEntity;
import com.example.newapp.view.CircleTransform;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private List<VideoEntity> mdatas;


    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.mdatas = datas;
    }

    public void setDatas(List<VideoEntity> datas) {
        this.mdatas = datas;
    }

    public VideoAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //把item 绑定这个viewholder
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //绑定 数据到viewholder中

        MyViewHolder vh = (MyViewHolder) holder;
        VideoEntity videoEntity = mdatas.get(position);
        vh.tv_title.setText(videoEntity.getNewsTitle());
        vh.tv_author.setText(videoEntity.getAuthorName());
        // vh.tv_collect.setText(String.valueOf(videoEntity.().size()));
        vh.tv_comment.setText(String.valueOf(videoEntity.getCommentCount()));
        vh.tv_dz.setText(String.valueOf(videoEntity.getThumbEntities().size()));

        //
        Picasso.with(mContext)
                .load(videoEntity.getHeaderUrl())
                .transform((Transformation) new CircleTransform())
                .into(vh.img_header);
        Picasso.with(mContext).load(videoEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.img_cover);
        //   vh.mPosition = position;
    }

    @Override
    public int getItemCount() {

        if (mdatas != null && mdatas.size() > 0) {
            return mdatas.size();
        } else {
            return 0;
        }


    }
}


class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_title;
    public TextView tv_author;
    public TextView tv_comment;
    public TextView tv_collect;
    public TextView tv_dz;
    public ImageView img_header;
    public ImageView img_cover;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.title);
        tv_author = itemView.findViewById(R.id.author);
        tv_comment = itemView.findViewById(R.id.comment);
        tv_collect = itemView.findViewById(R.id.collect);
        tv_dz = itemView.findViewById(R.id.dz);
        img_header = itemView.findViewById(R.id.img_header);
        img_cover = itemView.findViewById(R.id.img_cover);
    }

    @Override
    public void onClick(View v) {

    }
}



