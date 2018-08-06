package Estacionamiento.Estacionamiento.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;
import Estacionamiento.Estacionamiento.Servicio.Vigilante;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;
import Estacionamiento.Estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import Estacionamiento.Estacionamiento.exception.RecursoNoEncontradoExcepcion;
import Estacionamiento.Estacionamiento.fabrica.Celdas;
import Estacionamiento.Estacionamiento.fabrica.CeldasFabrica;

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
 