package me.suryaakasam.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int statusId;

    @Column(name = "status_name", length = 20, unique = true, nullable = false)
    private String statusName;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private Set<Movie> movies;

    public Status() {}

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Status.class.getSimpleName() + "[", "]")
                .add("statusId=" + statusId)
                .add("statusName='" + statusName + "'")
                .toString();
    }
}
