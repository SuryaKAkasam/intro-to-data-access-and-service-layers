package me.suryaakasam.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String userName;

    @Column(columnDefinition = "varchar(20) check (length(password) > 5)", nullable = false)
    private String password;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dob;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "phone_number", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "phone_number", length = 20)
    private Set<String> phoneNumbers;

    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id", nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
    private Language language;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Booking> bookings;

    public Customer() {}

    public Customer(String firstName, String lastName, String userName, String password, LocalDate dob,
                    Set<String> phoneNumbers, UserType userType, Language language) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dob = dob;
        this.phoneNumbers = phoneNumbers;
        this.userType = userType;
        this.language = language;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("customerId=" + customerId)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .add("dob=" + dob)
                .add("phoneNumber=" + phoneNumbers)
                .add("userType='" + userType.getUserTypeName() + "'")
                .add("language='" + language.getLanguageName() + "'")
                .toString();
    }
}
