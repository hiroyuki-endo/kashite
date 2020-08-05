package com.example.kashite.adapter.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class GoogleSaleInfo {
    private String country;
    private String saleability;
    private GooglePrice listPrice;
    private GooglePrice retailPrice;
}
