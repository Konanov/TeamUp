import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AUTH_PROVIDERS } from 'angular2-jwt';

import {AppComponent} from "./app.component";
import {Header} from "./header/header.component";
import {MapComponent} from "./map/map.component";
import {Footer} from "./footer/footer.component";
import {LoginPage} from "./login/login.component";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import { HttpModule, JsonpModule } from '@angular/http';
import {AppRoutingModule} from "./app.routing.module";
import {ParticipantsModule} from "./participants/participant.module";
import {MainView} from "./main.view.component";
import {AuthGuard} from "./login/auth.guard.service";
import {MissionModule} from "./mission/mission.module";


@NgModule({
  imports:      [ BrowserModule, ReactiveFormsModule, FormsModule, HttpModule, JsonpModule, AppRoutingModule, ParticipantsModule, MissionModule],
  declarations: [ AppComponent, Header, MapComponent,
    Footer, LoginPage, MainView ],
  bootstrap:    [ AppComponent ],
  providers: [
    AuthGuard, ...AUTH_PROVIDERS
  ]
})
export class AppModule { }
