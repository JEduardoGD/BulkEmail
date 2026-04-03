package egd.fmre.bulkemail;

import egd.fmre.bulkemail.service.BulkEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class BulkEmailApplication implements CommandLineRunner {

	private final BulkEmailService bulkEmailService;

	public static void main(String[] args) {
		SpringApplication.run(BulkEmailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Sending bulk email");
		bulkEmailService.sendBulkEmail();
		log.info("Bulk email sent");
	}
}
