package rsa.entity;
import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.*;


public abstract class BaseEntity {
	
	
	protected BaseEntity() {
		super();
		System.out.println("Contructor: "+ToStringBuilder.reflectionToString(this));
	}
	
	@CreatedBy
	protected String createdBy;
	
	@CreatedDate
	protected Date createdDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	@Version
	@LastModifiedDate
	protected Date lastModifiedDate;
	
	public String toString() {
	      return new ToStringBuilder(this).
	    		  append("lastModifiedDate",lastModifiedDate).
	    		  append("lastModifiedDate",lastModifiedDate)
	    		  .toString();
	    }

}
