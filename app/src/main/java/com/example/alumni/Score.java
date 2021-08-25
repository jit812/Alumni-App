package com.example.alumni;

public class Score {
    private String srn,name,topic,
            about_session,about_content,about_useful,about_time,about_topic,sugg,school,event,date;

    public Score(){

    }

    public Score( String srn,  String name,  String topic, String about_session, String about_content, String about_useful, String about_time, String about_topic, String sugg, String school, String event,String date){
        this.srn = srn;
        this.name = name;
        this.topic = topic;
        this.about_session = about_session;
        this.about_content = about_content;
        // this.about_info = about_info;
        this.about_time = about_time;
        this.about_topic= about_topic;
        this.about_useful = about_useful;
        this.event = event;
        this.sugg = sugg;
        this.school=school;
        this.date=date;
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAbout_session() {
        return about_session;
    }

    public void setAbout_session(String about_session) {
        this.about_session = about_session;
    }

    public String getAbout_content() {
        return about_content;
    }

    public void setAbout_content(String about_content) {
        this.about_content = about_content;
    }

    public String getAbout_useful() {
        return about_useful;
    }

    public void setAbout_useful(String about_useful) {
        this.about_useful = about_useful;
    }

    public String getAbout_time() {
        return about_time;
    }

    public void setAbout_time(String about_time) {
        this.about_time = about_time;
    }

    public String getAbout_topic() {
        return about_topic;
    }

    public void setAbout_topic(String about_topic) {
        this.about_topic = about_topic;
    }

    public String getSugg() {
        return sugg;
    }

    public void setSugg(String sugg) {
        this.sugg = sugg;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}