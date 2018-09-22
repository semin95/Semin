import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncidentManagerIncidentiComponent } from './incident-manager-incidenti.component';

describe('IncidentManagerIncidentiComponent', () => {
  let component: IncidentManagerIncidentiComponent;
  let fixture: ComponentFixture<IncidentManagerIncidentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncidentManagerIncidentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncidentManagerIncidentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
