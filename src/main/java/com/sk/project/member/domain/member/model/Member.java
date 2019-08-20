package com.sk.project.member.domain.member.model;

import javax.persistence.Entity;

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
	private Sex sex;
}
