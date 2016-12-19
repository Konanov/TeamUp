import {Component, OnInit} from '@angular/core';
import {ParticipantsService} from "./participants.service";
import {Participant} from "./participant.component";

@Component({
  selector: 'participants-list',
  template: `
  <div>
  <button *ngFor="let participant of participants" class="btn btn-primary" type="button">
  <div class="row"><img class="img-circle" style="height: 25px; width: 25px" src="{{avatarUrl}}{{participant.id}}"></div>
  <div class="row"><div class="label">{{participant.name}} {{participant.surname}}</div></div>
  </button>
  </div>`,

  providers: [ParticipantsService],
})

export class ParticipantListComponent implements OnInit {

  private avatarUrl = "http://ocalhost:8080/participants/avatar/";

  participants: Participant[];

  constructor(private participantsService: ParticipantsService) {
  }

  ngOnInit() {
    this.participantsService.getParticipants().subscribe(
      participants => this.participants = participants,
      error => this.errorMessage = <any>error);
  }
}
