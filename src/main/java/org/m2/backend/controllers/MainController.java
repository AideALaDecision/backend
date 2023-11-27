package org.m2.backend.controllers;

import java.util.List;
import java.util.Map;

import org.m2.backend.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
	DataGenerator dg;
	GaleShapley gp;
	List<Affectation> affectation;
	
	@GetMapping(path = "/preferences")
	public DataGenerator preferences(@RequestParam int size, @RequestParam int caseID){ //caseID = -1 worst || 0 normal || 1 perfect
		dg= new DataGenerator(size);
		
		switch(caseID) {
		case -1 : 
			dg.setWorstCase();
			break;
		case 0 : 
			dg.setNormalCase();
			break;
		case 1 : 
			dg.setPerfectCase();
			break;
		}
        
		return dg;
	}
	
	@GetMapping(path = "/mariage")
	public List<Affectation> findStableMatch(){
		gp = new GaleShapley(dg);
		affectation=gp.findStableMatch(dg.getStudents(),dg.getEstablishments());
		return affectation;
	}

	@GetMapping(path = "/satisfaction")
	public Satisfaction staisfaction(){
		Satisfaction sat=new Satisfaction(affectation);
		sat.satisfactionStudents(dg.getStudents());
		sat.satisfactionEstablishments(dg.getEstablishments());
		sat.satisfaction();
		return sat;

	}
		
}
