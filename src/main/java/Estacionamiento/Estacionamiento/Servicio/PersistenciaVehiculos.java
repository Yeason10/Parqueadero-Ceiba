package Estacionamiento.Estacionamiento.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Iservicio.IAlmacenamiento;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionVehiculoNoEncontrado;

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

	public 	Vehiculo buscarVehiculoASalir(String vehiculoPlaca) throws ExcepcionVehiculoNoEncontrado
	{
      VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new ExcepcionVehiculoNoEncontrado("Vehiculo no encontrado"));
      vehiculoEntidad.setEstado("INACTIVO");
      VehiculoEntidad actualizarVehiculoEntidad = vehiculoRepositorio.save(vehiculoEntidad);
      return convertirEntidadDominio(actualizarVehiculoEntidad);
	}

}
