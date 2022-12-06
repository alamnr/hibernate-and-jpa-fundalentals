package com.example.hibernate.domain;

public class MessageJdbc {
    private long id;
    private String text;

    public MessageJdbc(){ }
    public MessageJdbc(String text){
        setText(text);
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "Message [id=" + id + ", text=" + text + "]";
    }
    
}
