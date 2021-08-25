package com.example.alumni;

public class UserData {
    private String srn;
    private  String fullname;
    private  String email;
    private String phone;
    private String passedyear;
    private String password;
    private String school;
    private String image;
    private String message;

    public UserData(){

    }



    public UserData(String srn, String fullname, String email, String phone, String passedyear, String password, String message) {
        this.srn = srn;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.passedyear = passedyear;
        this.password = password;
        this.school = school;
        this.image = image;
        this.message = message;
    }


    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassedyear() {
        return passedyear;
    }

    public void setPassedyear(String passedyear) {
        this.passedyear = passedyear;
    }

    public String password() {
        return password;
    }

    public void password(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
