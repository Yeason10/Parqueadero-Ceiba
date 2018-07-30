package Estacionamiento.Estacionamiento.Iservicio;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;

public interface IAlmacenamiento 
{
  Vehiculo almacenamientoVehiculo(Vehiculo vehiculo);
  Vehiculo convertirEntidadDominio(VehiculoEntidad vehiculoEntidad);
}
