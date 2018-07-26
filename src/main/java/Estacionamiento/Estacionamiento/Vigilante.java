package Estacionamiento.Estacionamiento;

import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import Estacionamiento.Estacionamiento.Persistencia.Almacenamiento;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorio;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;

@Service
public class Vigilante 
{
  @Autowired //Inyeccion de deoendencias.
  Almacenamiento almacenamiento;
  
  @Autowired
  VehiculoRepositorio vehiculoRepositorio;
   
  
  private int cantCarros;
  private int cantMotos;
  
  
  public Vigilante(Almacenamiento almacenamiento, VehiculoRepositorio vehiculoRepositorio)
  {
	this.almacenamiento = almacenamiento;
	this.vehiculoRepositorio = vehiculoRepositorio;
  }
 
  public Vehiculo registroEntradaVehiculo(Vehiculo vehiculo) throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	cantCarros = vehiculoRepositorio.findByTipo("carro").size();
	cantMotos = vehiculoRepositorio.findByTipo("moto").size();
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
