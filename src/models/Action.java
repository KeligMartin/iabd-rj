package models;

public class Action implements Source {

    private String body;

    private String name;

    public Action(String body, String name) {
        this.body = body;
        this.name = name;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getName() {
        return name;
    }
}
