package estacionamiento.iservicio;

import estacionamiento.Vehiculo;
import estacionamiento.exception.ExcepcionDiaInvalido;
import estacionamiento.exception.ExcepcionRangoVehiculos;
import estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import estacionamiento.fabrica.Celdas;

public interface IVigilante 
{ 
	Vehiculo registroEntradaVehiculo(Vehiculo vehiculo,Celdas celdas)throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido;
	long registroSalidaVehiculo(String vehiculoPlaca)throws ExcepcionVehiculoNoEncontrado;
	boolean verificacionCantidadVehiculos(Vehiculo vehiculo,Celdas estacionamiento)throws ExcepcionRangoVehiculos;
	boolean verificacionPlaca(Vehiculo vehiculo)throws ExcepcionDiaInvalido;
	boolean verificacionFecha();
}
