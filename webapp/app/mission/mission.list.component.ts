import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {Router, Params, ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {MissionsService} from "./mission.service";
import {Mission} from "./mission.component";

@Component({
  selector: 'missions-list',
  template: `
  <div *ngIf="showMissionModal">
  <button *ngFor="let mission of missions" class="btn btn-primary" type="button" data-toggle="modal" data-target="#missionModal" (click)="onSelectMission(mission)">
  <div class="row"><img class="img-circle" style="height: 25px; width: 25px" src="{{avatarUrl}}{{mission.id}}"></div>
  <div class="row"><div class="label">{{mission.description}}</div></div>
  </button>
</div>`,
  providers: [MissionsService]
})

export class MissionListComponent implements OnInit {

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: MissionsService
  ) {}

  onSelectMission(mission: Mission) {
    this.router.navigate(['/content', { outlets: { main: 'missionInfo/' + mission.id } }]);
  }

  private avatarUrl = "http://localhost:8080/participants/avatar/";

  missions: Observable<Mission[]>;

  ngOnInit() {
    this.service.getUsersMissions().subscribe(
      missions => this.missions = missions,
      error => this.errorMessage = <any>error)
  }
}
