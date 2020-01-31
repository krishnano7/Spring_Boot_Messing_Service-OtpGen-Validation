package com.example.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pojo.OneTimePassword;

@Repository
public interface OtpRepositor extends CrudRepository<OneTimePassword, String> {

}
