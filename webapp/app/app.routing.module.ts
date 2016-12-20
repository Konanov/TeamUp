import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {AppComponent} from "./app.component";

const teamUpRoutes: Routes = [
  { path: 'index', component: AppComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(teamUpRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
