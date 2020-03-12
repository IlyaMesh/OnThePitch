import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandingListComponent } from './standing-list.component';

describe('StandingListComponent', () => {
  let component: StandingListComponent;
  let fixture: ComponentFixture<StandingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
