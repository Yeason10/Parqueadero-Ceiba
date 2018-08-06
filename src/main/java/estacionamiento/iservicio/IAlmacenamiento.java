package estacionamiento.iservicio;

import estacionamiento.Vehiculo;
import estacionamiento.model.VehiculoEntidad;

public interface IAlmacenamiento 
{
  Vehiculo insertar(Vehiculo vehiculo);
  Vehiculo convertirEntidadDominio(VehiculoEntidad vehiculoEntidad);
}
