package com.mycomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycomp.service.StorageService;

@RestController
@RequestMapping("/assets")
public class AssetsController {

	@Autowired
	private StorageService strgsrvc;

	@GetMapping("/{filename:.+}") // .+ -> PARA INDICARLE LA EXTENSION.
	Resource getResource(@PathVariable("filename") String filename) {

		return strgsrvc.loadAsResource(filename);

	}

}
