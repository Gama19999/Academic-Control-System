import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, NgForm } from '@angular/forms';

import { AuthService } from './auth.service';
import { Auth } from '../objects/auth.model';
import { User } from '../objects/user.model';
import { AuthError } from '../objects/authError.model';

@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent implements OnInit {
  @ViewChild('authForm') authForm: NgForm;

  options = ['student', 'teacher', 'admin'];
  userType = 'student';

  error = null;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.autoAuth();
  }

  onSubmit() {
    if (!this.isValidForm()) return;

    const authSubs = this.authService.authenticate(this.buildAuthObj()).subscribe({
      next: (response: User) => this.toHome(response),
      error: (response) => this.handleError(response)
    });
  }

  private isValidForm() {
    if (!this.authForm.valid) {
      this.error = 'Fill in all fields!';
      setTimeout(() => this.error = '', 2000);
    }
    return this.authForm.valid;
  }

  private buildAuthObj() {
    return new Auth(
      this.authForm.value.idNumber,
      this.authForm.value.nip,
      this.authForm.value.userType
    );
  }

  private toHome(data: User) {
    this.router.navigate(['/', data.userType]);
  }

  private handleError(errors: any) {
    console.log(errors);
    this.error = errors.error;
    setTimeout(() => this.error = '', 2000);
    this.authForm.reset();
  }
}
