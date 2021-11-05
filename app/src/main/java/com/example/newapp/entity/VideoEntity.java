package com.example.newapp.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VideoEntity implements Serializable {

    @SerializedName("newsId")
    private Integer newsId;
    @SerializedName("newsTitle")
    private String newsTitle;
    @SerializedName("authorName")
    private String authorName;
    @SerializedName("headerUrl")
    private String headerUrl;
    @SerializedName("commentCount")
    private Integer commentCount;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("type")
    private Integer type;
    @SerializedName("imgList")
    private Object imgList;
    @SerializedName("thumbEntities")
    private List<ThumbEntitiesDTO> thumbEntities;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getImgList() {
        return imgList;
    }

    public void setImgList(Object imgList) {
        this.imgList = imgList;
    }

    public List<ThumbEntitiesDTO> getThumbEntities() {
        return thumbEntities;
    }

    public void setThumbEntities(List<ThumbEntitiesDTO> thumbEntities) {
        this.thumbEntities = thumbEntities;
    }

    public static class ThumbEntitiesDTO implements Serializable {
        @SerializedName("thumbId")
        private Integer thumbId;
        @SerializedName("thumbUrl")
        private String thumbUrl;
        @SerializedName("newsId")
        private Integer newsId;

        public Integer getThumbId() {
            return thumbId;
        }

        public void setThumbId(Integer thumbId) {
            this.thumbId = thumbId;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public Integer getNewsId() {
            return newsId;
        }

        public void setNewsId(Integer newsId) {
            this.newsId = newsId;
        }
    }
}
