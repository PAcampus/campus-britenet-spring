package pl.britenet.spring.campusbritenetspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.campus.database.DatabaseService;

@Configuration
public class DatabaseConfig {

    @Bean
    public DatabaseService getDatabaseService() {
        return new DatabaseService();
    }
}
