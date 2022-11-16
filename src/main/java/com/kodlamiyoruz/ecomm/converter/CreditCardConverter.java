package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.user.CreditCardCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.user.CreditCardResponseDto;
import com.kodlamiyoruz.ecomm.model.CreditCard;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditCardConverter {
    public CreditCard creditCardCreateRequestDtoDoCreditCard(CreditCardCreateRequestDto dto) {
        CreditCard card=new CreditCard();
        card.setCardNumber(dto.getCardNumber());
        card.setCcv(dto.getCcv());
        card.setExpirationYear(dto.getExpirationYear());
        card.setExpirationMonth(dto.getExpirationMonth());
        card.setNameAndSurname(dto.getNameAndSurname());
        return card;
    }


    public List<CreditCardResponseDto> creditCartListToCreditCardDtoList(List<CreditCard> cards) {
        List<CreditCardResponseDto> dtos=new ArrayList<>();
        cards.forEach(value-> {
            CreditCardResponseDto dto=new CreditCardResponseDto();
            dto.setCreditCardId(value.getId());
            dto.setExpirationMonth(value.getExpirationMonth());
            dto.setExpirationYear(value.getExpirationYear());
            dto.setUserId(value.getUser().getId());
            dto.setUserName(value.getUser().getUserName());
            dto.setCreatedDate(value.getCreatedDate().toString());
            dtos.add(dto);
        });
        return dtos;
    }

    public CreditCardResponseDto creditCardToCreditCardResponseDto(CreditCard card) {
        CreditCardResponseDto dto=new CreditCardResponseDto();
        dto.setUserName(card.getUser().getUserName());
        dto.setUserId(card.getUser().getId());
        dto.setCreditCardId(card.getId());
        dto.setExpirationYear(card.getExpirationYear());
        dto.setExpirationMonth(card.getExpirationMonth());
        dto.setCreatedDate(card.getCreatedDate().toString());
        return dto;
    }
}
