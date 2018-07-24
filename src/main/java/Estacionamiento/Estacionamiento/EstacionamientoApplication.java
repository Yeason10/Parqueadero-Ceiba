package Estacionamiento.Estacionamiento;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication
//@EnableJpaAuditing
public class EstacionamientoApplication 
{

	public static void main(String[] args) 
	{
		//SpringApplication.run(EstacionamientoApplication.class, args);
	    Carro carro1 = new Carro("Art-345", new Date());
	    Vigilante vigilante1 = new Vigilante();
	    vigilante1.registroEntradaVehiculo(carro1);
	    
	    
	}
}
