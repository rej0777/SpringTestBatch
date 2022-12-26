package com.springTestBatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.springTestBatch.ExampleObject1.ExampleObject1;
import com.springTestBatch.repository.ExampleObject1Repository;
import jakarta.transaction.Transactional;

@Component
public class CLRunner implements CommandLineRunner {

	private ExampleObject1Repository exampleObject1Repository;

	public CLRunner(ExampleObject1Repository exampleObject1Repository) {
		super();
		this.exampleObject1Repository = exampleObject1Repository;
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		System.out.println("!!!!!!!! CLRunner   RUN ");
		for (int i = 0; i < 500; i++) {

			ExampleObject1 ob1 = new ExampleObject1("TestName+" + i);
			exampleObject1Repository.save(ob1);

		}
	}

}
