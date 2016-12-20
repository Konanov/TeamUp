import { Component } from '@angular/core';

import './rxjs-operators';

@Component({
  selector: 'main-app',
  template: `
<app-header></app-header>
<login-page></login-page>
<router-outlet></router-outlet>
    <my-map></my-map>
    <app-footer></app-footer>`
})
export class AppComponent  { name = 'Angular'; }
