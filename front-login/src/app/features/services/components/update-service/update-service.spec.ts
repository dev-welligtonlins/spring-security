import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateService } from './update-service';

describe('UpdateService', () => {
  let component: UpdateService;
  let fixture: ComponentFixture<UpdateService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateService],
    }).compileComponents();

    fixture = TestBed.createComponent(UpdateService);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
