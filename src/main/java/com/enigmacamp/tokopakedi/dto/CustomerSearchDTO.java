package com.enigmacamp.tokopakedi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchDTO {
    private String customerFullName;
    private String customerAddress;
    private Date customerBirthDate;
}
