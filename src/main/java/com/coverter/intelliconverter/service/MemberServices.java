package com.coverter.intelliconverter.service;

import com.coverter.intelliconverter.datatransferobject.MemberDataTransferObject;
import com.coverter.intelliconverter.model.Members;

public interface MemberServices {

	public Members saveMember(Members members);

	public Members saveMembersDataTransferObject(MemberDataTransferObject membersDataTransferObject);
}