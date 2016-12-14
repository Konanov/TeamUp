import {Component, OnInit} from '@angular/core';
import {ParticipantsService} from "./participants.service";
import {Participant} from "./participant.component";

@Component({
  selector: 'participants-list',
  template: `
  <div *ngFor="let participant of participants">
    <span>{{participant.name}}{{participant.surname}}</span>
  </div>`,

  providers: [ ParticipantsService ]
})

export class ParticipantListComponent implements OnInit{

  participants: Participant[];

  constructor (private participantsService: ParticipantsService) {}

  ngOnInit() {
    this.participantsService.getParticipants().subscribe(
      participants => this.participants = participants,
      error => this.errorMessage = <any>error);
  }
}
