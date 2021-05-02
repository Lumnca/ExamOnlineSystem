package app.datamodel.common;

import java.util.Date;

public class Message {
    private String name;
    private String content;
    private String iden;
    private Date date;
    public Message(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public String getIden() {
        return iden;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }
}
