package me.suryaakasam.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;

    @Column(name = "movie_name", length = 50, nullable = false, unique = true)
    private String movieName;

    @Column(name = "movie_desc", length = 200, nullable = false)
    private String movieDesc;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(columnDefinition = "int check (duration > 60)", nullable = false)
    private int duration;

    @Column(name = "cover_photo_url", length = 500, nullable = false)
    private String coverPhotoURL;

    @Column(name = "trailer_url", length = 500, nullable = false)
    private String trailerURL;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
    private Status status;

    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_theatre",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "theatre_id") }
    )
    private Set<Theatre> theatres;
    */

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private Set<MovieTheatre> associatedTheatres;

    public Movie() {}

    public Movie(String movieName, String movieDesc, LocalDate releaseDate, int duration,
                 String coverPhotoURL, String trailerURL, Status status) {
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
        this.status = status;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<MovieTheatre> getAssociatedTheatres() {
        return associatedTheatres;
    }

    public void setAssociatedTheatres(Set<MovieTheatre> associatedTheatres) {
        this.associatedTheatres = associatedTheatres;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movie.class.getSimpleName() + "[", "]")
                .add("movieId=" + movieId)
                .add("movieName='" + movieName + "'")
                .add("movieDesc='" + movieDesc + "'")
                .add("releaseDate=" + releaseDate)
                .add("duration=" + duration)
                .add("coverPhotoURL='" + coverPhotoURL + "'")
                .add("trailerURL='" + trailerURL + "'")
                .add("status='" + status.getStatusName() + "'")
                .toString();
    }
}
