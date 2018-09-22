import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestManagerUslugeComponent } from './request-manager-usluge.component';

describe('RequestManagerUslugeComponent', () => {
  let component: RequestManagerUslugeComponent;
  let fixture: ComponentFixture<RequestManagerUslugeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RequestManagerUslugeComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestManagerUslugeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
