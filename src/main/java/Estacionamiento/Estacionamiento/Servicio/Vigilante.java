package Estacionamiento.Estacionamiento.Servicio;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Iservicio.IVigilante;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;
import Estacionamiento.Estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import Estacionamiento.Estacionamiento.fabrica.Celdas;
import Estacionamiento.Estacionamiento.fabrica.CeldasFabrica;
import Estacionamiento.Estacionamiento.Servicio.PersistenciaVehiculos;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class Vigilante implements IVigilante
{
  @Autowired //Inyeccion de dependencias.
  PersistenciaVehiculos persistenciaVehiculos;
  
  @Autowired
  VehiculoRepositorioJPA vehiculoRepositorio;
  
  public Vigilante(){}
  
  public Vigilante(PersistenciaVehiculos persistenciaVehiculos,VehiculoRepositorioJPA vehiculoRepositorio)
  {
	this.persistenciaVehiculos = persistenciaVehiculos;
	this.vehiculoRepositorio = vehiculoRepositorio; 
  }
    
  public Vehiculo registroEntradaVehiculo(Vehiculo vehiculo,Celdas celdas) throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	verificacionCantidadVehiculos(vehiculo,celdas);
    verificacionPlaca(vehiculo); 
    return persistenciaVehiculos.insertar(vehiculo);           
  }
  
  public Vehiculo registroSalidaVehiculo(String vehiculoPlaca) throws ExcepcionVehiculoNoEncontrado
  {
     Vehiculo vehiculoASalir = persistenciaVehiculos.buscarVehiculoASalir(vehiculoPlaca);
     return vehiculoASalir;
  }
 

  public boolean verificacionCantidadVehiculos(Vehiculo vehiculo,Celdas celdas) throws ExcepcionRangoVehiculos
  {
	 
	  CeldasFabrica celdasFabrica = new CeldasFabrica();
	  celdas = celdasFabrica.creacionEstacionamiento(vehiculo.getTipo());  
      
	  if((celdas.getCantidadCeldasDisponibles() < vehiculoRepositorio.findByTipo(vehiculo.getTipo()).size()))
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
