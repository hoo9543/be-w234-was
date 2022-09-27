package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Board {

    @Id
    @GeneratedValue
    private int id=0;
    private String writer;

    private String title;
    private String contents;


    public Board(String title, String writer, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle(){
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Board [writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
    }

}
