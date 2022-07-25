package controller;

import com.creditpipeline.application.controller.ApplicationController;
import com.creditpipeline.application.dto.LoanApplicationRequestDTO;
import com.creditpipeline.application.dto.LoanOfferDTO;
import com.creditpipeline.application.feign.FeignServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ApplicationControllerTest {

    @Mock
    private FeignServiceUtil feignServiceUtil;

    @InjectMocks
    private ApplicationController applicationController;

    @Test
    public void PrescoringAndRequestForCalculationTest() {

        List<LoanOfferDTO> offerDTOS = new ArrayList<>();

        LoanOfferDTO offer1 = new LoanOfferDTO();
        offerDTOS.add(offer1);

        LoanOfferDTO offer3 = new LoanOfferDTO();
        offerDTOS.add(offer3);

        LoanOfferDTO offer4 = new LoanOfferDTO();
        offerDTOS.add(offer4);

        LoanOfferDTO offer5 = new LoanOfferDTO();
        offerDTOS.add(offer5);

        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(500000));
        loanApplicationRequestDTO.setTerm(18);
        loanApplicationRequestDTO.setFirstName("Ivan");
        loanApplicationRequestDTO.setLastName("Ivanov");
        loanApplicationRequestDTO.setMiddleName("Ivanovich");
        loanApplicationRequestDTO.setEmail("ivanov@mail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.of(2000, 01, 01));
        loanApplicationRequestDTO.setPassportSeries("2014");
        loanApplicationRequestDTO.setPassportNumber("289543");

        Mockito.when(feignServiceUtil.getOffers(loanApplicationRequestDTO)).thenReturn(offerDTOS);
        List<LoanOfferDTO> offers = applicationController.PrescoringAndRequestForCalculation(Optional.of(loanApplicationRequestDTO));

        Assertions.assertEquals(ResponseEntity.ok(offerDTOS).getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(ResponseEntity.ok(offers).getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void choiceOfferTest() {

        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setTerm(18);
        loanOfferDTO.setApplicationId(0l);
        loanOfferDTO.setRequestedAmount(BigDecimal.valueOf(500000));
        loanOfferDTO.setTotalAmount(BigDecimal.valueOf(500000));

        applicationController.choiceOffer(Optional.of(loanOfferDTO));

    }

}
