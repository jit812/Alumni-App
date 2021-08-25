package com.example.alumni;

public class Jobs {
    private String Companyname,Designation,jobdes,qualification,empType,exp,date;

    public Jobs(String companyname, String designation, String jobdes, String qualification, String empType, String exp,String date) {
        Companyname = companyname;
        Designation = designation;
        this.jobdes = jobdes;
        this.qualification = qualification;
        this.empType = empType;
        this.exp = exp;
        this.date=date;
    }
    public Jobs(){

    }

    public String getCompanyname() {
        return Companyname;
    }

    public void setCompanyname(String companyname) {
        Companyname = companyname;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getJobdes() {
        return jobdes;
    }

    public void setJobdes(String jobdes) {
        this.jobdes = jobdes;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
