import 'rxjs/add/operator/switchMap';
import {ActivatedRoute} from "@angular/router";
import {ParticipantsService} from "./participants.service";
import {Component} from '@angular/core';

@Component({
  selector: 'participant-details',
  template: `
  <div class="card card-block" *ngIf="participant">
  <h4 class="card-title">{{participant.name}} {{participant.surname}}</h4>
  <p class="card-text">{{participant.currentTask?.description}}</p>
  <a href="#" class="card-link">Card link</a>
  <a href="#" class="card-link">Another link</a>
</div>`,

  providers: [ParticipantsService],
})

export class Participant {

  private sub:any;
  private participant: Participant;

  constructor(
    private route: ActivatedRoute,
    private service: ParticipantsService,
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {

      let id = params['id'];

      this.service.getParticipantDetails(id).subscribe(participant => this.participant = participant);
    });
  }
}
