package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import static com.example.demo.core.utils.Constants.POSTGRES_FORTUNE_COOKIE;

@AutoConfigureTestEntityManager
public abstract class AbstractIntegrationTest {

	@Autowired
	protected TestEntityManager em;

	@SuppressWarnings("resource")
	private static final PostgreSQLContainer<?> POSTGRES =
			new PostgreSQLContainer<>("postgres:12.1")
					.withDatabaseName(POSTGRES_FORTUNE_COOKIE)
					.withPassword(POSTGRES_FORTUNE_COOKIE)
					.withUsername(POSTGRES_FORTUNE_COOKIE);

	@DynamicPropertySource
	public static void properties(DynamicPropertyRegistry registry) {
		Startables.deepStart(POSTGRES).join();
		registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
	}
}
