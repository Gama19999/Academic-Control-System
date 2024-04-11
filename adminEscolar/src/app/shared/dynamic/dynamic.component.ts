import { Component, Input } from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-dynamic',
  templateUrl: './dynamic.component.html',
  styleUrl: './dynamic.component.css'
})
export class DynamicComponent {
  @Input() title: string;
  @Input() content: string;
  @Input() okButtonText: string;
  @Input() notOkButtonText: string;
  @Input() okButtonAction: () => {};
  @Input() notOkButtonAction: () => {};

  constructor(private authService: AuthService) { }

}
