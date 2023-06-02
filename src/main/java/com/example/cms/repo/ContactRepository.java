package com.example.cms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.cms.entity.Contact;

@EnableJpaRepositories
public interface ContactRepository extends JpaRepository<Contact, Long> {

	Optional<Contact> findByFirstNameContainingIgnoreCase(String firstName);

	Optional<Contact> findByLastNameContainingIgnoreCase(String lastName);

	Optional<Contact> findByEmailContainingIgnoreCase(String email);
}