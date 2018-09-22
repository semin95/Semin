import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StatistikaIncidentiComponent} from './statistika-incidenti.component';

describe('StatistikaIncidentiComponent', () => {
  let component: StatistikaIncidentiComponent;
  let fixture: ComponentFixture<StatistikaIncidentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatistikaIncidentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatistikaIncidentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
