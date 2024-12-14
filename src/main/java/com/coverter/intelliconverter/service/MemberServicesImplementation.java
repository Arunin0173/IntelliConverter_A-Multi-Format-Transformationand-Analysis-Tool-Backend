package com.coverter.intelliconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coverter.intelliconverter.datatransferobject.MemberDataTransferObject;
import com.coverter.intelliconverter.model.Members;
import com.coverter.intelliconverter.repository.MemberRepository;
@Service
public class MemberServicesImplementation implements MemberServices {

	@Autowired
	private MemberRepository memberRepository;
	
	
	@Override
	public Members saveMember(Members members) {
	
		return memberRepository.save(members);
	}


	@Override
	public Members saveMembersDataTransferObject(MemberDataTransferObject membersDataTransferObject) {
		
		return memberRepository.findByGmailAndPassword(membersDataTransferObject.getGmail(),membersDataTransferObject.getPassword());
	}

	
}