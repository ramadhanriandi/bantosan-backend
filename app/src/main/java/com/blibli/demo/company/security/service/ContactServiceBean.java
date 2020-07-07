package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Contact;
import com.blibli.demo.company.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceBean implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void create(Contact contact) {
		contactRepository.save(contact);
	}
}
