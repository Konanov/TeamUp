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

  login(event, email, password) {
    event.preventDefault();
    let body = JSON.stringify({ email, password });
    this.http.post('http://localhost:8080/login', body, { headers: contentHeaders })
      .subscribe(
        response => {
          if (response.json().email != null && response.json().password != null) {
            localStorage.setItem('email', response.json().email);
            localStorage.setItem('password', response.json().password);
            this.router.navigate(['index']);
          } else {
            alert("Такого пользователя не существует");
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
}
