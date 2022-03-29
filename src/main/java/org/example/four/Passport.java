package org.example.four;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long number;

    @OneToMany(mappedBy = "passport", cascade = CascadeType.ALL)
    private List<User> user;

    public Passport() {
    }

    public Passport(long number) {
        this.number = number;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
