import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppComponent} from "./app.component";
import {Header} from "./header/header.component";
import {MapComponent} from "./map/map.component";
import {Footer} from "./footer/footer.component";
import {ParticipantListComponent} from "./participants/participant-list.component";
import {LoginPage} from "./login/login.component";
import {ReactiveFormsModule} from "@angular/forms";
import { HttpModule, JsonpModule } from '@angular/http';


@NgModule({
  imports:      [ BrowserModule, ReactiveFormsModule, HttpModule, JsonpModule ],
  declarations: [ AppComponent, Header, MapComponent,
    Footer, ParticipantListComponent, LoginPage ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
