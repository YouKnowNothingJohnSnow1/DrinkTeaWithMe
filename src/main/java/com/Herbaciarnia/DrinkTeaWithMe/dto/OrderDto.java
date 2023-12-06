package com.Herbaciarnia.DrinkTeaWithMe.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDto {

    private Long orderId;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;
}
