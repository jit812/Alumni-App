package com.example.alumni;

public class Suppots {

  private String srn,transactionId,yearofgraduation,branch,document,checked;

   public Suppots(){

   }

    public Suppots(String srn, String transactionId, String yearofgraduation, String branch, String document,String checked) {
        this.srn = srn;
        this.transactionId = transactionId;
        this.yearofgraduation = yearofgraduation;
        this.branch = branch;
        this.document = document;
        this.checked=checked;
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getYearofgraduation() {
        return yearofgraduation;
    }

    public void setYearofgraduation(String yearofgraduation) {
        this.yearofgraduation = yearofgraduation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
