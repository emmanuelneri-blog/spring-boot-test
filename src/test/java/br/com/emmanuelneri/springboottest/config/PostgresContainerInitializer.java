package br.com.emmanuelneri.springboottest.config;

import org.junit.Rule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Rule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:12-alpine")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("postgres");

    public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
        postgreSQLContainer.start();
        TestPropertyValues.of(
                "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                "spring.datasource.password=" + postgreSQLContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }

}
