import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from '../objects/user.model';
import { UserService } from './user.service';
import { Subscription } from 'rxjs';
import { DynamicComponent } from '../shared/dynamic/dynamic.component';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit, OnDestroy {
  user: User;
  expirationComp = null;

  soonExpSubs: Subscription;
  dynamicCompSubs: Subscription;

  inputs: any;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.userService.getUser();
    this.userService.loadSignatures();
    this.soonExpSubs = this.authService.soonExpiring.subscribe((flag) => {
      this.expirationComp = flag ? DynamicComponent : null;
      this.inputs = {
        'title': 'Current session is about to end!',
        'content': 'Extend session?',
        'okButtonText': 'Extend',
        'notOkButtonText': 'Logout',
        'okButtonAction': () => this.authService.extendExpTime(),
        'notOkButtonAction': () => this.authService.logout()
      }
    });
    this.dynamicCompSubs = this.userService.dynamicComp.subscribe((data) => {
      this.expirationComp = data['component'];
      this.inputs = data['inputs'];
    });
    this.router.navigate(['home'], { relativeTo: this.route });
  }

  ngOnDestroy(): void {
    if (this.soonExpSubs) this.soonExpSubs.unsubscribe();
    if (this.dynamicCompSubs) this.dynamicCompSubs.unsubscribe();
  }
}
