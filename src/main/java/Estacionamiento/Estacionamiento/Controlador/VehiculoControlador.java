package Estacionamiento.Estacionamiento.Controlador;

import Estacionamiento.Estacionamiento.exception.RecursoNoEncontradoExcepcion;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehiculoControlador 
{
  @Autowired
  VehiculoRepositorio vehiculoRepositorio;
  
  //get all vehicles
  @GetMapping("/vehiculos")
  public List<VehiculoEntidad> getTodosLosVehiculos()
  {
	return vehiculoRepositorio.findAll();
  }
  
  //create a new vehicle
  @PostMapping("/vehiculos")
  public VehiculoEntidad crearVehiculoEntidad(@Valid @RequestBody VehiculoEntidad vehiculoEntidad)
  {
	  return vehiculoRepositorio.save(vehiculoEntidad);
  }
  
  //create a new vehicle
  @PostMapping("/vehi")
  public String crearVehiculo(@Valid @RequestBody String vehi)
  {
	  return vehi;
  }
  
  //Get a single vehicle
  @GetMapping("/notes/{placa}")
  public VehiculoEntidad getVehiculoPorPlaca(@PathVariable(value = "placa") String vehiculoPlaca)
  {
	  return vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new RecursoNoEncontradoExcepcion("VehiculoEntidad","placa",vehiculoPlaca));
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
 