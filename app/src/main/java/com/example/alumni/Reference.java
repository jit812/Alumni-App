package com.example.alumni;

public class Reference {

    private String Fullname,Email,phonenumber,dob,address,state,country,pincode,gender,course,srn,parent,date;

    public Reference()
    {

    }

    public Reference(String Fullname,String course, String Email, String phonenumber, String dob, String address, String state, String country, String pincode, String gender,String parent,String srn,String date) {
        this.Fullname = Fullname;
        this.course=course;
        this.gender = gender;
        this.Email = Email;
        this.phonenumber = phonenumber;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.parent =parent;
        this.srn = srn;
        this.date=date;

    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
