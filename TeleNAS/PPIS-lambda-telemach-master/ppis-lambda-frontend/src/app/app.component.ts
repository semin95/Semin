import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from './core/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'PPIS Lambda';
  isLoggedIn: any;
  mojTelenash: boolean = false;

  constructor(private router: Router, public authService: AuthService
  ) {
  }

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  prikaziPocetnu() {
    this.router.navigate(['/pocetna']);
  }

  prikaziUsluge() {
    this.router.navigate(['/mojeusluge']);
  }

  prikaziZahtjeve() {
    this.router.navigate(['/mojizahtjevi']);
  }

  prikaziIncident() {
    this.router.navigate(['/incident']);
  }

  prikaziIncidente() {
    this.router.navigate(['/prikaziIncidente']);
  }

  prikaziStatistikuIncidenata() {
    this.router.navigate(['/statistika/incidenti']);
  }

  prikaziStatistikuZahtjeva() {
    this.router.navigate(['/statistika/zahtjevi']);
  }

  prikaziUslugeRequestManager() {
    this.router.navigate(['/requestManagerUsluge']);
  }

  prikaziZahtjeveRequestManager() {
    this.router.navigate(['/requestManagerZahtjevi']);
  }

  prikaziIncidenteMenadzer(){
    this.router.navigate(['/incidentManagerIncidenti']);
  }

  prikaziZahtjeveOperater(){
    this.router.navigate(['/zahtjeviOperater']);
  }

  prikaziIncidenteOperater(){
    this.router.navigate(['/incidentiOperater']);
  }

  prikaziPocetnuOperater(){
    this.router.navigate(['/pocetnaOperater']);
  }

  prikaziTelenash() {
    this.router.navigate(['/telenash']);
  }

  odjaviSe() {
    AuthService.logOut();
    this.prikaziTelenash();
  }

  getCurrentUserInfo() {
    return AuthService.getCurrentUser();
  }

  goToHomePage(): void {

    this.mojTelenash = true;

    if (this.authService.isOperater()) {
      this.prikaziPocetnuOperater();
    }

    if (this.authService.isIncidentManager()) {
      this.prikaziIncidente();
    }

    if (this.authService.isRequestManager()) {
      this.prikaziUslugeRequestManager();
    }

    if (this.authService.isUser()) {
      this.prikaziPocetnu();
    }
  }
}
