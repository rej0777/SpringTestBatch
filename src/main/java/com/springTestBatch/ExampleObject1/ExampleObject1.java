package com.springTestBatch.ExampleObject1;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "ExampleObject1" )
public  class ExampleObject1 {
	ExampleObject1(){}
	@Id 
	@Column(name = "ID", unique = true )	// nullable = false
//	@SequenceGenerator(name = "MY_SEQ", sequenceName = "EOseq")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ") //GenerationType.SEQUENCE,
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	 private Long id;                      
	    private String name;
	
	
	 public ExampleObject1(String name) {
		this.name =name;
	}
	 
	
	  
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	    
	    
	    
}                      
                