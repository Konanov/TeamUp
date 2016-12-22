import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppComponent} from "./app.component";
import {Header} from "./header/header.component";
import {MapComponent} from "./map/map.component";
import {Footer} from "./footer/footer.component";
import {LoginPage} from "./login/login.component";
import {ReactiveFormsModule} from "@angular/forms";
import { HttpModule, JsonpModule } from '@angular/http';
import {AppRoutingModule} from "./app.routing.module";
import {ParticipantsModule} from "./participants/participant.module";
import {MainView} from "./main.view.component";


@NgModule({
  imports:      [ BrowserModule, ReactiveFormsModule, HttpModule, JsonpModule, AppRoutingModule, ParticipantsModule],
  declarations: [ AppComponent, Header, MapComponent,
    Footer, LoginPage, MainView ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
