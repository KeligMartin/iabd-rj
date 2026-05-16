package models;

public class Notification implements Source {

    private String body;
    private String name;

    public Notification(String body, String name) {
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
