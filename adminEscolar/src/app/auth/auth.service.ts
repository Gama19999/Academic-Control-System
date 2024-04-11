import { Injectable } from "@angular/core";
import { BehaviorSubject, Subject, catchError, tap, throwError } from "rxjs";

import { User } from "../objects/user.model";
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";
import { UserService } from "../user/user.service";
import { Auth } from "../objects/auth.model";

@Injectable({ providedIn: 'root' })
export class AuthService {
  user = new BehaviorSubject<User>(null);
  soonExpiring = new Subject<boolean>();

  result: any;

  private expirationTimer: any;
  private expTime = 600000; // 10 minutes

  constructor(private http: HttpClient, private router: Router, private userService: UserService) { }

  authenticate(authObj: Auth) {
    return this.http.post<User>('http://localhost:8080/auth/' + authObj.userType, authObj).pipe(
      tap((response) => this.handleResponse(response))
    );
  }

  private handleResponse(data: User) {
    this.user.next(data);
    this.userService.setUser(data);
    this.autoLogout();
    localStorage.setItem('userData', JSON.stringify(data));
    this.result = data;
  }

  autoAuth() {
    const localData: User = JSON.parse(localStorage.getItem('userData'));
    if (!localData) return;
    this.user.next(localData);
    this.userService.setUser(localData);
    this.router.navigate(['/', localData.userType]);
    this.autoLogout();
  }

  autoLogout() {
    this.expirationTimer = setTimeout(() => {
      this.logout();
    }, this.expTime);
    setTimeout(() => this.soonExpiring.next(true), this.expTime - 60000);
  }

  extendExpTime() {
    clearTimeout(this.expirationTimer);
    this.expirationTimer = null;
    this.autoLogout();
    this.soonExpiring.next(false);
  }

  logout() {
    this.user.next(null);
    this.router.navigate(['/auth']);
    localStorage.removeItem('userData');
    this.userService.logoutUser();

    if (this.expirationTimer) {
      clearTimeout(this.expirationTimer);
    }
    this.expirationTimer = null;
  }
}
