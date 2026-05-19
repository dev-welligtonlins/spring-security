import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAndLoginPage } from './register-and-login-page';

describe('RegisterAndLoginPage', () => {
  let component: RegisterAndLoginPage;
  let fixture: ComponentFixture<RegisterAndLoginPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterAndLoginPage],
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterAndLoginPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
