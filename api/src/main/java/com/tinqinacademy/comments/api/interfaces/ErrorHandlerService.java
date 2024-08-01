package com.tinqinacademy.comments.api.interfaces;

import com.tinqinacademy.comments.api.models.ErrorWrapper;

public interface ErrorHandlerService {
    ErrorWrapper handle(Throwable throwable);
}
