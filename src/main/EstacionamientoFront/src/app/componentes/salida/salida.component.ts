import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { VehiculoService } from '../../servicios/vehiculo.service';
import { Vehiculo } from '../../model/vehiculo-modelo';

@Component({
  selector: 'app-salida',
  templateUrl: './salida.component.html',
  styleUrls: ['./salida.component.css']
})
export class SalidaComponent implements OnInit {

  
  @Input() vehiculos: Vehiculo[];
  @Output() eventoSalida = new EventEmitter<boolean>();

  vehiculo: Vehiculo;
  mensaje = "";
  mostrarMensaje = false;
  salidaExitosa;
  vehiculoASalir = new Vehiculo();

  constructor(private vehiculoService:VehiculoService) { }

  ngOnInit() {
    
  }
  
  obtenerListaDeVehiculos(){
    this.vehiculoService.obtenerVehiculosGet().subscribe(res=>{
      this.vehiculos = res;
      console.log(res);
    });
  }

  salidaDeVehiculo(placa:string, estado:string, cilindraje:string, tipo:string, fechaIngreso:Date){
    this.vehiculoASalir.placa = placa;
    this.vehiculoASalir.fechaIngreso = fechaIngreso;
    this.vehiculoASalir.cilindraje = cilindraje;
    this.vehiculoASalir.tipo = tipo;
    console.log(this.vehiculoASalir.tipo);
    this.mostrarMensaje = true;
    console.log(estado);
    
    if(estado == "INACTIVO"){
      this.salidaExitosa = false;
      this.mensaje = "El vehiculo no se encuentra en el estacionamiento";
      this.vehiculo = null;
      setTimeout(()=>{
        this.mostrarMensaje = false;
      }, 3000);
    }else {
      this.salidaExitosa = true;
      this.vehiculoService.salidaVehiculoPut(placa).subscribe(res =>{
        this.eventoSalida.emit();
        this.mensaje = "Vehiculo retirado con exito! - El valor a pagar es " + res;
        setTimeout(()=>{
          
          this.mostrarMensaje = false;
        }, 3000);
        this.vehiculo = this.vehiculoASalir;
      });
    }
    
  }


}
