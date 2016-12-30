import {Component} from "@angular/core";


@Component({
  selector: 'modal-dialog',
  template: `
<div id="missionModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content" *ngIf="mission">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><img class="img-circle" style="height: 40px; width: 40px" src="{{avatarUrl}}{{id}}">{{mission.description}}</h4>
      </div>
      <div class="modal-body">
        <p>Stuff</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>`
})

export class Mission {

}
