package me.suryaakasam.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @Column(name = "user_type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userTypeId;

    @Column(
            name = "user_type_name", nullable = false,
            columnDefinition = "varchar(20) check (user_type_name in ('Customer', 'Administrator')) default 'Customer'"
    )
    private String userTypeName;

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY)
    private Set<Customer> customers;

    public UserType() {}

    public UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserType.class.getSimpleName() + "[", "]")
                .add("userTypeId=" + userTypeId)
                .add("userTypeName='" + userTypeName + "'")
                .toString();
    }
}
