package com.blibli.demo.company.controller;

import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.company.entity.About;
import com.blibli.demo.company.security.service.AboutService;
import com.blibli.demo.dto.AboutResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = AboutControllerPath.BASE_PATH)
public class AboutController {

	@Autowired
	private AboutService aboutService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ListBaseResponse<AboutResponse> find() throws Exception {
		List<About> abouts = this.aboutService.find();
		List<AboutResponse> aboutResponses = new ArrayList<>();

		for (About about : abouts) {
			AboutResponse aboutResponse = AboutResponse.builder().build();
			BeanUtils.copyProperties(about, aboutResponse);
			aboutResponses.add(aboutResponse);
		}
		return new ListBaseResponse<>(null, null, true, null, aboutResponses,null);
	}
}
