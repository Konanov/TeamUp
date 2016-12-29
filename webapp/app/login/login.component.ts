import {Component} from '@angular/core';
import {Http} from "@angular/http";
import {Router} from "@angular/router";
import {contentHeaders} from "./content.headers";

@Component({
  selector: 'login-page',
  templateUrl: 'login.html'
})
export class LoginPage {

  constructor(public router: Router, public http: Http) {
  }

  showLogin: boolean = true;

  showRegister: boolean = false;

  userIsRegistered: boolean = false;

  login(event, email, password) {
    event.preventDefault();
    let body = JSON.stringify({ email, password });
    this.http.post('http://localhost:8080/login', body, { headers: contentHeaders })
      .subscribe(
        response => {
          if (response.json().email != null && response.json().password != null) {
            localStorage.setItem('email', response.json().email);
            localStorage.setItem('password', response.json().password);
            if (response.json().currentMissionId != null) {
              localStorage.setItem('currentMissionId', response.json().currentMissionId);
            }
            this.router.navigate(['index']);
          } else {
            this.showLogin = false;
            this.showRegister = true;
          }
        },
        error => {
          alert(error.text());
          console.log(error.text());
        }
      );
  }

  register(event, name, surname, email, password) {
    event.preventDefault();
    let body = JSON.stringify({ name, surname, email, password });
    this.http.post('http://localhost:8080/register', body, { headers: contentHeaders })
      .subscribe(
        response => {
          if (response.json().email != null && response.json().password != null) {
            localStorage.setItem('email', response.json().email);
            localStorage.setItem('password', response.json().password);
            this.router.navigate(['index']);
          } else {
            this.userIsRegistered = true;
          }
        },
        error => {
          alert(error.text());
          console.log(error.text());
        }
      );
  }

  signup(event) {
    event.preventDefault();
    this.router.navigate(['signup']);
  }

  showRegistration() {
    this.showRegister = true;
    this.showLogin = false;
  }

  showLoginPage() {
    this.showLogin = true;
    this.showRegister = false;
  }
}
