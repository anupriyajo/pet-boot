package com.anu.pets;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

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

    public Pet(String name, int age, Status status, List<String> photoUrls) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.photoUrls = photoUrls;
    }

    public Pet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        final var pet = (Pet) o;
        return Objects.equals(this.id, pet.id) && Objects.equals(this.name, pet.name)
            && Objects.equals(this.status, pet.status) && Objects.equals(this.age, pet.age)
            && Objects.equals(this.photoUrls, pet.photoUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.age, this.status);
    }

    @Override
    public String toString() {
        return "Pet{" + "id=" + this.id + ", name='" + this.name + '\'' + ", age='" + this.age + '\'' + '}';
    }
}
