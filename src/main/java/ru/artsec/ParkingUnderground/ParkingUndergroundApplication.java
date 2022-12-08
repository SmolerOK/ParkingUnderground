package ru.artsec.ParkingUnderground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ParkingUndergroundApplication {
	public static void main(String[] args) {
		log.info("Запуск программы.");
		SpringApplication.run(ParkingUndergroundApplication.class, args);
	}
}
