package Beans;

import java.io.Serializable;

public class User implements Serializable {
    
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String telephone;
    private String email;
    private String preferences;
    private String rating;
    private int userid;
    
    public User() {
        
    }
    
    public void setRating(String r){
        rating = r;
    }
    
    public String getRating(){
        return rating;
    }
    
    public void setPreferences(String p){
        preferences = p;
    }
    
    public String getPreferences(){
        return preferences;
    }
    
    public void setUserid(int u){
        userid = u;
    }
    
    public int getUserid(){
        return userid;
    }
    
    public void setPassword(String p){
        password = p;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setEmail(String e){
        email = e;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setTelephone(String t){
        telephone = t;
    }
    
    public String getTelephone(){
        return telephone;
    }
    
    public void setZipcode(String z){
        zipcode = z;
    }
    
    public String getZipcode(){
        return zipcode;
    }
    
    public void setState(String s){
        state = s;
    }
    
    public String getState(){
        return state;
    }
    
    public void setCity(String c){
        city = c;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setAddress(String a){
        address = a;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setLastname(String l){
        lastname = l;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public void setFirstname(String f){
        firstname = f;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
}
