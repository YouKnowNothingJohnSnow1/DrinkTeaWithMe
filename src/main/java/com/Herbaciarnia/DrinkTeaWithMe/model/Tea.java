package com.Herbaciarnia.DrinkTeaWithMe.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "teas")
public class Tea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teaId;

    @Column(name = "tea_name")
    private String teaName;

    @Column(name = "tea_description")
    private String teaDescription;

    @Column(name = "price_of_selling")
    private BigDecimal priceOfSelling;

    @Column(name = "price_of_delivery")
    private BigDecimal priceOfDelivery;

    @Column(name = "available_quantity")
    private String availableQuantity;

    @Override
    public String toString() {
        return "Tea{" +
                "teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaDescription='" + teaDescription + '\'' +
                ", priceOfSelling=" + priceOfSelling +
                ", priceOfDelivery=" + priceOfDelivery +
                ", availableQuantity='" + availableQuantity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tea tea = (Tea) o;
        return Objects.equals(teaId, tea.teaId) && Objects.equals(teaName, tea.teaName) && Objects.equals(teaDescription, tea.teaDescription) && Objects.equals(priceOfSelling, tea.priceOfSelling) && Objects.equals(priceOfDelivery, tea.priceOfDelivery) && Objects.equals(availableQuantity, tea.availableQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teaId, teaName, teaDescription, priceOfSelling, priceOfDelivery, availableQuantity);
    }
}

