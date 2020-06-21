package br.com.emmanuelneri.springboottest;

import br.com.emmanuelneri.springboottest.config.PostgresContainerInitializer;
import lombok.Data;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
class SpringBootTestApplicationIT {

    @LocalServerPort
    private int port;

    @Test
    void shouldHealthyApplication() {
        final String url = String.format("http://localhost:%d/actuator/health", port);

        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Health> healthResponseEntity = restTemplate.getForEntity(url, Health.class);
        Assert.assertEquals(HttpStatus.OK, healthResponseEntity.getStatusCode());

        final Health health = healthResponseEntity.getBody();
        Assert.assertNotNull(health);
        Assert.assertEquals(Status.UP, health.getStatus());
    }

    @Data
    static class Health {
        private Status status;
    }

}
