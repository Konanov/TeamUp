import {Component} from '@angular/core';

import './rxjs-operators';

@Component({
  selector: 'main-app',
  template: `<router-outlet></router-outlet>`
})

export class AppComponent {
  name = 'Angular';
}
