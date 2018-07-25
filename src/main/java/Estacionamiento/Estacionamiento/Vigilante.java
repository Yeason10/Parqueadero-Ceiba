package Estacionamiento.Estacionamiento;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Estacionamiento.Estacionamiento.Persistencia.Almacenamiento;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;

@Service
public class Vigilante 
{
  public static int cantMotos;
  public static int cantCarros;
  
  @Autowired
  Almacenamiento almacenamiento;
  
  public Vigilante(){}
  
  public Vehiculo registroEntradaVehiculo(Vehiculo vehiculo) throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	verificacionTipoVehiculo(vehiculo);
    verificacionCantidadVehiculos(vehiculo);
    verificacionPlaca(vehiculo);

	return almacenamiento.almacenamientoVehiculo(vehiculo);
  }
  
  public void registroSalidaVehiculo(Vehiculo vehiculo)
  {
    //Por implementar
  }

  public void verificacionTipoVehiculo(Vehiculo vehiculo)
  {
	if(vehiculo.getTipo().equals("moto"))
	  cantMotos++;
	else
      cantCarros++;
   System.out.println("+++++++++++++++++++++++++++++++*********************" + cantCarros);
  }
  
  public boolean verificacionCantidadVehiculos(Vehiculo vehiculo) throws ExcepcionRangoVehiculos
  {
	 if (((cantMotos > 10)&&(vehiculo.getTipo().equals("moto")))||((cantCarros > 20)&&(vehiculo.getTipo().equals("carro"))))
	 {
	   throw new ExcepcionRangoVehiculos("Numero de vehiculos superior al permitido");
	 }
	return true;
  }
  
  public boolean verificacionPlaca(Vehiculo vehiculo) throws ExcepcionDiaInvalido
  {
	  if (((vehiculo.getPlaca()).charAt(0) == 'A') && !(verificacionFecha(vehiculo)))
	  {
		throw new ExcepcionDiaInvalido("Dia invalido para ingresar al estacionamiento");
	  }
      return true;
  }
  
  public boolean verificacionFecha(Vehiculo vehiculo) 
  {
	Calendar calendar = Calendar.getInstance();
	return ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)||(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY));
  }
 

}
