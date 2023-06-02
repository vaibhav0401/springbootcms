package com.example.cms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.ContactDetails;
import com.example.cms.entity.Contact;

public interface ContactService {

	List<Contact> getAllContacts();

	ResponseEntity<Contact> searchContact(String firstName, String lastName, String email);

	Contact saveContact(ContactDetails contactDetails);

	ResponseEntity<Contact> updateContact(String email, String phoneNumber);

	ResponseEntity<Contact> deleteContact(String email);
}