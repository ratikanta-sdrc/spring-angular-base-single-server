package org.sdrc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.sdrc.security.Auditor;

//Note Audit column will be automatically

@Entity
@Table(name = "custom_tbl_to_test_auditing")
public class MyCustomDomainUsingSpringDataJpaAudit extends Auditor<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "column_one")
	private String columnOne;

	@Column(name = "column_two")
	private String columnTwo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColumnOne() {
		return columnOne;
	}

	public void setColumnOne(String columnOne) {
		this.columnOne = columnOne;
	}

	public String getColumnTwo() {
		return columnTwo;
	}

	public void setColumnTwo(String columnTwo) {
		this.columnTwo = columnTwo;
	}

}
