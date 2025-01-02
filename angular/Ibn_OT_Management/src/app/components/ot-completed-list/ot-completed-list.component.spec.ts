import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtCompletedListComponent } from './ot-completed-list.component';

describe('OtCompletedListComponent', () => {
  let component: OtCompletedListComponent;
  let fixture: ComponentFixture<OtCompletedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OtCompletedListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OtCompletedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
