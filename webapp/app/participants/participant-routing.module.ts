import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Participant} from "./participant.component";

const participantsRoutes: Routes = [
  { path: 'participantInfo/:id', component: Participant }
];

@NgModule({
  imports: [
    RouterModule.forChild(participantsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ParticipantRoutingModule { }
