import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncidentDetaljiComponent } from './incident-detalji.component';

describe('IncidentDetaljiComponent', () => {
  let component: IncidentDetaljiComponent;
  let fixture: ComponentFixture<IncidentDetaljiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncidentDetaljiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncidentDetaljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
