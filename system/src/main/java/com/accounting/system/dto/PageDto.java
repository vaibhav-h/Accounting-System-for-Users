package com.accounting.system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    private int page;
    private int per_page;
    private Long total;
    private int total_pages;
    private List<TransactionDto> data;
}
