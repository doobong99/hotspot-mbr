package com.sk.project.member.domain.member.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.sk.project.member.domain.member.model.Member;


@RepositoryRestResource
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, QueryDslPredicateExecutor<Member>{
	
}
