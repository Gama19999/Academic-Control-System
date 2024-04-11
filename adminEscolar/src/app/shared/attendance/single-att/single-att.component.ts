import { Component, ElementRef, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { User } from '../../../objects/user.model';
import { Term } from '../../../objects/signature.model';
import { AttendanceSavingService } from '../attendance-saving.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-single-att',
  templateUrl: './single-att.component.html',
  styleUrl: './single-att.component.css'
})
export class SingleAttComponent implements OnInit, OnDestroy {
  @Input() studentSignData: {
    student: User,
    terms: Term[]
  }
  @ViewChild('radioP') radioP: ElementRef;
  @ViewChild('radioF') radioF: ElementRef;

  submitSub: Subscription;

  constructor(private attendanceSavingSrv: AttendanceSavingService) { }

  ngOnInit(): void {
    this.submitSub = this.attendanceSavingSrv.submitReq.subscribe(() => this.sendData());
  }

  sendData() {
    let status = this.radioP.nativeElement.checked ? this.radioP.nativeElement.attributes['value'].nodeValue : false;
    let signStudId = this.radioP.nativeElement.attributes['name'].nodeValue.split('-')[1];

    this.attendanceSavingSrv.addAttendance({
      signStudId: signStudId,
      status: status,
      date: new Date().getTime()
    });
  }

  ngOnDestroy(): void {
    this.submitSub.unsubscribe();
  }
}
