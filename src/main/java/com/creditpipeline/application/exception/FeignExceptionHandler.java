package com.creditpipeline.application.exception;

import com.creditpipeline.application.dto.LoanOfferDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(value = OffersException.class)
    public ResponseEntity<Object> handleOffersException(OffersException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<LoanOfferDTO> offers = new ArrayList<>();
        return new ResponseEntity<>(offers, badRequest);
    }

    @ExceptionHandler(value = ChoiceOfferException.class)
    public ResponseEntity<Object> handleChoiceOfferException(ChoiceOfferException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        return new ResponseEntity<>(loanOfferDTO, badRequest);
    }

}
