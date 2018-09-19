package com.vladislavgusev.bookphone.data;

public class Record {
    private int id;
    private User user;

    public Record(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
