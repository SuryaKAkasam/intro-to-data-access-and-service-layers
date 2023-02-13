package me.suryaakasam.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "movie_theatre_id", referencedColumnName = "movie_theatre_id")
    private MovieTheatre movieTheatre;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "no_of_seats", nullable = false)
    private int noOfSeats;

    public Booking() {
    }

    public Booking(Customer customer, MovieTheatre movieTheatre, LocalDate bookingDate, int noOfSeats) {
        this.customer = customer;
        this.movieTheatre = movieTheatre;
        this.bookingDate = bookingDate;
        this.noOfSeats = noOfSeats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MovieTheatre getMovieTheatre() {
        return movieTheatre;
    }

    public void setMovieTheatre(MovieTheatre movieTheatre) {
        this.movieTheatre = movieTheatre;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Booking.class.getSimpleName() + "[", "]")
                .add("bookingId=" + bookingId)
                .add("customerId=" + customer.getCustomerId())
                .add("movieName='" + movieTheatre.getMovie().getMovieName() + "'")
                .add("theatreName='" + movieTheatre.getTheatre().getTheatreName() + "'")
                .add("bookingDate=" + bookingDate)
                .add("noOfSeats=" + noOfSeats)
                .toString();
    }
}
