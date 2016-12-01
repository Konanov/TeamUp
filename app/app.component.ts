import { Component } from '@angular/core';
import {MapComponent} from "./map.component";
import {Header} from "./header.component";
import {Footer} from "./footer.component";
import {Participant} from "./participant.component";
import {ParticipantListComponent} from "./participant-list.component";
import {ParticipantsComponent} from "./participants.component";
import {PARTICIPANTS} from "./mock-participants";

@Component({
  selector: 'main-app',
  template: `<app-header></app-header>
    <my-map></my-map><app-footer></app-footer>`, directives: [MapComponent, Header, Footer,
    Participant, ParticipantListComponent, ParticipantsComponent, PARTICIPANTS]
})
export class AppComponent  { name = 'Angular'; }
