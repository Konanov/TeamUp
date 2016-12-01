import {Component} from "@angular/core";
import {ParticipantsComponent} from "./participants.component";

@Component({
  selector: 'app-footer',
  template: `<div class="footer">
      <div class="container">
      <my-participants></my-participants>
      </div>
    </div>`, directives: [ ParticipantsComponent ]
})
export class Footer {}
