package com.coverter.intelliconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coverter.intelliconverter.model.Members;
@Repository
public interface MemberRepository extends JpaRepository<Members, Long>{

	Members findByGmailAndPassword(String gmail, String password);

}