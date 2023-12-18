package com.springboot.validException.data.dto;

import lombok.*;


@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private String name;
    private int price;
    private int stock;


}
