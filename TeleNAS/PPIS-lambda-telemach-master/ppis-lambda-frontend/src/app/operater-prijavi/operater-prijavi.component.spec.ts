import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperaterPrijaviComponent } from './operater-prijavi.component';

describe('OperaterPrijaviComponent', () => {
  let component: OperaterPrijaviComponent;
  let fixture: ComponentFixture<OperaterPrijaviComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperaterPrijaviComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperaterPrijaviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
