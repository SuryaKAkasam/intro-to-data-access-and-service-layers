package me.suryaakasam.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @Column(name = "theatre_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int theatreId;

    @Column(name = "theatre_name", length = 20, nullable = false, unique = true)
    private String theatreName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "ticket_price", columnDefinition = "decimal(5,2) default 150.00", nullable = false)
    private float ticketPrice = 150.00f;

    /*
    @ManyToMany(mappedBy = "theatres", fetch = FetchType.LAZY)
    private Set<Movie> movies;
    */

    @OneToMany(mappedBy = "theatre", fetch = FetchType.EAGER)
    private Set<MovieTheatre> associatedMovies;

    public Theatre() {}

    public Theatre(String theatreName, City city, float ticketPrice) {
        this.theatreName = theatreName;
        this.city = city;
        this.ticketPrice = ticketPrice;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Set<MovieTheatre> getAssociatedMovies() {
        return associatedMovies;
    }

    public void setAssociatedMovies(Set<MovieTheatre> associatedMovies) {
        this.associatedMovies = associatedMovies;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Theatre.class.getSimpleName() + "[", "]")
                .add("theatreId=" + theatreId)
                .add("theatreName='" + theatreName + "'")
                .add("city=" + city)
                .add("ticketPrice=" + ticketPrice)
                .toString();
    }
}
