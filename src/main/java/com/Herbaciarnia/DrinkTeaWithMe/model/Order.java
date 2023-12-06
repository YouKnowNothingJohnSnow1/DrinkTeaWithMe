package com.Herbaciarnia.DrinkTeaWithMe.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "local_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(String firstName, String lastName, String city, String street, String zipCode) {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", localDate=" + localDate +
                ", user{" + "userId=" + user.getUserId() +
                ", firstName='" + user.getFirstName() + '\'' +
                ", lastName='" + user.getLastName() + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(localDate, order.localDate) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, localDate, user);
    }
}
