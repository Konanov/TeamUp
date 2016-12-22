import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'login-page',
  templateUrl: 'login.html'
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
