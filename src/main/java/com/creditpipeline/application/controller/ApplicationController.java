package com.creditpipeline.application.controller;

import com.creditpipeline.application.dto.LoanApplicationRequestDTO;
import com.creditpipeline.application.dto.LoanOfferDTO;
import com.creditpipeline.application.feign.FeignServiceUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Tag(name = "Приложение")
public class ApplicationController {

    private final FeignServiceUtil feignServiceUtil;

    @Autowired
    public ApplicationController(FeignServiceUtil feignServiceUtil) {
        this.feignServiceUtil = feignServiceUtil;
    }

    @PostMapping(value = "/application")
    @Operation(
            summary = "Прескоринг + запрос на расчёт возможных условий кредита")
    public List<LoanOfferDTO> PrescoringAndRequestForCalculation(@RequestBody @Validated LoanApplicationRequestDTO loanApplicationRequestDTO) {

        return feignServiceUtil.getOffers(loanApplicationRequestDTO);

    }

    @PutMapping(value = "/application/offer")
    @Operation(
            summary = " Выбор одного из предложений")
    public void choiceOffer(@RequestBody @Validated LoanOfferDTO loanOfferDTO) {

        feignServiceUtil.choiceOffer(loanOfferDTO);

    }

}
