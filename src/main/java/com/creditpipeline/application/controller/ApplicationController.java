package com.creditpipeline.application.controller;

import com.creditpipeline.application.dto.LoanApplicationRequestDTO;
import com.creditpipeline.application.dto.LoanOfferDTO;
import com.creditpipeline.application.exception.ChoiceOfferException;
import com.creditpipeline.application.exception.OffersException;
import com.creditpipeline.application.feign.FeignServiceUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Приложение")
public class ApplicationController {

    private final FeignServiceUtil feignServiceUtil;
    private final static Logger logger = LogManager.getLogger(ApplicationController.class);

    @Autowired
    public ApplicationController(FeignServiceUtil feignServiceUtil) {
        this.feignServiceUtil = feignServiceUtil;
    }

    @PostMapping(value = "/application")
    @Operation(
            summary = "Прескоринг + запрос на расчёт возможных условий кредита")
    public List<LoanOfferDTO> PrescoringAndRequestForCalculation(@RequestBody @Validated Optional<LoanApplicationRequestDTO> loanApplicationRequestDTO) {

        logger.debug("Return offers");
        return feignServiceUtil.getOffers(loanApplicationRequestDTO.orElseThrow(() -> new OffersException("No offers")));

    }

    @PutMapping(value = "/application/offer")
    @Operation(
            summary = " Выбор одного из предложений")
    public void choiceOffer(@RequestBody @Validated Optional<LoanOfferDTO> loanOfferDTO) {

        logger.debug("Post to MC http://localhost:8090/deal/offer");
        feignServiceUtil.choiceOffer(loanOfferDTO.orElseThrow(() -> new ChoiceOfferException("Cant choice")));

    }

}
