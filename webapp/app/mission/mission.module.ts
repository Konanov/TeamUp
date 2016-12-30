import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import {Mission} from "./mission.component";
import {MissionListComponent} from "./mission.list.component";
import {MissionRoutingModule} from "./mission.routing.module";
import {MissionsService} from "./mission.service";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MissionRoutingModule
  ],
  declarations: [
    MissionListComponent,
    Mission
  ],
  providers: [
    MissionsService
  ],
  exports: [MissionListComponent, Mission]
})
export class MissionModule {}
