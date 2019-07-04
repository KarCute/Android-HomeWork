package com.bytedance.android.lesson.restapi.solution.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Xavier.S
 * @date 2019.01.18 17:53
 */
public class PostVideoResponse {

    // TODO-C2 (3) Implement your PostVideoResponse Bean here according to the response json

//    @SerializedName("success") private boolean success;
//    @SerializedName("item") private Feed item;
//
//    public boolean getSuccess() { return success; }
//
//    public void setSuccess(boolean success){ this.success = success; }
//
//    public Feed getItem(){ return item; }
//
//    public void setItem(Feed item){ this.item = item; }
    private Object result;
    private String url;
    private boolean success;

    public Object getResult(){return result;}
    public String getUrl(){return url;}
    public boolean getSuccess(){return success;}

}
