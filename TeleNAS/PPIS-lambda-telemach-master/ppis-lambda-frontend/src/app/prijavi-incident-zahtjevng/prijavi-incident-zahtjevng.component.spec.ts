import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrijaviIncidentZahtjevngComponent } from './prijavi-incident-zahtjevng.component';

describe('PrijaviIncidentZahtjevngComponent', () => {
  let component: PrijaviIncidentZahtjevngComponent;
  let fixture: ComponentFixture<PrijaviIncidentZahtjevngComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrijaviIncidentZahtjevngComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrijaviIncidentZahtjevngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
