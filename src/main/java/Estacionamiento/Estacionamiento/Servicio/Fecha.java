package Estacionamiento.Estacionamiento.Servicio;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.stereotype.Service;


@Service
public class Fecha  
{  
	
	
	public Duration obtenertCantDeTiempoEnParqueadero(DateTime fechaIngreso, DateTime fechaSalida)
	{
		 
		return  new Duration(fechaIngreso,fechaSalida); 
		   
	}

   public DateTime fechaSalida()
   {
	   return DateTime.now();
   }



}
