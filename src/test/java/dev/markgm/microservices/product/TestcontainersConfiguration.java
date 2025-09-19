package dev.markgm.microservices.product;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    MongoDBContainer mongoDbContainer() {
        final DockerImageName mongoDbImage = DockerImageName
                .parse("mongo:8.0.12@sha256:108c3c1646aa75e8f75361024ae3f90b2ba9d2ad6d442d042bdeb269ef8d15bf")
                .asCompatibleSubstituteFor("mongo");
        return new MongoDBContainer(mongoDbImage);
    }

}
