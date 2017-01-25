package Beans;

import java.io.Serializable;

public class Employee implements Serializable {
    
    private String ssn;
    private String password;
    private String firstname;
    private String lastname;
    private String zipcode;
    private String address;
    private String city;
    private String state;
    private String telephone;
    private String startdate;
    private double hourlyrate;
    
    public Employee() {
        
    }
    
    public void setSsn(String s){
        ssn = s;
    }
    
    public String getSsn(){
        return ssn;
    }
    
    public void setPassword(String p){
        password = p;
    }
    
    public String getPassword(){
        return password;
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

    public void setStartdate(String string) {
        startdate = string;
    }
    
    public String getStartdate(){
        return startdate;
    }

    public void setHourlyrate(double aDouble) {
        hourlyrate = aDouble;
    }
    
    public double getHourlyrate(){
        return hourlyrate;
    }
    
}
