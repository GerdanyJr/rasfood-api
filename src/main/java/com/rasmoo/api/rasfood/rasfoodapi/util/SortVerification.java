package com.rasmoo.api.rasfood.rasfoodapi.util;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortVerification {
    
    public static Pageable verifySort(Integer actualPage,Sort.Direction sort,String property){
        Pageable pageable = Objects.nonNull(sort)?
        PageRequest.of(actualPage, 5,Sort.by(sort,property)):
        PageRequest.of(actualPage, 5);
        return pageable;
    }
}
