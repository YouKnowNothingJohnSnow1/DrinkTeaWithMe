package com.Herbaciarnia.DrinkTeaWithMe.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressDto {

    private String city;
    private String street;
    private String zipCode;
}
