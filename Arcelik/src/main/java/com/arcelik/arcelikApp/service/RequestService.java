package com.arcelik.arcelikApp.service;

import com.arcelik.arcelikApp.entity.Requests;
import com.arcelik.arcelikApp.entity.User;
import com.arcelik.arcelikApp.model.SaveRequest;
import com.arcelik.arcelikApp.model.UpdateRequestStatus;
import com.arcelik.arcelikApp.repository.RequestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class RequestService {
	@Autowired
	RequestRepository requestRepository;
	@Autowired
	UserService userService;

	public Requests saveRequest(SaveRequest saveRequest) throws ParseException {
		Requests requests = new Requests();
		requests.setRequestDescription(saveRequest.getRequestDescription());
		requests.setRequestReason(saveRequest.getRequestReason());
		requests.setBudgetNumber(saveRequest.getBudgetNumber());
		requests.setDeadline(new SimpleDateFormat("yyyy-MM-dd").parse(saveRequest.getDeadline()));
		requests.setNumberOfProduct(saveRequest.getNumberOfProduct());

		requests.setCompanyName(saveRequest.getCompanyName());
		requests.setInkNumber(saveRequest.getInkNumber());
		requests.setSkuList(saveRequest.getSkuList());
		requests.setProjectName(saveRequest.getProjectName());
		User user = userService.getUserById(saveRequest.getUserId());
		requests.setUser(user);
		return requestRepository.save(requests);
	}

	public List<Requests> getAllRequests() {
		return requestRepository.findAll();
	}

	public Requests updateRequestStatus(UpdateRequestStatus updateRequestStatus) {
		Optional<Requests> optionalRequests = requestRepository.findByRequestId(updateRequestStatus.getRequestId());
		if (optionalRequests.isEmpty()){
			throw new RuntimeException();
		}
		Requests request = optionalRequests.get();
		request.setStatus(updateRequestStatus.getStatus());
		request.setReasonOfReject(updateRequestStatus.getReasonOfReject());
		return requestRepository.save(request);
	}

	public List<Requests> getAllPendingRequests() {
		return requestRepository.findAllStatusEqualPending();
	}
}
