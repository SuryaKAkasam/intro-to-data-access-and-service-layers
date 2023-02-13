package me.suryaakasam.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int languageId;

    @Column(name = "language_name", length = 20, unique = true, nullable = false)
    private String languageName;

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY)
    private Set<Customer> customers;

    public Language() {}

    public Language(String languageName) {
        this.languageName = languageName;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Language.class.getSimpleName() + "[", "]")
                .add("languageId=" + languageId)
                .add("languageName='" + languageName + "'")
                .toString();
    }
}
