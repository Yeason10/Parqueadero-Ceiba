import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http"
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { EntradaComponent } from './componentes/entrada/entrada.component';
import { SalidaComponent } from './componentes/salida/salida.component';
import { ValidacionComponent } from './componentes/validacion/validacion.component';

@NgModule({
  declarations: [
    AppComponent,
    EntradaComponent,
    SalidaComponent,
    ValidacionComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
