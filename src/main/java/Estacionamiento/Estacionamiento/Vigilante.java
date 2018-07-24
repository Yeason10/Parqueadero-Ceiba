package Estacionamiento.Estacionamiento;

import java.util.Calendar;
import java.util.Date;

import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;

public class Vigilante 
{
  public static int cantMotos;
  public static int cantCarros;
  
  public Vigilante(){}
  
  public void registroEntradaVehiculo(Vehiculo vehiculo)
  {
	verificacionTipoVehiculo(vehiculo);

	try
	{
	 verificacionCantidadVehiculos();
	}
	catch(ExcepcionRangoVehiculos e)
	{
     System.err.println(e.getMessage());    
    }
    
	try
	{
     verificacionPlaca(vehiculo);
	}
	catch(ExcepcionDiaInvalido e)
	{
	 System.err.println(e.getMessage());
	}
    
  }
  
  public void registroSalidaVehiculo(Vehiculo vehiculo)
  {
    //Por implementar
  }

  public void verificacionTipoVehiculo(Vehiculo vehiculo)
  {
	if(vehiculo instanceof Moto)
	  cantMotos++;
	else
      cantCarros++;
  }
  
  public void verificacionCantidadVehiculos() throws ExcepcionRangoVehiculos
  {
	 if ((cantMotos > 20)|| (cantCarros > 10))
	 {
	   throw new ExcepcionRangoVehiculos("Numero de vehiculos superior al permitido");
	 }
  }
  
  public void verificacionPlaca(Vehiculo vehiculo) throws ExcepcionDiaInvalido
  {
	  if (((vehiculo.getPlaca()).charAt(0) == 'A') && (verificacionFecha(vehiculo)))
	  {
		throw new ExcepcionDiaInvalido("Dia invalido para ingresar al estacionamiento");
	  }
  }
  
  public boolean verificacionFecha(Vehiculo vehiculo) 
  {
	Date fecha = vehiculo.getFechaIngreso();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(fecha);
	if((calendar.get(Calendar.DAY_OF_WEEK) == 1)||(calendar.get(Calendar.DAY_OF_WEEK) == 2))
	 return true;
	else
	 return false;
   }
 

}
