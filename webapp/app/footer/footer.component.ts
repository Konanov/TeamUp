import {Component} from "@angular/core";

@Component({
  selector: 'app-footer',
  template: `<div style="height: 10%" class="footer">
      <div class="container">
      <div class="col-sm-4"></div>
      <div class="col-sm-4"><participants-list></participants-list></div>
      <div class="col-sm-4"></div>
      </div>
    </div>`
})
export class Footer {}
