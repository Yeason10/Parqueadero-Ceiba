package Estacionamiento.Estacionamiento.Servicio;

import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;

public class Factura 
{
   private static final int VALOR_HORA_CARRO = 1000;
   private static final int VALOR_HORA_MOTO = 500;
   private static final int VALOR_DIA_CARRO = 8000;
   private static final int VALOR_DIA_MOTO = 4000;
   
   private int total_A_Pagar;
   
   
   
   public int cobroSalidaDeVehiculo(Vehiculo vehiculo)
   {
	   
	    if((vehiculo.getTipo().equals("moto"))&&(Integer.parseInt(vehiculo.getCilindraje()) > 500))
	    {
	    	  
	    	 
	    		 
	    	 
	    	
	    }
	   return 0;
	  
   }

   public int cobroCantHorasMotoParqueadero(Duration cantHorasParqueadero)
   {
	    
	   return 0;
   
   }




}
