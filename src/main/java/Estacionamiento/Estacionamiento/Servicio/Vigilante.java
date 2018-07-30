package Estacionamiento.Estacionamiento.Servicio;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Fabrica.Celdas;
import Estacionamiento.Estacionamiento.Fabrica.CeldasFabrica;
import Estacionamiento.Estacionamiento.Iservicio.IVigilante;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorio;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class Vigilante implements IVigilante
{
  @Autowired //Inyeccion de dependencias.
  Almacenamiento almacenamiento;
  
  @Autowired
  VehiculoRepositorio vehiculoRepositorio;
   
  public Vehiculo registroEntradaVehiculo(Vehiculo vehiculo,Celdas celdas) throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	verificacionCantidadVehiculos(vehiculo,celdas);
    verificacionPlaca(vehiculo);
    return almacenamiento.almacenamientoVehiculo(vehiculo);
   }
  
  public void registroSalidaVehiculo(Vehiculo vehiculo)
  {
    //Por implementar
  }

  
  public boolean verificacionCantidadVehiculos(Vehiculo vehiculo,Celdas celdas) throws ExcepcionRangoVehiculos
  {
	  int cantVehiculosEnBasedeDatos;
	  if(vehiculoRepositorio.findByTipo(vehiculo.getTipo())==null)
	     cantVehiculosEnBasedeDatos = 0;
	  else
		  cantVehiculosEnBasedeDatos = vehiculoRepositorio.findByTipo(vehiculo.getTipo()).size();
	  
	  celdas = CeldasFabrica.creacionEstacionamiento(vehiculo.getTipo());
      
	  if((celdas.getCantidadCeldasDisponibles() < cantVehiculosEnBasedeDatos))
	  {
	   throw new ExcepcionRangoVehiculos("Numero de vehiculos superior al permitido");
	  }
  
	  return true;
  }
	
  public boolean verificacionPlaca(Vehiculo vehiculo) throws ExcepcionDiaInvalido
  {
	  if (((vehiculo.getPlaca()).charAt(0) == 'A') && !(verificacionFecha()))
	  {
		throw new ExcepcionDiaInvalido("Dia invalido para ingresar al estacionamiento");
	  }
      return true;
  }
  
  public boolean verificacionFecha() 
  {
	Calendar calendar = Calendar.getInstance();
	return ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)||(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY));
  }
 

}
