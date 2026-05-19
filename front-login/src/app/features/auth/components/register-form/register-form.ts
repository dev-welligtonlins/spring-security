import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-register-form',
  imports: [],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss',
})
export class RegisterForm {
  @Output() submitForm = new EventEmitter<void>();

  onSubmit() {
    this.submitForm.emit();
  }
}
