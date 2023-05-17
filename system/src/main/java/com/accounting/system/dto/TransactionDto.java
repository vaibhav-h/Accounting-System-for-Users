package com.accounting.system.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {

    @NotNull
    private Long userId;
    @NotEmpty(message = "userName should not be null or empty")
    private String userName;
    @NotEmpty(message = "txnType should not be null or empty")
    private String txnType;
    @NotEmpty(message = "amount should not be null or empty")
    private String amount;
    @Valid
    private LocationDto location;
    @NotEmpty(message = "ip should not be null or empty")
    private String ip;
}
