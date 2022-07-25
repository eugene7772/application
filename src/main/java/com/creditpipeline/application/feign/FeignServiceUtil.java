package com.creditpipeline.application.feign;

import com.creditpipeline.application.dto.LoanApplicationRequestDTO;
import com.creditpipeline.application.dto.LoanOfferDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8090/deal")
public interface FeignServiceUtil {

    @PostMapping("/application")
    List<LoanOfferDTO> getOffers(ResponseEntity<LoanApplicationRequestDTO> loanApplicationRequestDTO);

    @PostMapping("/offer")
    void choiceOffer(ResponseEntity<LoanOfferDTO> loanOfferDTO);
}
