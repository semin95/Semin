import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperaterZahtjeviComponent } from './operater-zahtjevi.component';

describe('OperaterZahtjeviComponent', () => {
  let component: OperaterZahtjeviComponent;
  let fixture: ComponentFixture<OperaterZahtjeviComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperaterZahtjeviComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperaterZahtjeviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
