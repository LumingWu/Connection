package Beans;

import java.io.Serializable;

public class UserPosts implements Serializable {
    
    private int postid;
    private String date;
    private String content;
    private int commentcount;
    private int likes;
    private int postby;
    private int postson;
    
    public UserPosts() {
        
    }
    
    public void setPostson(int p){
        postson = p;
    }
    
    public int getPostson(){
        return postson;
    }
    
    public void setPostby(int p){
        postby = p;
    }
    
    public int getPostby(){
        return postby;
    }
    
    public void setLikes(int l){
        likes = l;
    }
    
    public int getLikes(){
        return likes;
    }
    
    public void setCommentcount(int c){
        commentcount = c;
    }
    
    public int getCommentcount(){
        return commentcount;
    }
    
    public void setContent(String c){
        content = c;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setDate(String d){
        date = d;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setPostid(int p){
        postid = p;
    }
    
    public int getPostid(){
        return postid;
    }
    
}
