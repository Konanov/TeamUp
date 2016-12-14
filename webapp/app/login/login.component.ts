import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'login-page',
  template: `<form [formGroup]="loginForm" (ngSubmit)="doLogin($event)">
<input formControlName="email" type="email" placeholder="Your email">
<input formContControl="password" type="password" placeholder="Your password">
<button type="submit">Log in</button>
</form>`
})
export class LoginPage {
  public loginForm = this.fb.group({
    email: ["", Validators.required],
    password: ["", Validators.required]
  });

  constructor(public fb: FormBuilder) {
  }

  doLogin(event) {
    console.log(event);
    console.log(this.loginForm.value);
  }
}
