package com.demo.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminUserDetailResponse {

    private long userId;
    private String companyName;
    private String district;
    private int numberOfAds;

}

