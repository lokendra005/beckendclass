package org.buildproduct.build_api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException {
    private Long id;

    public ProductNotFoundException(Long id, String message) {
//        super(message);
//        this.id = id;
    }
}
