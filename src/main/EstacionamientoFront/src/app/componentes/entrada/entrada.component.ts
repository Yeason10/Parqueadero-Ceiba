import { VehiculoService } from './../../servicios/vehiculo.service';
import { Vehiculo } from './../../model/vehiculo-modelo';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.css']
})
export class EntradaComponent implements OnInit {

  vehiculo = new Vehiculo();
  mostrarMensaje = false;
  registroExitoso = false;
  mensaje = "";
  @Output() evento = new EventEmitter<boolean>();

  constructor(private vehiculoService: VehiculoService) { }

  ngOnInit() {
  }

  realizarRegistro(){
    this.vehiculoService.registrarVehiculosPost(this.vehiculo).subscribe(res => {
      console.log(res);
      this.mostrarMensaje = true;
      if(res.fechaIngreso != null){
        this.evento.emit();
        this.registroExitoso = true;
        this.mensaje = "Registro exitoso!";
        setTimeout(()=>{
          this.mostrarMensaje = false;
        }, 3000);
        
      }else {
        this.registroExitoso = false;
        this.mensaje = "Registro fallido";
        setTimeout(()=>{
          this.mostrarMensaje = false;
        }, 3000);
      }
    });
  }
}
