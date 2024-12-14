package com.coverter.intelliconverter.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coverter.intelliconverter.datatransferobject.MemberDataTransferObject;
import com.coverter.intelliconverter.model.Members;
import com.coverter.intelliconverter.service.MemberServices;

@RestController
public class MemberController {
	
	@Autowired
	private MemberServices memberServices;
	
	@PostMapping("saveMember")
	public Map<String,Object> saveMember(@RequestBody Members members){
		Map<String,Object> response = new HashMap<>();
		Members member = memberServices.saveMember(members);
		if(member != null) {
			
			response.put("submission","SUCCESSFULLY");
			response.put("status", HttpStatus.OK);
		}
		else {
			response.put("submission","failed");
			response.put("status",HttpStatus.BAD_REQUEST);
			
		}
		
		return response;
	}
	
	@PostMapping("login")
	public Map<String,Object> login(@RequestBody MemberDataTransferObject membersDataTransferObject){
		Map<String,Object> response = new HashMap<>();
		Members m = memberServices.saveMembersDataTransferObject(membersDataTransferObject);
		if(m != null) {
		
			response.put("submission","SUCCESSFULLY");
			response.put("status", HttpStatus.OK);
		}
		else {
			response.put("submission","failed");
			response.put("status",HttpStatus.BAD_REQUEST);
			
		}
		
		return response;
	}
	

}