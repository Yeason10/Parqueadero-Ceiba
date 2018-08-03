package Estacionamiento.Estacionamiento.Servicio;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Estacionamiento.Estacionamiento.Vehiculo;

@Service
public class Factura 
{
   private static final int VALOR_HORA_CARRO = 1000;
   private static final int VALOR_HORA_MOTO = 500 ;
   private static final int VALOR_DIA_CARRO = 8000;
   private static final int VALOR_DIA_MOTO = 4000;
   private static final int MINUTOS_EN_UNA_HORA=60;
   private static final int MINUTOS_EN_UN_DIA = 1440;
   private static final int MINUTOS_EN_NUEVE_HORAS=540;
   
   @Autowired
   Fecha fecha;
   
   
   
   
   
   public long cobroSalidaDeVehiculo(Vehiculo vehiculo)
   {
	   System.out.println(vehiculo.getFechaIngreso());
	   DateTime fechaIngreso= new DateTime(vehiculo.getFechaIngreso());
 	   Duration tiempoEnEstacionamiento = fecha.obtenertCantDeTiempoEnParqueadero(fechaIngreso, fecha.fechaSalida());
	   System.out.println(tiempoEnEstacionamiento.getStandardMinutes());
 	   if(vehiculo.getTipo().equals("moto")) 
	   {
	      if(Integer.parseInt(vehiculo.getCilindraje()) > 500) 
	      {
	    	  if(tiempoEnEstacionamiento.getStandardMinutes() > MINUTOS_EN_NUEVE_HORAS )//SE QUEDO MAS DE 9 HORAS
	    	    return cobroMotoMayorANueveHoras(tiempoEnEstacionamiento);
	    	  else
	    	    return cobroMotoMenorANueveHoras(tiempoEnEstacionamiento); 
	      }
	      else
	      { 
	    	  if(tiempoEnEstacionamiento.getStandardMinutes() > MINUTOS_EN_NUEVE_HORAS )
	    	  {//SE QUEDO MAS DE 9 HORAS
		       return (cobroMotoMayorANueveHoras(tiempoEnEstacionamiento)-2000);
	          } 
		      else
		      {
		        return (cobroMotoMenorANueveHoras(tiempoEnEstacionamiento)-2000);
		      }
	      } 
	    
	    }
	    else
	    	if(tiempoEnEstacionamiento.getStandardMinutes() > MINUTOS_EN_NUEVE_HORAS )//SE QUEDO MAS DE 9 HORAS
	    	    return cobroCarroMayorANueveHoras(tiempoEnEstacionamiento);
	    	  else
	    	    return cobroCarroMenorANueveHoras(tiempoEnEstacionamiento);
	  }

   public long cobroMotoMayorANueveHoras(Duration cantTiempoParqueadero)
   {
	   if(cantTiempoParqueadero.getStandardMinutes() / MINUTOS_EN_UN_DIA == 0)//No se quedo mas de un dia.
		    return (VALOR_DIA_MOTO + 2000);
	   else if(cantTiempoParqueadero.getStandardMinutes() % MINUTOS_EN_UN_DIA != 0)//Se quedo mas de un dia (dias NO exactos)
		   return ((cantTiempoParqueadero.getStandardDays()*VALOR_DIA_MOTO ) + VALOR_DIA_MOTO + 2000);
	   else //Se quedo mas de un dia (dias exactos)
		   return ((cantTiempoParqueadero.getStandardDays()*VALOR_DIA_MOTO) + 2000);
   }

   public long cobroMotoMenorANueveHoras(Duration cantTiempoParqueadero)
   {
	   if(cantTiempoParqueadero.getStandardMinutes() % MINUTOS_EN_UNA_HORA ==  0)//Horas exactas.
		   return ((VALOR_HORA_MOTO * cantTiempoParqueadero.getStandardHours()) + 2000);
	   else //Horas No exactas
		   return (((cantTiempoParqueadero.getStandardHours())*VALOR_HORA_MOTO) + VALOR_HORA_MOTO + 2000);
   }

   public long cobroCarroMayorANueveHoras(Duration cantTiempoParqueadero)
   {
	   if(cantTiempoParqueadero.getStandardMinutes() / MINUTOS_EN_UN_DIA == 0)//No se quedo mas de un dia.
		    return VALOR_DIA_CARRO;
	   else if(cantTiempoParqueadero.getStandardMinutes() % MINUTOS_EN_UN_DIA != 0)//Se quedo mas de un dia (dias NO exactos)
		   return ((cantTiempoParqueadero.getStandardDays()*VALOR_DIA_CARRO ) + VALOR_DIA_CARRO);
	   else //Se quedo mas de un dia (dias exactos)
		   return ((cantTiempoParqueadero.getStandardDays()*VALOR_DIA_CARRO)); 
   }
   
   public long cobroCarroMenorANueveHoras(Duration cantTiempoParqueadero)
   {
	   if(cantTiempoParqueadero.getStandardMinutes() % MINUTOS_EN_UNA_HORA ==  0)//Horas exactas.
		   return (VALOR_HORA_CARRO * cantTiempoParqueadero.getStandardHours());
	   else //Horas No exactas
		   return (((cantTiempoParqueadero.getStandardHours())*VALOR_HORA_CARRO) + VALOR_HORA_CARRO);
   }
   
}
