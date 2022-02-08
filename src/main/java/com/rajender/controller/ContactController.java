package com.rajender.controller;

import com.rajender.model.Contact;
import com.rajender.service.ContactService;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	
	private ContactService contactService;

	public ContactController() {

	}
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView hello(HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	// Get All Contacts
		@RequestMapping(value = "/allContacts", method = RequestMethod.POST)
		public ModelAndView displayAllContact() {
			System.out.println("User Page Requested : All Contacts");
			ModelAndView mv = new ModelAndView();
			List contactList = contactService.getAllContacts();
			mv.addObject("userContact", contactList);
			mv.setViewName("allContacts");
			return mv;
		}
		@RequestMapping(value = "/addContact", method = RequestMethod.GET)
		public ModelAndView displayNewContactForm() {
			ModelAndView mv = new ModelAndView("addContact");
			mv.addObject("headerMessage", "Add Contact Details");
			mv.addObject("contact", new Contact());
			return mv;
		}
		@RequestMapping(value = "/addUser", method = RequestMethod.POST)
		public ModelAndView saveNewContact(@ModelAttribute Contact contact, BindingResult result) {
			ModelAndView mv = new ModelAndView("redirect:/home");

			if (result.hasErrors()) {
				return new ModelAndView("error");
			}
			boolean isAdded = contactService.saveContact(contact);
			if (isAdded) {
				mv.addObject("message", "New contact successfully added");
			} else {
				return new ModelAndView("error");
			}

			return mv;
		}
		@RequestMapping(value = "/editContact/{id}", method = RequestMethod.GET)
		public ModelAndView displayEditContactForm(@PathVariable Long id) {
			ModelAndView mv = new ModelAndView("/editContact");
			Contact contact = contactService.getContactById(id);
			mv.addObject("headerMessage", "Edit Contact Details");
			mv.addObject("contact", contact);
			return mv;
		}
		@RequestMapping(value = "/editContact/{id}", method = RequestMethod.POST)
		public ModelAndView saveEditedContact(@ModelAttribute Contact contact, BindingResult result) {
			ModelAndView mv = new ModelAndView("redirect:/home");

			if (result.hasErrors()) {
				System.out.println(result.toString());
				return new ModelAndView("error");
			}
			boolean isSaved = contactService.saveContact(contact);
			if (!isSaved) {

				return new ModelAndView("error");
			}

			return mv;
		}
		@RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.GET)
		public ModelAndView deleteContactById(@PathVariable Long id) {
			boolean isDeleted = contactService.deleteContactById(id);
			System.out.println("User deletion respone: " + isDeleted);
			ModelAndView mv = new ModelAndView("redirect:/home");
			return mv;

		}

			
	

}
