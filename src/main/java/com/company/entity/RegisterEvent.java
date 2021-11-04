package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name ="register_event")
public class RegisterEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    public RegisterEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event book) {
        this.event = book;
    }
}
