// src/main/java/com/aluracursos/challenge/ChallengeLiterAluraApplication.java
package com.aluracursos.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import com.aluracursos.challenge.principal.PrincipalChallenge;

@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class
		}
)
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	private final PrincipalChallenge principalChallenge;

	public ChallengeLiterAluraApplication(PrincipalChallenge principalChallenge) {
		this.principalChallenge = principalChallenge;
	}

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		principalChallenge.muestraElMenu();
	}
}
