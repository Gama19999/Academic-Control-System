import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent implements OnInit {
  userType: string;

  constructor(private authService: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.userType = this.userService.getUser().userType;
  }

  logout() {
    this.authService.logout();
  }
}
