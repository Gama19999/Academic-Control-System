import { Component, OnInit } from '@angular/core';
import { Signature } from '../../objects/signature.model';
import { UserService } from '../../user/user.service';

@Component({
  selector: 'app-signatures',
  templateUrl: './signatures.component.html',
  styleUrl: './signatures.component.css'
})
export class SignaturesComponent implements OnInit {
  signatures: Signature[];
  userType: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userType = this.userService.getUser().userType;
    this.signatures = this.userService.signatures;
  }
}
