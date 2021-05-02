package app.datamodel.exam;

import java.util.List;

public class Part<T> {
    private String name;
    private Integer type;
    private Integer score;
    private List<T> content;

    public Part(String name, Integer type, Integer score, List<T> content) {
        this.name = name;
        this.type = type;
        this.score = score;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
