package ro.ubb.gunstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.ubb.gunstore.core.repository"
         //,repositoryImplementationPostfix = "JPQLImpl"
          ,repositoryImplementationPostfix = "CriteriaImpl"
        //  ,repositoryImplementationPostfix = "SQLImpl"
)
public class GunStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunStoreApplication.class, args);
    }

}
