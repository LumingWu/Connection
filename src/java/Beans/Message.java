package Beans;

import java.io.Serializable;

public class Message implements Serializable {
    
    private int messageid;
    private String date;
    private String subject;
    private String content;
    private int sender;
    private int receiver;
    
    public Message() {
        
    }
    
    public void setReceiver(int r){
        receiver = r;
    }
    
    public int getReceiver(){
        return receiver;
    }
    
    public void setSender(int s){
        sender = s;
    }
    
    public int getSender(){
        return sender;
    }
    
    public void setContent(String c){
        content = c;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setSubject(String s){
        subject = s;
    }
    
    public String getSubject(){
        return subject;
    }
    
    public void setDate(String d){
        date = d;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setMessageid(int m){
        messageid = m;
    }
    
    public int getMessageid(){
        return messageid;
    }
    
    
}
