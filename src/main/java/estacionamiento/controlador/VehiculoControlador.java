package estacionamiento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import estacionamiento.Vehiculo;
import estacionamiento.exception.ExcepcionDiaInvalido;
import estacionamiento.exception.ExcepcionRangoVehiculos;
import estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import estacionamiento.exception.RecursoNoEncontradoExcepcion;
import estacionamiento.fabrica.Celdas;
import estacionamiento.fabrica.CeldasFabrica;
import estacionamiento.model.VehiculoEntidad;
import estacionamiento.repositorio.VehiculoRepositorioJPA;
import estacionamiento.servicio.Vigilante;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders="*", maxAge = 3600)
public class VehiculoControlador  
{ 
  @Autowired
  VehiculoRepositorioJPA vehiculoRepositorio;
  
  @Autowired 
  Vigilante vigilante;
  
  
  @GetMapping("/vehiculos")
  public List<VehiculoEntidad> getTodosLosVehiculos()
  { 
	return vehiculoRepositorio.findAll(); 
  }
  
 
  @PostMapping("/vehiculos")
  public Vehiculo crearVehiculoEntidad(@Valid @RequestBody Vehiculo vehiculo) throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	  CeldasFabrica celdasFabrica =  new CeldasFabrica();
	  Celdas celdas = celdasFabrica.creacionEstacionamiento(vehiculo.getTipo()); 
	  return vigilante.registroEntradaVehiculo(vehiculo,celdas);  
  }
  
  
  @GetMapping("/vehiculos/{placa}")
  public VehiculoEntidad getVehiculoPorPlaca(@PathVariable(value = "placa") String vehiculoPlaca)
  {
	  return vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new RecursoNoEncontradoExcepcion("VehiculoEntidad","id",vehiculoPlaca));
  }
  
  
  @PutMapping("/vehiculos/{placa}")
  public long actualizarVehiculo(@PathVariable(value = "placa") String vehiculoPlaca) throws ExcepcionVehiculoNoEncontrado
  {
	 return vigilante.registroSalidaVehiculo(vehiculoPlaca);
	  
  }  
	  
  
  @DeleteMapping("/vehiculos/{placa}")
  public ResponseEntity<?> eliminarVehiculo(@PathVariable(value = "placa")String vehiculoPlaca)
  {
	  VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findById(vehiculoPlaca).orElseThrow(() -> new RecursoNoEncontradoExcepcion("VehiculoEntidad","placa",vehiculoPlaca));
	  vehiculoRepositorio.delete(vehiculoEntidad);
	  return ResponseEntity.ok().build();
  }
  
  

}
 