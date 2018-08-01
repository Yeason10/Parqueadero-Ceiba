package Estacionamiento.Estacionamiento.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Iservicio.IAlmacenamiento;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;

@Service
public class PersistenciaVehiculos implements IAlmacenamiento 
{
	@Autowired
	VehiculoRepositorioJPA vehiculoRepositorio;

	public Vehiculo insertar(Vehiculo vehiculo) 
	{
		VehiculoEntidad vehiculoEntidad = new VehiculoEntidad(vehiculo.getPlaca(), vehiculo.getEstado(),
		vehiculo.getCilindraje(), vehiculo.getTipo());
		vehiculoEntidad.setEstado("ACTIVO");
		vehiculoEntidad.setFechaIngreso(vehiculo.getFechaIngreso());
		return convertirEntidadDominio(vehiculoRepositorio.save(vehiculoEntidad));
	}

	public Vehiculo convertirEntidadDominio(VehiculoEntidad vehiculoEntidad) 
	{
		return new Vehiculo(vehiculoEntidad.getPlaca(), vehiculoEntidad.getEstado(), vehiculoEntidad.getCilindraje(),
				vehiculoEntidad.getTipo(), vehiculoEntidad.getFechaIngreso());
	}

	public boolean buscarVehiculoASalir(Vehiculo vehiculo) throws ExcepcionDiaInvalido
	{
      VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.getOne(vehiculo.getPlaca());
	  if(vehiculoEntidad == null)
	  {
	    throw new ExcepcionDiaInvalido("Dia invalido para ingresar al estacionamiento");
	  }
	  return true;
	}

}
