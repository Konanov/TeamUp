import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MissionListComponent} from "./mission.list.component";

const missionsRoutes: Routes = [
  { path: 'missionsList', component: MissionListComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(missionsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class MissionRoutingModule { }
