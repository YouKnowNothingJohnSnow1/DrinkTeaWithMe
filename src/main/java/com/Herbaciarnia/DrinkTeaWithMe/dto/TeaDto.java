package com.Herbaciarnia.DrinkTeaWithMe.dto;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TeaDto {


    private String teaName;
    private String teaDescription;
    private BigDecimal priceOfSelling;
    private BigDecimal priceOfDelivery;
    private String availableQuantity;


}
