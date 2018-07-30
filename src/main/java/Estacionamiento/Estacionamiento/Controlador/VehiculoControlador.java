package Estacionamiento.Estacionamiento.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Fabrica.Celdas;
import Estacionamiento.Estacionamiento.Fabrica.CeldasFabrica;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorio;
import Estacionamiento.Estacionamiento.Servicio.Vigilante;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;
import Estacionamiento.Estacionamiento.exception.RecursoNoEncontradoExcepcion;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehiculoControlador 
{
  @Autowired //Inyeccion(Interface)
  VehiculoRepositorio vehiculoRepositorio;
  
  @Autowired
  Vigilante vigilante;
  
  //get all vehicles
  @GetMapping("/vehiculos")
  public List<VehiculoEntidad> getTodosLosVehiculos()
  {
	return vehiculoRepositorio.findAll();
  }
  
  //create a new vehicle
  @PostMapping("/vehiculos")
  public Vehiculo crearVehiculoEntidad(@Valid @RequestBody Vehiculo vehiculo) throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	  Celdas celdas = CeldasFabrica.creacionEstacionamiento(vehiculo.getTipo());
	  return vigilante.registroEntradaVehiculo(vehiculo,celdas);  
  }
  
  //Get a single vehicle
  @GetMapping("/vehiculos/{placa}")
  public VehiculoEntidad getVehiculoPorPlaca(@PathVariable(value = "placa") String vehiculoPlaca)
  {
	  return vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new RecursoNoEncontradoExcepcion("VehiculoEntidad","id",vehiculoPlaca));
  }
  
  //Update vehicle
  @PutMapping("/vehiculos/{placa}")
  public VehiculoEntidad actualizarVehiculo(@PathVariable(value = "placa") String vehiculoPlaca, @Valid @RequestBody VehiculoEntidad vehiculoDetalles)
  {
	VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new RecursoNoEncontradoExcepcion("VehiculoEntidad","placa",vehiculoPlaca));  
	vehiculoEntidad.setPlaca(vehiculoDetalles.getPlaca());
	VehiculoEntidad actualizarVehiculoEntidad = vehiculoRepositorio.save(vehiculoEntidad);
	return actualizarVehiculoEntidad;
  }
  
  //Delete a vehicle
  @DeleteMapping("/vehiculos/{placa}")
  public ResponseEntity<?> eliminarVehiculo(@PathVariable(value = "placa")String vehiculoPlaca)
  {
	  VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new RecursoNoEncontradoExcepcion("VehiculoEntidad","placa",vehiculoPlaca));
	  vehiculoRepositorio.delete(vehiculoEntidad);
	  return ResponseEntity.ok().build();
  }
  
  

}
 