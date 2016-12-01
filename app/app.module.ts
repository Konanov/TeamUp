import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppComponent} from "./app.component";
import {Header} from "./header.component";
import {MapComponent} from "./map.component";
import {Footer} from "./footer.component";
import {ParticipantsComponent} from "./participants.component";
import {ParticipantListComponent} from "./participant-list.component";


@NgModule({
  imports:      [ BrowserModule ],
  declarations: [ AppComponent, Header, MapComponent, Footer, ParticipantListComponent, ParticipantsComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
