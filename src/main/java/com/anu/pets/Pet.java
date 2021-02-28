package com.anu.pets;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Pet {
    private @Id
    @GeneratedValue
    Long id;
    private int age;
    private String name;
    private Status status;
    @ElementCollection
    private List<String> photoUrls;
}
