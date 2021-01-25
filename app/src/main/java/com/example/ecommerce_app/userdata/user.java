package com.example.ecommerce_app.userdata;

public class user {
   private String useremail ;
   private String password;

    public user() {
    }

    public user(String useremail, String password) {
        this.useremail = useremail;
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
