import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {ParticipantsService} from "./participants.service";
import {Participant} from "./participant.component";
import {Router, Params, ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'participants-list',
  template: `
  <div>
  <button *ngFor="let participant of participants" class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal" (click)="onSelect(participant)">
  <div class="row"><img class="img-circle" style="height: 25px; width: 25px" src="{{avatarUrl}}{{participant.id}}"></div>
  <div class="row"><div class="label">{{participant.name}} {{participant.surname}}</div></div>
  </button>
</div>`,

  providers: [ParticipantsService]
})

export class ParticipantListComponent implements OnInit {

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: ParticipantsService
  ) {}

  onSelect(participant: Participant) {
    this.router.navigate(['/participantInfo', participant.id]);
  }

  private avatarUrl = "http://localhost:8080/participants/avatar/";

  participants: Observable<Participant[]>;

  ngOnInit() {
    this.service.getParticipants().subscribe(
    participants => this.participants = participants,
    error => this.errorMessage = <any>error);
  }
}
