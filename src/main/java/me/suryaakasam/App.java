package me.suryaakasam;

import me.suryaakasam.dao.*;
import me.suryaakasam.entities.*;
import me.suryaakasam.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private UserTypeDAO userTypeDAO;

    @Autowired
    private LanguageDAO languageDAO;

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private TheatreDAO theatreDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private MovieTheatreDAO movieTheatreDAO;

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private OrderRepository orderDAO;

    @Autowired
    private MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        /* initMySQLDB(); */
        /* queryMySQLDB(); */

        /* initMongoDB(); */
        /* queryMongoDB(); */

        /* testMovieService(); */
    }

    private void initMySQLDB() {
        // Init Language
        Language enLanguage = languageDAO.save(new Language("English"));

        // Init UserType
        UserType customerType = userTypeDAO.save(new UserType("Customer"));

        // Init Customer
        Customer john = new Customer(
                "John",
                "Doe",
                "john_doe",
                "********",
                LocalDate.of(1987, 1, 1),
                new HashSet<>(Arrays.asList("1234567890", "1234567899")),
                customerType,
                enLanguage
        );
        Customer jane = new Customer(
                "Jane",
                "Doe",
                "jane_doe",
                "********",
                LocalDate.of(1992, 2, 2),
                new HashSet<>(List.of("9876543210")),
                customerType,
                enLanguage
        );
        customerDAO.save(john);
        customerDAO.save(jane);

        // Init City
        City bangalore = cityDAO.save(new City("Bangalore"));

        // Init Theatre
        Theatre inoxTheatre = new Theatre("INOX Leisure", bangalore, 300.00f);
        Theatre everestTheatre = new Theatre("Everest Cinema", bangalore, 225.00f);
        Theatre urvashiTheatre = new Theatre("Urvashi Cinema", bangalore, 200.00f);
        Theatre rocklineTheatre = new Theatre("Rockline Cinema", bangalore, 275.00f);
        Theatre modern7DTheatre = new Theatre("Modern Mastii 7D", bangalore, 500.00f);
        Theatre cinepolisTheatre = new Theatre("Cinepolis Multiplex", bangalore, 250.00f);
        theatreDAO.saveAll(Arrays.asList(inoxTheatre, everestTheatre, urvashiTheatre, rocklineTheatre,
                modern7DTheatre, cinepolisTheatre));

        // Init Status
        Status nowShowing = new Status("Now Showing");
        Status comingSoon = new Status("Coming Soon");
        statusDAO.saveAll(Arrays.asList(nowShowing, comingSoon));

        // Init Movie
        Movie avatarMovie = new Movie(
                "Avatar",
                "Description",
                LocalDate.of(2023, 1, 1),
                160,
                "Cover Photo URL",
                "Trailer URL",
                nowShowing
        );
        Movie frozenMovie = new Movie(
                "Frozen",
                "Description",
                LocalDate.of(2023, 2, 1),
                90,
                "Cover Photo URL",
                "Trailer URL",
                nowShowing
        );
        Movie tangledMovie = new Movie(
                "Tangled",
                "Description",
                LocalDate.of(2023, 3, 1),
                100,
                "Cover Photo URL",
                "Trailer URL",
                comingSoon
        );
        movieDAO.saveAll(Arrays.asList(avatarMovie, frozenMovie, tangledMovie));

        //Init Movie-Theatre Associations
        MovieTheatre avatarAtInox = new MovieTheatre(avatarMovie, inoxTheatre);
        MovieTheatre avatarAtUrvashi = new MovieTheatre(avatarMovie, urvashiTheatre);
        MovieTheatre avatarAtModern = new MovieTheatre(avatarMovie, modern7DTheatre);
        MovieTheatre frozenAtEverest = new MovieTheatre(frozenMovie, everestTheatre);
        MovieTheatre frozenAtRockline = new MovieTheatre(frozenMovie, rocklineTheatre);
        MovieTheatre frozenAtCinepolis = new MovieTheatre(frozenMovie, cinepolisTheatre);
        MovieTheatre tangledAtInox = new MovieTheatre(tangledMovie, inoxTheatre);
        MovieTheatre tangledAtCinepolis = new MovieTheatre(tangledMovie, cinepolisTheatre);
        movieTheatreDAO.saveAll(Arrays.asList(avatarAtInox, avatarAtUrvashi, avatarAtModern,
                frozenAtEverest, frozenAtRockline, frozenAtCinepolis,
                tangledAtInox, tangledAtCinepolis));

        //Init Booking
        bookingDAO.saveAll(Arrays.asList(
                new Booking(jane, avatarAtInox, LocalDate.now(), 3),
                new Booking(john, frozenAtRockline, LocalDate.now(), 2),
                new Booking(jane, avatarAtInox, LocalDate.of(2023, 3, 1), 5)
        ));
    }

    private void queryMySQLDB() {
        System.out.println("\n\n==================== Get Customers  ====================");
        customerDAO.findAll().forEach(e -> System.out.println("<<RESULT>>    " + e));

        System.out.println("\n\n===================== Get Theatres  ====================");
        theatreDAO.findAll().forEach(e -> System.out.println("<<RESULT>>    " + e));

        System.out.println("\n\n==================== Use Pagination ====================");
        //Page Count Starts At 0
        int page = 0, size = 4;
        System.out.println("Page 1 / Size 4: ");
        theatreDAO.findAll(PageRequest.of(page++, size)).forEach(e -> System.out.println("<<PAGE-1>>    " + e));
        System.out.println("Page 2 / Size 4: ");
        theatreDAO.findAll(PageRequest.of(page, size)).forEach(e -> System.out.println("<<PAGE-2>>    " + e));

        System.out.println("\n\n===================== Find Theatre =====================");
        System.out.println("<<RESULT>>    " + theatreDAO.findByTheatreName("Rockline Cinema"));

        System.out.println("\n\n============ Theatres with ticket <275.00/- ============");
        theatreDAO.findByTicketPriceLessThan(275.00f).forEach(e -> System.out.println("<<RESULT>>    " + e));

        System.out.println("\n\n====== Theatres containing 'Cine' in their names =======");
        theatreDAO.findByTheatreNameContaining("Cine").forEach(e -> System.out.println("<<RESULT>>    " + e));
    }

    private void initMongoDB() {
        // Init Orders
        Order order1 = new Order("IN-2014-76016", "Corporate", "FUR-BO-10004852", 5667.87f, 2097.03f);
        Order order2 = new Order("IN-2014-32084", "Consumer", "TEC-PH-10001457", 2550.00f, 280.44f);
        Order order3 = new Order("IN-2013-34968", "Home Office", "FUR-BO-10004679", 1564.56f, 172.08f);
        Order order4 = new Order("IN-2012-48240", "Corporate", "FUR-TA-10000226", 1745.34f, 226.86f);
        Order order5 = new Order("IN-2014-25847", "Consumer", "OFF-AP-10003275", 2488.56f, 348.24f);
        orderDAO.save(order1);
        orderDAO.save(order2);
        orderDAO.save(order3);
        orderDAO.save(order4);
        orderDAO.save(order5);
    }

    private void queryMongoDB() {
        System.out.println("\n\n==================== Print All Docs ====================");
        orderDAO.findAll().forEach(System.out::println);
    }

    private void testMovieService() {
        System.out.println("\n\n==================== Get All Movies ====================");
        movieService.getAllMovies().forEach(movie -> System.out.println("--> " + movie));

        /*
        System.out.println("\n\n==================== Add New Movie  ====================");
        Movie avengersMovie = movieService.addMovie(new Movie(
                "Avengers",
                "Description",
                LocalDate.of(2022, 12, 1),
                100,
                "Avengers Cover Photo",
                "Avengers Trailer URL",
                statusDAO.findByStatusName("Now Showing")
        ));
        System.out.println("--> " + avengersMovie);
        */

        System.out.println("\n\n==================== Get Movie Info ====================");
        System.out.println("--> " + movieService.getMovie(14));
    }
}
