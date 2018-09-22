import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrijaviIncidentComponent } from './prijavi-incident.component';

describe('PrijaviIncidentComponent', () => {
  let component: PrijaviIncidentComponent;
  let fixture: ComponentFixture<PrijaviIncidentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrijaviIncidentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrijaviIncidentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
