import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import {contentHeaders} from "./content.headers";
import {Http} from "@angular/http";


@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, public http: Http) {
  }

  canActivate() {
    // Check to see if a user has a valid JWT
    if (localStorage.getItem("email") != null && localStorage.getItem("password") != null) {
      var email = localStorage.getItem("email");
      var password = localStorage.getItem("password");
      let body = JSON.stringify({ email, password });
      this.http.post('http://localhost:8080/login', body, {headers: contentHeaders})
        .subscribe(
          response => {
            if (response.json().email != null && response.json().password != null) {
              return true;
            } else {
              alert("Такого пользователя не существует!");
            }
          });
      // If they do, return true and allow the user to load the home component
    } else {
      alert("Такого пользователя не существует!");
    }
    // If not, they redirect them to the login page
    this.router.navigate(['/login']);
    return false;
  }
}
