package com.ragender.controller;

import com.rajender.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContactController {
	
	private ContactService contactService;

	public ContactController() {

	}
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	

}
