package com.eventoapp.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.eventoapp.models.Login;

public interface LoginRepository extends CrudRepository<Login, String>{
}