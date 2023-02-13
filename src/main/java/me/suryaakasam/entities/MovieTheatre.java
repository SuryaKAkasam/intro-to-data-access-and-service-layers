package me.suryaakasam.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "movie_theatre")
public class MovieTheatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_theatre_id", nullable = false)
    private int movieTheatreId;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", referencedColumnName = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "movieTheatre", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

    public MovieTheatre() {
    }

    public MovieTheatre(Movie movie, Theatre theatre) {
        this.movie = movie;
        this.theatre = theatre;
    }

    public int getMovieTheatreId() {
        return movieTheatreId;
    }

    public void setMovieTheatreId(int movieTheatreId) {
        this.movieTheatreId = movieTheatreId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MovieTheatre.class.getSimpleName() + "[", "]")
                .add("movieTheatreId=" + movieTheatreId)
                .add("movie=" + movie)
                .add("theatre=" + theatre)
                .toString();
    }
}
