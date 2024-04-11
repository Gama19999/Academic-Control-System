import { Component, Input, OnDestroy, ViewChild } from '@angular/core';
import { Signature, Term } from '../../../objects/signature.model';
import { UserService } from '../../../user/user.service';
import { User } from '../../../objects/user.model';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { SignatureSavingService } from './signature-saving.service';
import { DynamicComponent } from '../../dynamic/dynamic.component';

@Component({
  selector: 'app-signature',
  templateUrl: './signature.component.html',
  styleUrl: './signature.component.css',
  providers: [SignatureSavingService]
})
export class SignatureComponent implements OnDestroy {
  @Input() userType: string;
  @Input() signature: Signature;

  @ViewChild('studentsCalifs') studentsCalifs: NgForm;

  studentsInSignature: {
    student: User,
    terms: Term[]
  }[] = [];
  gradesVisibility = false;

  infoComp = null;
  inputs = {
    'title': '',
    'content': '',
    'okButtonText': null,
    'notOkButtonText': null,
    'okButtonAction': () => { this.infoComp = null; this.clearDynamic(); },
    'notOkButtonAction': () => { this.infoComp = null; }
  }

  message = '';
  studentsSubs: Subscription;

  constructor(private userService: UserService, private signatureSavingSrv: SignatureSavingService) { }

  show() {
    if (this.userType == 'teacher') {
      this.fetchStudentsOfSignature();
    }
    this.clearMessage();
  }

  private clearMessage() {
    setTimeout(() => this.message = '', 3000);
    this.gradesVisibility = !this.gradesVisibility;
  }

  private fetchStudentsOfSignature() {
    this.studentsSubs = this.userService.loadStudentsOfSignature(this.signature.signatureId).subscribe(
      responseData => this.handleStudentsInSignature(responseData)
    );
  }

  private handleStudentsInSignature(data: any) {
    this.studentsInSignature = [];
    for (let pair in data) {
      this.studentsInSignature.push({
        student: new User(data[pair].a.studentId, 'student', data[pair].a.name, data[pair].a.lastName, true, null, null, null, data[pair].a.grade),
        terms: [...data[pair].b]
      });
    }
  }

  saveScores() {
    this.signatureSavingSrv.reset();
    this.signatureSavingSrv.submitReq.next(null);
    this.signatureSavingSrv.save();
    this.onDynamicComp();
    this.clearMessage();
  }

  private onDynamicComp() {
    this.message = 'Update success!';
    this.inputs['title'] = 'Update success!';
    this.inputs['content'] = '';
    this.inputs['okButtonText'] = 'Ok';
    this.infoComp = DynamicComponent;
    this.userService.dynamicComp.next({ 'component': this.infoComp, 'inputs': this.inputs });
  }

  private handleError(error: any) {
    console.log(error);
  }

  private clearDynamic() {
    this.userService.dynamicComp.next({
      'component': this.infoComp, 'inputs': {
        'title': '',
        'content': '',
        'okButtonText': null,
        'notOkButtonText': null,
        'okButtonAction': () => { this.infoComp = null; },
        'notOkButtonAction': () => { this.infoComp = null; }
      }
    });
  }

  ngOnDestroy(): void {
    if (!!this.studentsSubs) this.studentsSubs.unsubscribe();
  }
}
