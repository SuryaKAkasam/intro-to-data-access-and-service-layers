package me.suryaakasam.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cityId;

    @Column(name = "city_name", length = 20, nullable = false, unique = true)
    private String cityName;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Theatre> theatres;

    public City() {}

    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Set<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(Set<Theatre> theatres) {
        this.theatres = theatres;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", City.class.getSimpleName() + "[", "]")
                .add("cityId=" + cityId)
                .add("cityName='" + cityName + "'")
                .toString();
    }
}
