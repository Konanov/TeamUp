import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import {ParticipantListComponent} from "./participant-list.component";
import {Participant} from "./participant.component";
import {ParticipantsService} from "./participants.service";
import {ParticipantRoutingModule} from "./participant-routing.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ParticipantRoutingModule
  ],
  declarations: [
    ParticipantListComponent,
    Participant
  ],
  providers: [
    ParticipantsService
  ],
  exports: [ParticipantListComponent, Participant]
})
export class ParticipantsModule {}
