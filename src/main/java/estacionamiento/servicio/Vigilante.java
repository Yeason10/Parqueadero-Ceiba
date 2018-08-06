package estacionamiento.servicio;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estacionamiento.Vehiculo;
import estacionamiento.exception.ExcepcionDiaInvalido;
import estacionamiento.exception.ExcepcionRangoVehiculos;
import estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import estacionamiento.fabrica.Celdas;
import estacionamiento.fabrica.CeldasFabrica;
import estacionamiento.iservicio.IVigilante;
import estacionamiento.repositorio.VehiculoRepositorioJPA;

@Service
public class Vigilante implements IVigilante
{
  @Autowired
  PersistenciaVehiculos persistenciaVehiculos;
  
  @Autowired
  VehiculoRepositorioJPA vehiculoRepositorio;
  
  @Autowired
  Factura factura; 
    
  public Vigilante(){}   
  
  
  public Vigilante(PersistenciaVehiculos persistenciaVehiculos,VehiculoRepositorioJPA vehiculoRepositorio, Factura factura)  
  {
	this.persistenciaVehiculos = persistenciaVehiculos;
	this.vehiculoRepositorio = vehiculoRepositorio;  
	this.factura = factura; 
  }
  
  
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
  
  public long registroSalidaVehiculo(String vehiculoPlaca) throws ExcepcionVehiculoNoEncontrado
  {  
	 
	 Vehiculo vehiculoASalir = persistenciaVehiculos.buscarVehiculoASalir(vehiculoPlaca);
     
     
     return factura.cobroSalidaDeVehiculo(vehiculoASalir);
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
