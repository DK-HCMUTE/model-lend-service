package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;
import com.my.motelApp.service.HomeService;
import com.my.motelApp.service.WardService;

@RestController
@RequestMapping("api/v1/wards")
public class WardController {

	@Autowired
	HomeService homeService;

	@Autowired
	WardService wardService;

	@GetMapping
	private List<Ward> getAllWards(){
		return wardService.getAllWards();
	}
	
	@GetMapping("/{ward_id}/homes/")
	private ResponseEntity<List<Home>> getHomesByWardId(@PathVariable("ward_id") Long wardId) {
		List<Home> homeRespone = homeService.getHomesByWardId(wardId);
		return new ResponseEntity<>(homeRespone,HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<Ward> createWard(@RequestBody Ward wardRequest){
		Ward wardResponse =  wardService.createWard(wardRequest);
		return new ResponseEntity<>(wardResponse,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/{ward_id}/homes")
	private ResponseEntity<Home> addHomeByWardId(@PathVariable("ward_id") Long wardId, @RequestBody Home homeRequest) {
		
		Home homeResponse = wardService.addHome(wardId,homeRequest);
		return new ResponseEntity<>(homeResponse,HttpStatus.CREATED); 
	}
	
	
	
	@PutMapping("/{ward_id}")
	private ResponseEntity<Ward> updateNameById(@PathVariable("ward_id") Long wardId,@RequestBody Ward wardRequest){
		Ward wardRespone = wardService.updateNameById(wardId, wardRequest);
		return new ResponseEntity<>(wardRespone,HttpStatus.OK);
	}

	@DeleteMapping("/{ward_id}")
	private  ResponseEntity<String> deleteById(@PathVariable("ward_id") Long wardId){
		wardService.delete(wardId);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY,HttpStatus.OK);
	}
	
	
	
}
