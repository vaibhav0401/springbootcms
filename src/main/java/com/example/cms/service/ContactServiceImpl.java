package com.example.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.ContactDetails;
import com.example.cms.entity.Contact;
import com.example.cms.repo.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();

	}

	@Override
	public ResponseEntity<Contact> searchContact(String firstName, String lastName, String email) {
		Optional<Contact> optionalContact = Optional.empty();
		if (firstName != null) {
			optionalContact =contactRepository.findByFirstNameContainingIgnoreCase(firstName);
		}else
		if (lastName != null) {
			optionalContact =contactRepository.findByLastNameContainingIgnoreCase(lastName);
		} else
		if (email != null) {
			optionalContact = contactRepository.findByEmailContainingIgnoreCase(email);
		}

		if (optionalContact.isPresent()) {
			return ResponseEntity.ok(optionalContact.get());
		}

		return ResponseEntity.notFound().build();

	}

	@Override
	public Contact saveContact(ContactDetails contactDetails) {
		var contact = new Contact();
		contact.setFirstName(contactDetails.getFirstName());
		contact.setLastName(contactDetails.getLastName());
		contact.setEmail(contactDetails.getEmail());
		contact.setPhoneNumber(contactDetails.getPhoneNumber());
		return contactRepository.save(contact);

	}

	@Override
	public ResponseEntity<Contact> updateContact(String email, String phoneNumber) {
		var optionalContact = contactRepository.findByEmailContainingIgnoreCase(email);
		if (optionalContact.isPresent()) {
			var contact = optionalContact.get();

			contact.setPhoneNumber(phoneNumber);
			contactRepository.save(contact);
			return ResponseEntity.ok(contact);
		}
		return ResponseEntity.notFound().build();
	}
	@Override
	public ResponseEntity<Contact> deleteContact(String email) {
		var optionalContact = contactRepository.findByEmailContainingIgnoreCase(email);
		if (optionalContact.isPresent()) {
			contactRepository.delete(optionalContact.get());
			return ResponseEntity.status(200).build();
		}

		return ResponseEntity.notFound().build();
	}

}