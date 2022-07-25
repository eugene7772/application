package com.creditpipeline.application.controller;

import com.creditpipeline.application.dto.LoanApplicationRequestDTO;
import com.creditpipeline.application.dto.LoanOfferDTO;
import com.creditpipeline.application.feign.FeignServiceUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final static Logger logger = LogManager.getLogger(ApplicationController.class);

    @Autowired
    public ApplicationController(FeignServiceUtil feignServiceUtil) {
        this.feignServiceUtil = feignServiceUtil;
    }

    @PostMapping(value = "/application")
    @Operation(
            summary = "Прескоринг + запрос на расчёт возможных условий кредита")
    public List<LoanOfferDTO> PrescoringAndRequestForCalculation(@RequestBody @Validated ResponseEntity<LoanApplicationRequestDTO> loanApplicationRequestDTO) {

        logger.debug("Return offers");
        return feignServiceUtil.getOffers(loanApplicationRequestDTO);

    }

    @PutMapping(value = "/application/offer")
    @Operation(
            summary = " Выбор одного из предложений")
    public void choiceOffer(@RequestBody @Validated ResponseEntity<LoanOfferDTO> loanOfferDTO) {

        logger.debug("Post to MC http://localhost:8090/deal/offer");
        feignServiceUtil.choiceOffer(loanOfferDTO);

    }

}
