package com.example.shoppinglist.model;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean bought = false;

    // Constructors, getters, setters
    public Item() {}
    public Item(String name) { this.name = name; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isBought() { return bought; }
    public void setBought(boolean bought) { this.bought = bought; }
}
