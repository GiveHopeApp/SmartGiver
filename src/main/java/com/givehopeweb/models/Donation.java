package com.givehopeweb.models;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>The <code>Donation</code> class stores information about each charity. Spring
 * annotations are used to designate the <code>Donation</code> class as a table and each of its
 * properties as a column. Foreign keys are used to link donations to users if they are
 * logged in when donating and link donations to the charity by ID. Getters and setters for each of
 * the properties are present.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Entity
@Table (name = "donations")
public class Donation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, precision = 4)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn (name = "charity_id")
    private Charity charity;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }
}
