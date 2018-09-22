import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AppComponent} from '../app.component';
import {AuthService} from '../core/auth.service';

@Component({
  selector: 'app-telenash',
  templateUrl: './telenash.component.html',
  styleUrls: ['./telenash.component.css']
})
export class TelenashComponent implements OnInit {

  constructor(private router: Router,
    private authService: AuthService,
    private appComponent: AppComponent) { }

  prikaziLogin() {
    this.appComponent.mojTelenash = true;

    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
    }
    else {
      this.appComponent.goToHomePage();
    }
  }

  ngOnInit() {
  }

}
