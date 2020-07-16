package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.ContactField;
import com.blibli.demo.company.repository.ContactFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFieldServiceBean implements ContactFieldService {

	@Autowired
	private ContactFieldRepository contactFieldRepository;

	@Override
	public List<ContactField> find() {
		return contactFieldRepository.findByMarkForDeleteFalse();
	}
}
