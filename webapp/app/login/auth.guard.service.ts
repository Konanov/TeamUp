import {Injectable} from '@angular/core';
import {Router, CanActivate} from '@angular/router';
import {contentHeaders} from "./content.headers";
import {Http} from "@angular/http";
import {Observable} from "rxjs";


@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, public http: Http) {
  }

  showLogin: boolean = true;

  showRegister: boolean = false;

  canActivate() {
    // Check to see if a user has a valid JWT
    if (localStorage.getItem("email") != null && localStorage.getItem("password") != null) {
      var email = localStorage.getItem("email");
      var password = localStorage.getItem("password");
      let body = JSON.stringify({email, password});
      return this.http.post('http://localhost:8080/login', body, {headers: contentHeaders})
        .map((response) => {
          if (response.json().email != null && response.json().password != null) {
            this.router.navigate(['/index']);
            return true;
          } else {
            this.showLogin = false;
            this.showRegister = true;
            this.router.navigate(['/login']);
            return false;
          }
        })
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
