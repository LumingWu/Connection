
package Beans;

import java.io.Serializable;

public class Sales implements Serializable {
    
    private int transactionid;
    private String date;
    private int advertisementid;
    private int units;
    private String accountnumber;
    private String employeeid;
    
    public Sales() {
        
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAdvertisementid(int advertisementid) {
        this.advertisementid = advertisementid;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }
    

    public int getTransactionid() {
        return transactionid;
    }

    public String getDate() {
        return date;
    }

    public int getAdvertisementid() {
        return advertisementid;
    }

    public int getUnits() {
        return units;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public String getEmployeeid() {
        return employeeid;
    }


}
