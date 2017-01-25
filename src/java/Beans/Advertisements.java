package Beans;

import java.io.Serializable;

public class Advertisements implements Serializable {
    
    private int advertisementid;
    private String employeeid;
    private String type;
    private String date;
    private String company;
    private String itemname;
    private String content;
    private double unitprice;
    private int units;
    
    public Advertisements() {
    
    }
    
    public void setUnits(int u){
        units = u;
    }
    
    public int getUnits(){
        return units;
    }
    
    public void setUnitprice(double u){
        unitprice = u;
    }
    
    public double getUnitprice(){
        return unitprice;
    }
    
    public void setContent(String c){
        content = c;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setItemname(String i){
        itemname = i;
    }
    
    public String getItemname(){
        return itemname;
    }
    
    public void setCompany(String c){
        company = c;
    }
    
    public String getCompany(){
        return company;
    }
    
    public void setType(String t){
        type = t;
    }
    
    public String getType(){
        return type;
    }
    
    public void setEmployeeid(String e){
        employeeid = e;
    }
    
    public String getEmployeeid(){
        return employeeid;
    }
    
    public void setAdvertisementid(int a){
        advertisementid = a;
    }
    
    public int getAdvertisementid(){
        return advertisementid;
    }

    public void setDate(String d) {
        date = d;
    }
    
    public String getDate(){
        return date;
    }
    
}
