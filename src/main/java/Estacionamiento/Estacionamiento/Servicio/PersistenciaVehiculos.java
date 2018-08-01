package Estacionamiento.Estacionamiento.Servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Iservicio.IAlmacenamiento;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorio;

@Service
public class Almacenamiento implements IAlmacenamiento
{
  @Autowired
  VehiculoRepositorio vehiculoRepositorio;
  
  public Vehiculo almacenamientoVehiculo(Vehiculo vehiculo)
  {
	VehiculoEntidad vehiculoEntidad = new VehiculoEntidad(vehiculo.getPlaca(),vehiculo.getEstado(),vehiculo.getCilindraje(),vehiculo.getTipo());
	vehiculoEntidad.setEstado("ACTIVO");
	vehiculoEntidad.setFechaIngreso(vehiculo.getFechaIngreso());
	return convertirEntidadDominio(vehiculoRepositorio.save(vehiculoEntidad));
  }

  public Vehiculo convertirEntidadDominio(VehiculoEntidad vehiculoEntidad)
  {
   return new Vehiculo(vehiculoEntidad.getPlaca(),vehiculoEntidad.getEstado(),vehiculoEntidad.getCilindraje(),vehiculoEntidad.getTipo(),vehiculoEntidad.getFechaIngreso());
  }

}
