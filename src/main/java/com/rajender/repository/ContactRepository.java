package com.rajender.repository;

import org.springframework.data.repository.CrudRepository;

import com.rajender.model.Contact;


public interface ContactRepository extends CrudRepository<Contact, Long>{

}
