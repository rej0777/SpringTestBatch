package com.springTestBatch.repository;

import java.util.Calendar;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.springTestBatch.ExampleObject1.ExampleObject1;



public interface ExampleObject1Repository extends JpaRepository<ExampleObject1, Object> { 

	
	@Query(value = " select * from dbo.ExampleObject1 where id >2", nativeQuery = true  ) //'2019-01-15  00:00:00'		 
	 Page<ExampleObject1> selectALLV2( Pageable pageable);    ///Stream
	
}
