package com.example.alumni;

public class News {
    private String news,imageUrl,randstring;


    public News()
    {

    }

    public News(String news,String imageUrl,String randstring)
    {
        this.news = news;
        this.imageUrl=imageUrl;
        this.randstring=randstring;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRandstring() {
        return randstring;
    }

    public void setRandstring(String randstring) {
        this.randstring = randstring;
    }
}
