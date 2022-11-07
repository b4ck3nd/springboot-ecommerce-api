package com.kodlamiyoruz.ecomm.exception;

import com.kodlamiyoruz.ecomm.model.Category;

public class CategoryException extends RuntimeException {
    public CategoryException() {
        super();
    }
    public CategoryException(String categoryName) {
        super("not found any category with name: " + categoryName);
    }
    public CategoryException(int id) {
        super("not found any category with this id: " + id);
    }
}
