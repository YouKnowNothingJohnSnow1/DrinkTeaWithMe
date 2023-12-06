package com.Herbaciarnia.DrinkTeaWithMe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(min=1, max=30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min=1, max=50)
    @Column(name = "last_name")
    private String lastName;


    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + (address != null ?
                "Address{" +
                        "addressId=" + address.getAddressId() +
                        ", street='" + address.getStreet() + '\'' +
                        ", city='" + address.getCity() + '\'' +
                        ", zipCode='" + address.getZipCode() + '\'' +
                        '}' :
                "null") +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, address);
    }
}

