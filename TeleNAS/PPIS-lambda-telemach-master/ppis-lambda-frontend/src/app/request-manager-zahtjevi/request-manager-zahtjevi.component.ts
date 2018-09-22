import {Component, OnInit} from '@angular/core';
import {RequestService} from '../shared/request/request.service';
import {Request} from '../../models/Request';
import {Router} from '@angular/router';
import {FeedbackRequestService} from '../shared/feedbackRequest/feedback-request.service';
import {FeedbackRequest} from '../shared/feedbackRequest/feedbackRequest';
import {User} from '../../models/User';
import {UserService} from '../shared/user/user.service';
import {AuthService} from '../core/auth.service';
import {Status} from '../../models/Status';
import {Prioritet} from '../../models/Prioritet';
import {AppComponent} from '../app.component';
import {MatSnackBar} from '@angular/material';
import * as jsPDF from 'jspdf';


@Component({
  selector: 'app-request-manager-zahtjevi',
  templateUrl: './request-manager-zahtjevi.component.html',
  styleUrls: ['./request-manager-zahtjevi.component.css']
})
export class RequestManagerZahtjeviComponent implements OnInit {

  zahtjevi: Request[] = [];
  odabrani_zahtjev: Request; // = new Request();

  feedbackRequests: Array<any>;
  new_feedback: FeedbackRequest = {
    id: 0,
    comment: '',
    date: new Date(),
    user: {
      id: null,
    },
    request: {
      id: null,
    }
  };

  user: User;
  nalogNaziv: String;
  nalogTekst: String;


  prioriteti: Array<Prioritet> = [
    new Prioritet(4, 'Mali'),
    new Prioritet(3, 'Srednji'),
    new Prioritet(2, 'Znacajan'),
    new Prioritet(1, 'Hitan')
  ];

  statusi: Array<Status> = [
    new Status(1, 'Podnesen'),
    new Status(2, 'U toku'),
    new Status(3, 'Rijesen'),
    new Status(4, 'Zatvoren')];

  odabraniPrioritet: Prioritet;
  odabraniStatus: Status;


  constructor(private requestService: RequestService,
              private appService: AppComponent,
              private feedbackRequestService: FeedbackRequestService,
              private router: Router,
              private alert: MatSnackBar,
              private userService: UserService,
              private authService: AuthService) {
    this.odabraniPrioritet = this.prioriteti[0];
    this.odabraniStatus = this.statusi[0];
  }

  ngOnInit(): void {
    this.loadAllRequests();
  }

  loadAllRequests(): void {
    this.requestService.getAllRequests()
      .subscribe((res: Request[]) => {
          for (let i = 0; i < res.length; i++) {
            const copy: Request = Object.assign({}, res[i]);
            this.zahtjevi.push(copy);
          }
        }
      );
  }

  izbrisiZahtjev(id, index) {
    this.requestService.deleteRequest(id).subscribe(() => {
      this.zahtjevi.splice(index, 1);
    });
  }

  prikaziDetalje(zahtjev: Request) {
    this.odabrani_zahtjev = zahtjev;
    this.odabraniStatus = zahtjev.status;
    this.odabraniPrioritet = this.odabrani_zahtjev.adminPriority;
    this.feedbackRequestService.getFeedbacks(zahtjev.id).subscribe((data) => {
      this.feedbackRequests = data;
    });
  }

  kreirajKomentar() {
    this.new_feedback.user.id = this.user.id;
    this.new_feedback.request.id = this.odabrani_zahtjev.id;
    this.feedbackRequestService.createFeedback(this.new_feedback).subscribe(data => {
      console.log(data);
    });
    this.prikaziDetalje(this.odabrani_zahtjev);
  }

  oznaciZahtjevZaNalog(zahtjev: any) {
    this.odabrani_zahtjev = zahtjev;
  }

  podnesiNalog() {
    this.odabrani_zahtjev.radniNalog = this.nalogNaziv + '<br><br> ' + this.nalogTekst;
    this.requestService.updateRequest(this.odabrani_zahtjev).subscribe(
      data => {
        this.alert.open('Nalog kreiran', 'OK', {duration: 5000});
        this.generisiNalog();
      }
    );
  }

  getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
  }

  generisiNalog() {

    const doc = new jsPDF();
    doc.text('Telenash', 100, 10);

    doc.text('Nalog za zahtjeve ', 10, 20);
    doc.line(10, 22, 100, 22); // horizontal line

    doc.text('Naziv i redni broj naloga: ', 10, 35);
    doc.text(this.nalogNaziv, 20, 45);
    // doc.line(10, 48, 100, 48); // horizontal line

    doc.text('Tekst naloga: ', 10, 70);
    doc.text(this.nalogTekst, 20, 80);
    // doc.line(10, 83, 100, 83); // horizontal line

    doc.text('Nalog kreirao: ', 110, 160);
    doc.text(this.appService.getCurrentUserInfo(), 155, 160);
    // doc.line(110, 163, 160, 163); // horizontal line

    const fileName = 'nalog' + this.getRandomInt(1000) + '.pdf';
    doc.save(fileName);
  }

  izmijeniZahtjev() {
    this.requestService.updateRequest(this.odabrani_zahtjev).subscribe((data) => {
      console.log(data);
      // this.loadajStranicu();
    });
  }

  odjaviSe(): void {
    this.appService.odjaviSe();
    document.getElementById('close').click();
  }

}
