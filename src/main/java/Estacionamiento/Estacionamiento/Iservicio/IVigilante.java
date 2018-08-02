package Estacionamiento.Estacionamiento.Iservicio;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;
import Estacionamiento.Estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import Estacionamiento.Estacionamiento.fabrica.Celdas;

public interface IVigilante 
{ 
	Vehiculo registroEntradaVehiculo(Vehiculo vehiculo,Celdas celdas)throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido;
	Vehiculo registroSalidaVehiculo(String vehiculoPlaca)throws ExcepcionVehiculoNoEncontrado;
	boolean verificacionCantidadVehiculos(Vehiculo vehiculo,Celdas estacionamiento)throws ExcepcionRangoVehiculos;
	boolean verificacionPlaca(Vehiculo vehiculo)throws ExcepcionDiaInvalido;
	boolean verificacionFecha();
}
