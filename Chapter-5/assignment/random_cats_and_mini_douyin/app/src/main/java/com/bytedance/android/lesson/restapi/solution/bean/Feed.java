package com.bytedance.android.lesson.restapi.solution.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Xavier.S
 * @date 2019.01.20 14:18
 */
public class Feed {

    // TODO-C2 (1) Implement your Feed Bean here according to the response json
    @SerializedName("student_id") private String student_id;
    @SerializedName("user_name") private String user_name;
    @SerializedName("image_url") private String image_url;
    @SerializedName("_id") private String _id;
    @SerializedName("video_url") private String video_url;
    @SerializedName("createdAt") private String createdAt;
    @SerializedName("updatedAt") private String updatedAt;
    @SerializedName("__v") private int __v;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUser_name() { return getUser_name(); }

    public  void setUser_name(String user_name) { this.user_name = user_name; }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) { this.image_url = image_url; }

    public String get_id(){return _id;}

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getCreatedAt(){return createdAt;}

    public String getUpdatedAt(){return updatedAt;}

    public int get__v(){return __v;}

}
