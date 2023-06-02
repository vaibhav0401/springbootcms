package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.ContactDetails;
import com.example.cms.entity.Contact;
import com.example.cms.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contacts")
@Api(tags = "Contact API")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@ApiOperation("Get all contacts")
	@GetMapping
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}

	@ApiOperation("Get a contact by either firstname, lastname, email")
	@GetMapping("/{firstName}/{lastName}/{email}")
	public ResponseEntity<Contact> getContacts( @PathVariable("firstName") String firstName, @PathVariable("lastName")  String lastName, @PathVariable("email")  String email) {
		return contactService.searchContact(firstName,lastName,email);
	}
	@Secured("ROLE_ADMIN")
	@ApiOperation("Create a new contact")
	@PostMapping
	public Contact createContact(@RequestBody ContactDetails contactDetails) {
		return  contactService.saveContact(contactDetails);

	}
	@Secured("ROLE_ADMIN")
	@ApiOperation("Update an existing contact")
	@PutMapping("/{email}/{phone_number}")
	public ResponseEntity<Contact> updateContact( @PathVariable("email") String email, @PathVariable("phone_number") String phoneNumber) {
		return contactService.updateContact(email,phoneNumber);
	}
	@Secured("ROLE_ADMIN")
	@ApiOperation("Delete a contact")
	@DeleteMapping("/{email}")
	public ResponseEntity<Contact> deleteContact( @PathVariable("email") String email) {
		return contactService.deleteContact(email);
	}

}