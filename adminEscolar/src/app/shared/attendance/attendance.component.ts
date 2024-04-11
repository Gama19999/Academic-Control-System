import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../../user/user.service';
import { Attendance } from '../../objects/attendance.model';
import { Signature, Term } from '../../objects/signature.model';
import { Subscription } from 'rxjs';
import { User } from '../../objects/user.model';
import { AttendanceSavingService } from './attendance-saving.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrl: './attendance.component.css',
  providers: [AttendanceSavingService]
})
export class AttendanceComponent implements OnInit, OnDestroy {
  attendance: Attendance[] = [];
  signatures: Signature[];
  studentsInSignature: {
    student: User,
    terms: Term[]
  }[] = [];

  savedAtts = 0;
  attSubs = new Subscription;

  visible = false;
  signatureInView = 0;
  userType: string;

  constructor(private userService: UserService, private attendanceSavingSrv: AttendanceSavingService) { }

  ngOnInit(): void {
    this.userType = this.userService.getUser().userType;
    this.signatures = this.userService.signatures;
  }

  showAttendance(data: any) {
    this.attendance = [];

    if (this.signatureInView != data.target['sign-id']) this.visible = false;
    this.signatureInView = data.target['sign-id'];

    if (this.userType == 'student') {
      this.attSubs = this.userService.loadAttendances(data.target['sign-id']).subscribe(
        responseData => {
          for (let item in responseData) {
            this.attendance.push(new Attendance(responseData[item].sign_stud_id, responseData[item].attendance, new Date(responseData[item].date).getTime()));
          }
          if (this.attendance.length == 0) {
            this.attendance.push(new Attendance(null, null, null));
          }
        }
      );
    } else {
      this.attSubs = this.userService.loadStudentsOfSignature(this.signatureInView).subscribe(
        responseData => {
          this.handleStudentsInSignature(responseData);
        }
      );
    }

    this.visible = true;
  }

  saveAttendance() {
    this.attendanceSavingSrv.reset();
    this.attendanceSavingSrv.submitReq.next(null);

    this.savedAtts = this.attendanceSavingSrv.save();
    setTimeout(() => this.savedAtts = 0, 2000);
    this.visible = false;
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

  ngOnDestroy(): void {
    this.attSubs.unsubscribe();
  }
}
