import {Component} from '@angular/core';

import './rxjs-operators';

@Component({
  selector: 'main-view',
  template: `
    <app-header></app-header>
    <router-outlet name="main"></router-outlet>
            <my-map></my-map>
        <app-footer></app-footer>`
})

export class MainView {}
