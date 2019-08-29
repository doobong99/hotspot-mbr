package com.sk.project.member.domain.member.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.project.member.domain.member.model.Member;

@RepositoryRestResource
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, QuerydslPredicateExecutor<Member>{
	
}
