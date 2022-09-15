package com.example.batteryprice.webclient;

import com.example.batteryprice.dto.KWPriceDto;
import com.example.batteryprice.service.BatteriesPriceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class WebClientTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Autowired
    BatteriesPriceService service;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = BatteriesPriceService.builder().webClient(webClient).build();
    }

    @AfterEach
    void teardown() throws Exception {
        autoCloseable.close();
    }


//    @Test
//    void getValueRestCallTest() {
//
//        KWPriceDto kwPriceDto = new KWPriceDto(200);
//
//        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);
//        WebClient.RequestBodyUriSpec requestBodyUriSpecMock = mock(WebClient.RequestBodyUriSpec.class);
//        WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
//
//        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
//        Mockito.when(requestHeadersUriSpecMock.uri("/priceKw")).thenReturn(requestHeadersSpecMock);
//        Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
//        Mockito.when(responseSpecMock.bodyToMono(KWPriceDto.class)).thenReturn(Mono.just(kwPriceDto));
//
//        KWPriceDto kwPriceDtoMono = service.getValueRestCall();
//
//        Assertions.assertEquals(kwPriceDto, kwPriceDtoMono);
//
//
//
//
//    }

}
