package com.nice.batallanaval;

import com.nice.batallanaval.bl.CampoBatalla;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatallanavalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatallanavalApplication.class, args);

		CampoBatalla campoBatalla = new CampoBatalla();
		campoBatalla.inicializaJuego();
		campoBatalla.iniciaBatalla();


	}
}
