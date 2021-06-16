package com.vn.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entity.Account;
import com.vn.model.AccountDto;

@Service
public class AccountMapper {
	@Autowired
	public  ModelMapper mapper;

	public  Account convertToEntity(AccountDto accountDto) {
		Account entity = mapper.map(accountDto,Account.class);
				return entity;
	}
	
	public  AccountDto convertToDTO(Account account) {
		AccountDto accountDto = mapper.map(account, AccountDto.class);
		return accountDto;
	}
}
