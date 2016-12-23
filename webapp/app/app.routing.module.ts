import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {AppComponent} from "./app.component";
import {LoginPage} from "./login/login.component";
import {MainView} from "./main.view.component";
import {Participant} from "./participants/participant.component";
import {AuthGuard} from "./login/auth.guard.service";

const teamUpRoutes: Routes = [
  { path: '', component: AppComponent },
  { path: 'login', component: LoginPage},
  { path: 'index', component: MainView, canActivate: [AuthGuard] },

  {
    path: 'content',
    component: MainView,
    children: [
      {
        path: 'participantInfo/:id',
        component: Participant,
        outlet: 'main'
      }
    ]
  }

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
