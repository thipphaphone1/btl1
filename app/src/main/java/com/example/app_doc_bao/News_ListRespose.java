package com.example.app_doc_bao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News_ListRespose {

    @SerializedName("status")
    private  boolean status;
    @SerializedName("msg")
    private String mess;
    @SerializedName("data")
    private List<News> newsList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setCookList(List<News> newsList) {
        this.newsList = newsList;
    }

}
