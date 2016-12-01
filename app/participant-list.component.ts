import { Component } from '@angular/core';
import { PARTICIPANTS } from './mock-participants';
@Component({
  selector: 'participants-list',
  template: `
  <div *ngFor="let participant of participants">
    <img [src]="participant.avatar" />
    <span>{{participant.name}}</span>
  </div>
  `
})
export class ParticipantListComponent {
  participants = PARTICIPANTS;
}
