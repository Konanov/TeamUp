import 'rxjs/add/operator/switchMap';
import {ActivatedRoute} from "@angular/router";
import {ParticipantsService} from "./participants.service";
import {Component} from '@angular/core';

@Component({
  selector: 'participant-details',
  template: `
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content" *ngIf="participant">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><img class="img-circle" style="height: 40px; width: 40px" src="{{avatarUrl}}{{id}}"> {{participant.name}} {{participant.surname}}</h4>
      </div>
      <div class="modal-body">
        <p>{{participant.currentTask?.description}}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>`,

  providers: [ParticipantsService],
})

export class Participant {

  private id: string;

  private avatarUrl = "http://localhost:8080/participants/avatar/";

  private sub:any;
  private participant: Participant;

  constructor(
    private route: ActivatedRoute,
    private service: ParticipantsService,
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {

      let id = params['id'];

      this.id = id;

      this.service.getParticipantDetails(id).subscribe(participant => this.participant = participant);
    });
  }
}
