package com.rajender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajender.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{

}
