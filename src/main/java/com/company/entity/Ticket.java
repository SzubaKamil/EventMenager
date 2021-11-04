package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private Status status;

    @Column (name = "price")
    private int price;

    @Column (name = "link")
    private String link;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public enum Status {
        onSale ( "W trakcie"),
        close ("Zakończona"),
        notStart ("Nie rozpoczęta"),
        canceled ("Odwołana");

        private final String name;

        Status(String name) {
            this.name = name;
        }

        public boolean equals (String name){
            return name.equals(this.name);
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
