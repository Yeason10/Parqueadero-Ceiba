package Estacionamiento.Estacionamiento.Iservicio;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Fabrica.Celdas;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;

public interface IVigilante 
{ 
	Vehiculo registroEntradaVehiculo(Vehiculo vehiculo,Celdas celdas)throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido;
	void registroSalidaVehiculo(Vehiculo vehiculo);
	boolean verificacionCantidadVehiculos(Vehiculo vehiculo,Celdas estacionamiento)throws ExcepcionRangoVehiculos;
	boolean verificacionPlaca(Vehiculo vehiculo)throws ExcepcionDiaInvalido;
	boolean verificacionFecha();
}
