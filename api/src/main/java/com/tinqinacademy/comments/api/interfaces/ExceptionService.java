package com.tinqinacademy.comments.api.interfaces;

import com.tinqinacademy.comments.api.models.ErrorWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ExceptionService {
    ErrorWrapper handleException(MethodArgumentNotValidException ex);
}
