import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {UserService} from './shared/user/user.service';
import {RequestFormComponent} from './request-form/request-form.component';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';

import {
  MatButtonModule,
  MatCardModule,
  MatInputModule,
  MatListModule,
  MatRadioModule,
  MatSelectModule,
  MatSnackBarModule,
  MatToolbarModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {MojeuslugeComponent} from './mojeusluge/mojeusluge.component';
import {PocetnaComponent} from './pocetna/pocetna.component';

import {IncidentComponent} from './incident/incident.component';
import {IncidentService} from './shared/incident/incident.service';
import {IncidentDetaljiComponent} from './incident-detalji/incident-detalji.component';
import {PrijaviIncidentComponent} from './prijavi-incident/prijavi-incident.component';
import {IncidentiComponent} from './incidenti/incidenti.component';
import {ChartModule, HIGHCHARTS_MODULES} from 'angular-highcharts';
import * as exporting from 'highcharts/modules/exporting.src';
import * as noDataToDisplay from 'highcharts/modules/no-data-to-display.src';
import {StatistikaIncidentiComponent} from './statistika/statistika-incidenti/statistika-incidenti.component';
import {StatistikaZahtjeviComponent} from './statistika/statistika-zahtjevi/statistika-zahtjevi.component';
import {ProductService} from './shared/product/product.service';
import {PriorityService} from './shared/priority/priority.service';
import {RequestService} from './shared/request/request.service';

import {MatMenuModule} from '@angular/material/menu';
import {LoginComponent} from './login/login.component';
import {Interceptor} from './core/interceptor';
import {TokenStorage} from './core/token.storage';
import {AuthService} from './core/auth.service';
import {UserinfoComponent} from './User-info/userinfo.component';
import {AuthGuard} from './core/auth.guard';
import {RequestManagerUslugeComponent} from './request-manager-usluge/request-manager-usluge.component';
import {RequestManagerZahtjeviComponent} from './request-manager-zahtjevi/request-manager-zahtjevi.component';
import {IncidentManagerIncidentiComponent} from './incident-manager-incidenti/incident-manager-incidenti.component';
import {PrijaviIncidentZahtjevngComponent} from './prijavi-incident-zahtjevng/prijavi-incident-zahtjevng.component';
import {OperaterZahtjeviComponent} from './operater-zahtjevi/operater-zahtjevi.component';
import {OperaterIncidentiComponent} from './operater-incidenti/operater-incidenti.component';
import {OperaterPrijaviComponent} from './operater-prijavi/operater-prijavi.component';
import {TelenashComponent} from './telenash/telenash.component';
import {SidemenuComponent} from './sidemenu/sidemenu.component';
import {StatusService} from './shared/status/status.service';
import {FeedbackIncidentService} from './shared/feedbackIncident/feedback-incident.service';
import {EditIncidentComponent} from './shared/modals/edit-incident/edit-incident.component';
import {EditRequestComponent} from './shared/modals/edit-request/edit-request.component';
import {DepartmentService} from './shared/department/department.service';
import {EditProductComponent} from './shared/modals/edit-product/edit-product.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/telenash', pathMatch: 'full'},
  {
    path: 'pocetna',
    component: PocetnaComponent, canActivate: [AuthGuard]
  },
  {
    path: 'mojizahtjevi',
    component: RequestFormComponent, canActivate: [AuthGuard]
  },
  {
    path: 'mojeusluge',
    component: MojeuslugeComponent, canActivate: [AuthGuard]
  },
  {
    path: 'incident',
    component: IncidentComponent, canActivate: [AuthGuard]
  },
  {
    path: 'incident/:id',
    component: IncidentDetaljiComponent
  },
  {
    path: 'prijaviIncident',
    component: PrijaviIncidentComponent, canActivate: [AuthGuard]
  },
  {
    path: 'prikaziIncidente',
    component: IncidentiComponent, canActivate: [AuthGuard]
  },
  {
    path: 'statistika/incidenti',
    component: StatistikaIncidentiComponent, canActivate: [AuthGuard]
  },
  {
    path: 'statistika/zahtjevi',
    component: StatistikaZahtjeviComponent, canActivate: [AuthGuard]
  },
  {
    path: 'requestManagerUsluge',
    component: RequestManagerUslugeComponent, canActivate: [AuthGuard]
  },
  {
    path: 'requestManagerZahtjevi',
    component: RequestManagerZahtjeviComponent, canActivate: [AuthGuard]
  },
  {
    path: 'incidentManagerIncidenti',
    component: IncidentManagerIncidentiComponent, canActivate: [AuthGuard]
  },
  {
    path: 'zahtjeviOperater',
    component: OperaterZahtjeviComponent, canActivate: [AuthGuard]
  },
  {
    path: 'incidentiOperater',
    component: OperaterIncidentiComponent, canActivate: [AuthGuard]
  },
  {
    path: 'pocetnaOperater',
    component: OperaterPrijaviComponent, canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'telenash',
    component: TelenashComponent
  },
  {
    path: 'app',
    component: SidemenuComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    RequestFormComponent,
    MojeuslugeComponent,
    PocetnaComponent,
    IncidentComponent,
    IncidentDetaljiComponent,
    PrijaviIncidentComponent,
    IncidentiComponent,
    StatistikaIncidentiComponent,
    StatistikaZahtjeviComponent,
    LoginComponent,
    UserinfoComponent,
    RequestManagerUslugeComponent,
    RequestManagerZahtjeviComponent,
    IncidentManagerIncidentiComponent,
    PrijaviIncidentZahtjevngComponent,
    OperaterZahtjeviComponent,
    OperaterIncidentiComponent,
    OperaterPrijaviComponent,
    TelenashComponent,
    SidemenuComponent,
    EditIncidentComponent,
    EditRequestComponent,
    EditProductComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatRadioModule,
    MatSelectModule,
    MatInputModule,
    MatListModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatMenuModule,
    FormsModule,
    ChartModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    UserService,
    AuthService,
    AuthGuard,
    IncidentService,
    ProductService,
    RequestService,
    TokenStorage,
    PriorityService,
    StatusService,
    DepartmentService,
    FeedbackIncidentService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
    {
      provide: HIGHCHARTS_MODULES,
      useFactory: () => [exporting, noDataToDisplay]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
