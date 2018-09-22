import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperaterIncidentiComponent } from './operater-incidenti.component';

describe('OperaterIncidentiComponent', () => {
  let component: OperaterIncidentiComponent;
  let fixture: ComponentFixture<OperaterIncidentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperaterIncidentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperaterIncidentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
