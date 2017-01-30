import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {Router, Params, ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {MissionsService} from "./mission.service";
import {Mission} from "./mission.component";
import {Participant} from "../participants/participant.component";

@Component({
  selector: 'missions-list',
  template: `
  <div>
  <div class="middle panel panel-warning">
  
  <div class="panel-heading">
  <h3 class="panel-title">Миссии</h3>
  </div>
  <div class="panel-body">
  
  
  <div id="missionListPresent" *ngIf="missions?.length > 0">
  <div *ngFor="let mission of missions">
  <div class="row">
  <div class="col-sm-1"></div>
  <div class="col-sm-10">
  <a class="missionsHeader">{{mission.description}}</a><hr>
  <div *ngFor="let participant of mission.participants">
  <a>{{participant.name}} {{participant.surname}}</a>
  </div>
  </div>
  <div class="col-sm-1"></div>
  </div>
  
  </div>
  </div>
  
  <div id="missionListEmpty" *ngIf="!missions || missions.length == 0">
  <button *ngFor="let mission of missions" class="btn btn-primary" type="button" data-toggle="modal" data-target="#missionModal" (click)="onSelect(mission)">
  <div class="row"><div class="label">{{mission.description}}</div></div>
  </button>
  </div>
  
  </div>
  
  <div class="panel-footer"><button class="btn btn-success" type="button">Добавить</button></div>
  
  </div>`,
  providers: [MissionsService]
})

export class MissionListComponent implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute,
              private service: MissionsService) {
  }

  onSelectMission(mission: Mission) {
    this.router.navigate(['/content', {outlets: {main: 'missionInfo/' + mission.id}}]);
  }

  private avatarUrl = "http://localhost:8080/participants/avatar/";

  missions: Observable<Mission[]>;

  ngOnInit() {
    this.service.getUsersMissions().subscribe(
      missions => this.missions = missions,
      error => this.errorMessage = <any>error)
  }
}
