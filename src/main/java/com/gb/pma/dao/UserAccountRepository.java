package com.gb.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gb.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
