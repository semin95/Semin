import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TelenashComponent } from './telenash.component';

describe('TelenashComponent', () => {
  let component: TelenashComponent;
  let fixture: ComponentFixture<TelenashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TelenashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TelenashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
