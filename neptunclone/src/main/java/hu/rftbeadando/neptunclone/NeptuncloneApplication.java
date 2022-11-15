package hu.rftbeadando.neptunclone;

import hu.rftbeadando.neptunclone.model.HallgatoModel;
import hu.rftbeadando.neptunclone.repository.HallgatoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NeptuncloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeptuncloneApplication.class, args);
	}

}
