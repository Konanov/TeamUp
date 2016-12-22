import {ViewChild} from "@angular/core";
import {LoginPage} from "./login.component";

export class AuthenticationService {
  @ViewChild(LoginPage)
  public loginModal : LoginPage;

  constructor() {}

  public ngAfterViewInit(): void {
    //check if user is logged in
    if(!userIsLoggedIn) {
      LoginPage.open();
    }

  }
}
