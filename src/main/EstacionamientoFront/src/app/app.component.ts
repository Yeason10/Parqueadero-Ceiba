import { VehiculoService } from './servicios/vehiculo.service';
import { Component } from '@angular/core';
import { Vehiculo } from './model/vehiculo-modelo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  vehiculos: Vehiculo[];

  constructor(private vehiculoservice: VehiculoService){

  }

  ngOnInit(){
    this.mostrarLista();
  }

  mostrarLista(){
    this.vehiculoservice.obtenerVehiculosGet().subscribe(res =>{
      console.log(res);
      this.vehiculos = res;
    });
  }
}
