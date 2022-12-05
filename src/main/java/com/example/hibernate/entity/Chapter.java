package com.example.hibernate.entity;

public class Chapter {

    private Integer chapterNumber;
    private String title;

    public Chapter() {
    }
    public Chapter(Integer chapterNumber, String title) {
        this.chapterNumber = chapterNumber;
        this.title = title;
    }
    public Integer getChapterNumber() {
        return chapterNumber;
    }
    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Chapter [chapterNumber=" + chapterNumber + ", title=" + title + "]";
    }
    
    
    
    
}
