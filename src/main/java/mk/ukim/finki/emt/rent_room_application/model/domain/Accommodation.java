package mk.ukim.finki.emt.rent_room_application.model.domain;


import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.rent_room_application.model.enumerations.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private Integer numRooms;
    boolean isRented = false;

    @OneToMany(cascade = CascadeType.ALL)
    List<Review> reviewList;


    public Accommodation(String name, Category category, Host host, Integer numRooms, Review reviewList) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.reviewList = new ArrayList<>();
        this.reviewList.add(reviewList);
    }

    public Accommodation(){

    }

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = isRented;
        this.reviewList=new ArrayList<>();
    }




    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }


}
