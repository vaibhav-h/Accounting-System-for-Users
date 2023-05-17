package com.accounting.system.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {

    @NotNull
    private Long locationId;
    @NotNull
    private Long zipCode;
    @NotEmpty(message = "address should not be null or empty")
    private String address;
    @NotEmpty(message = "city should not be null or empty")
    private String city;
}
