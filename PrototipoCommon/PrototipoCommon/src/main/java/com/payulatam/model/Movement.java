package com.payulatam.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceRouting;


/**
 * The persistent class for the movement database table.
 * @author John
 */
@Entity
@Table(name="movement")
@SpaceClass(persist=true)
public class Movement extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="movementdate")
	private Date movementDate;

	@Enumerated(EnumType.STRING)
	private String type;

	private BigDecimal value;
	
	private String accountId;
	
	private boolean processed;

	public Movement() {
	}

	public Date getMovementDate() {
		return this.movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	@Override
	@SpaceRouting
	public Long getSpacerouting() {
		return this.spacerouting;
	}

	@Override
	public void setSpacerouting(Long spacerouting) {
		this.spacerouting = spacerouting;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
}