import { Vehiculo } from './../../model/vehiculo-modelo';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-validacion',
  templateUrl: './validacion.component.html',
  styleUrls: ['./validacion.component.css']
})
export class ValidacionComponent implements OnInit {

  @Input() vehiculo: Vehiculo;

  constructor() { }

  ngOnInit() {
  }

}
