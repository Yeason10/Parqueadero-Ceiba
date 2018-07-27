package Estacionamiento.Estacionamiento.Iservicio;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;

public interface IVigilante 
{ 
	Vehiculo registroEntradaVehiculo(Vehiculo vehiculo)throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido;
	void registroSalidaVehiculo(Vehiculo vehiculo);
	void verificacionTipoVehiculo(Vehiculo vehiculo);
	boolean verificacionCantidadVehiculos(Vehiculo vehiculo)throws ExcepcionRangoVehiculos;
	boolean verificacionPlaca(Vehiculo vehiculo)throws ExcepcionDiaInvalido;
	boolean verificacionFecha();
}
