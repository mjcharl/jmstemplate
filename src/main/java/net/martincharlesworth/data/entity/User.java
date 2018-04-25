package net.martincharlesworth.data.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({ @NamedQuery(name = "userByUsername", query = "select u from User u where username = :username"),
        @NamedQuery(name = "userWithOrders", query = "select u from User u join fetch u.orders where u.id = :id"),
        @NamedQuery(name = "userWithOrdersAndItems", query = "select u from User u join fetch u.orders o join fetch o.items where u.id = :id") })
@Entity
@Table(name = "Users")
@SequenceGenerator(name = "UsersSeq", sequenceName = "users_seq", allocationSize = 5)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsersSeq")
    private Integer id;

    private String forename;

    private String surname;

    String username;

    Boolean enabled;

    @Version
    private Long version;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<Order>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
