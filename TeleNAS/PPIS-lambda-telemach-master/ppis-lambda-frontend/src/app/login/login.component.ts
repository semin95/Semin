import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../core/auth.service';
import {TokenStorage} from '../core/token.storage';
import {UserService} from '../shared/user/user.service';
import {AppComponent} from '../app.component';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router,
              private authService: AuthService,
              private tokenService: TokenStorage,
              private alert: MatSnackBar,
              private appComponent: AppComponent,
              private userService: UserService) {
  }

  username: string;
  password: string;

  login(): void {
    this.authService.attemptAuth(this.username, this.password)
      .subscribe(
        data => {
          TokenStorage.saveToken(data.token);

          this.userService.getUserByUsername(this.authService.getUserName())
            .subscribe(response => {
              console.log(response);
              TokenStorage.saveCurrentUser(response.firstName + ' ' + response.lastName);
            });
          this.appComponent.goToHomePage();
        },
        error => {
          console.error('Login failed...' + error);
          this.alert.open('Login failed. Wrong username or password!', null, {duration: 3000});
        },
        () => {
          console.log('User: ' + this.username + ' successfuly logged in...');
          this.alert.open('Login successful', null, {duration: 3000});
        }
      );
  }
}
