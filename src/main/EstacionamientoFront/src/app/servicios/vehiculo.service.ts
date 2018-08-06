import { Vehiculo } from './../model/vehiculo-modelo';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

  

  constructor(private http: HttpClient) { }

  obtenerVehiculosGet(){
    return this.http.get<Vehiculo[]>("http://localhost:1334/api/vehiculos");
  }

  registrarVehiculosPost(vehiculo:Vehiculo){
    return this.http.post<Vehiculo>("http://localhost:1334/api/vehiculos", vehiculo);
  }

  salidaVehiculoPut(placa:string){
    return this.http.put<Vehiculo>("http://localhost:1334/api/vehiculos/"+placa, placa);
  }
}
