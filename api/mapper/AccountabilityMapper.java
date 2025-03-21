package com.cit.backend.api.mapper;

import com.cit.backend.api.request.AccountabilityRequest;
import com.cit.backend.api.response.AccountabilityResponse;
import com.cit.backend.domain.entity.Income;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountabilityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Income toIncome(AccountabilityRequest accountabilityRequest) {
        return modelMapper.map(accountabilityRequest, Income.class);
    }

    public AccountabilityResponse toAccountabilityResponse(Income income) {
        return modelMapper.map(income, AccountabilityResponse.class);
    }

    public List<AccountabilityResponse> toAccountabilityResponse(List<Income> incomes) {
        return incomes.stream().map(this::toAccountabilityResponse).toList();
    }
}
