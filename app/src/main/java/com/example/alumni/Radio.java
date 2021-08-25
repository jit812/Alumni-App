package com.example.alumni;

public class Radio {
    private String srn,branch,year_of_completion,designation,
            about_reva,about_app,curriculum,reva_website,lab_infra,faculty,lib,ofc_staff,hostel_facilities,sugg,date;

    public Radio()
    {

    }

    public Radio( String srn,String branch,String year_of_completion, String designation, String about_reva, String about_app, String curriculum, String reva_website, String lab_infra, String faculty, String lib, String ofc_staff, String hostel_facilities, String sugg,String date) {
        this.srn = srn;
        this.branch=branch;
        this.year_of_completion = year_of_completion;
        this.designation = designation;
        this.about_reva = about_reva;
        this.about_app = about_app;
        this.curriculum = curriculum;
        this.reva_website = reva_website;
        this.lab_infra = lab_infra;
        this.faculty = faculty;
        this.lib = lib;
        this.ofc_staff = ofc_staff;
        this.hostel_facilities = hostel_facilities;
        this.sugg = sugg;
        this.date=date;

    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAbout_reva() {
        return about_reva;
    }

    public void setAbout_reva(String about_reva) {
        this.about_reva = about_reva;
    }

    public String getAbout_app() {
        return about_app;
    }

    public void setAbout_app(String about_app) {
        this.about_app = about_app;
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getYear_of_completion() {
        return year_of_completion;
    }

    public void setYear_of_completion(String year_of_completion) {
        this.year_of_completion = year_of_completion;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getReva_website() {
        return reva_website;
    }

    public void setReva_website(String reva_website) {
        this.reva_website = reva_website;
    }

    public String getLab_infra() {
        return lab_infra;
    }

    public void setLab_infra(String lab_infra) {
        this.lab_infra = lab_infra;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getOfc_staff() {
        return ofc_staff;
    }

    public void setOfc_staff(String ofc_staff) {
        this.ofc_staff = ofc_staff;
    }

    public String getHostel_facilities() {
        return hostel_facilities;
    }

    public void setHostel_facilities(String hostel_facilities) {
        this.hostel_facilities = hostel_facilities;
    }

    public String getSugg() {
        return sugg;
    }

    public void setSugg(String sugg) {
        this.sugg = sugg;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
