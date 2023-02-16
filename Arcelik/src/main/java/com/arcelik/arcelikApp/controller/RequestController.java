package com.arcelik.arcelikApp.controller;

import com.arcelik.arcelikApp.entity.Requests;
import com.arcelik.arcelikApp.model.SaveRequest;
import com.arcelik.arcelikApp.model.UpdateRequestStatus;
import com.arcelik.arcelikApp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
	@Autowired
	RequestService requestService;

	@PostMapping()
	@CrossOrigin
	public Requests postRequest(@RequestBody SaveRequest saveRequest) throws ParseException {
		return requestService.saveRequest(saveRequest);
	}
	@GetMapping("/getAllRequests")
	@CrossOrigin
	public List<Requests> getAllRequests() {
		return requestService.getAllRequests();
	}

	@GetMapping("/getAllPendingRequests")
	@CrossOrigin
	public List<Requests> getAllPendingRequests() {
		return requestService.getAllPendingRequests();
	}

	@PostMapping("/getRequest")
	@CrossOrigin
	public ResponseEntity<Requests> updateRequestStatus(@RequestBody UpdateRequestStatus updateRequestStatus) {

		return new ResponseEntity<>(requestService.updateRequestStatus(updateRequestStatus), HttpStatus.OK);
	}
	
}
