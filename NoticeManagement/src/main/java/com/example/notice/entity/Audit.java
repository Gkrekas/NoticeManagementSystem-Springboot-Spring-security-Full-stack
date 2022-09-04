package com.example.notice.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {
	
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name="create_dt", nullable = false, updatable = false)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name="update_dt")
	private Date updateDate;
	
	public Audit() {}

	public Audit(Date createDate, Date updateDate) {
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Audit [createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
}
