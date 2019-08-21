package com.sk.project.member.domain.member.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sk.project.member.domain.base.AbstractEntity;

import lombok.Data;

@Data
@Entity
public class Member extends AbstractEntity {
	private String name;
	private String login_id;
	private Long age;
	private String mbr_sts;
	private String mbr_addr;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
