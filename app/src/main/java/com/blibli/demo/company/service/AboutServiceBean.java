package com.blibli.demo.company.service;

import com.blibli.demo.company.entity.About;
import com.blibli.demo.company.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutServiceBean implements AboutService {

	@Autowired
	private AboutRepository aboutRepository;

	@Override
	public List<About> find() {
		return aboutRepository.findAllBy();
	}
}
