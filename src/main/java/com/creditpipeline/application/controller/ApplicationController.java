package com.creditpipeline.application.controller;

import com.creditpipeline.application.dto.LoanApplicationRequestDTO;
import com.creditpipeline.application.dto.LoanOfferDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Tag(name = "Приложение")
public class ApplicationController {

    @PostMapping(value = "/application")
    @Operation(
            summary = "Прескоринг + запрос на расчёт возможных условий кредита")
    public List<LoanOfferDTO> PrescoringAndRequestForCalculation(LoanApplicationRequestDTO loanApplicationRequestDTO) {

        List<LoanOfferDTO> offers = new ArrayList<>();

        return offers;

    }

    @PutMapping(value = "/application/offer")
    @Operation(
            summary = " Выбор одного из предложений")
    public void choiceOffer(LoanOfferDTO loanOfferDTO) {



    }

}
