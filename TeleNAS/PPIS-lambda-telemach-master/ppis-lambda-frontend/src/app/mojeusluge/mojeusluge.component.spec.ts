import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MojeuslugeComponent } from './mojeusluge.component';

describe('MojeuslugeComponent', () => {
  let component: MojeuslugeComponent;
  let fixture: ComponentFixture<MojeuslugeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MojeuslugeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MojeuslugeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
