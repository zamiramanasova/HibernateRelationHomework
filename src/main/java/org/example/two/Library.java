package org.example.two;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int openOfYear;
    private String nameOfCity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "library_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "library_id"))
    private List<Book> book;

    public Library() {
    }

    public Library(String name, int openOfYear, String nameOfCity) {
        this.name = name;
        this.openOfYear = openOfYear;
        this.nameOfCity = nameOfCity;
    }

    public Library(String name, int openOfYear, String nameOfCity, List<Book> book) {
        this.name = name;
        this.openOfYear = openOfYear;
        this.nameOfCity = nameOfCity;
        this.book = book;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpenOfYear() {
        return openOfYear;
    }

    public void setOpenOfYear(int openOfYear) {
        this.openOfYear = openOfYear;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openOfYear='" + openOfYear + '\'' +
                ", nameOfCity='" + nameOfCity + '\'' +
                '}';
    }
}
