import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestManagerZahtjeviComponent } from './request-manager-zahtjevi.component';

describe('RequestManagerZahtjeviComponent', () => {
  let component: RequestManagerZahtjeviComponent;
  let fixture: ComponentFixture<RequestManagerZahtjeviComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RequestManagerZahtjeviComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestManagerZahtjeviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
