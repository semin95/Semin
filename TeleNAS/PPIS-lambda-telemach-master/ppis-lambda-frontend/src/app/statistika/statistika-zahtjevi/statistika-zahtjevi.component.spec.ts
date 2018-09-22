import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StatistikaZahtjeviComponent} from './statistika-zahtjevi.component';

describe('StatistikaZahtjeviComponent', () => {
  let component: StatistikaZahtjeviComponent;
  let fixture: ComponentFixture<StatistikaZahtjeviComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatistikaZahtjeviComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatistikaZahtjeviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
