import { Injectable } from "@angular/core";
import { Attendance } from "../../objects/attendance.model";
import { Subject } from "rxjs";
import { UserService } from "../../user/user.service";

@Injectable()
export class AttendanceSavingService {
  attendances: Attendance[] = [];
  submitReq = new Subject();

  constructor(private userService: UserService) { }

  addAttendance(data: Attendance) {
    this.attendances.push(data);
  }

  save() {
    return this.userService.saveAttendances(this.attendances);
  }

  reset() {
    this.attendances = [];
  }
}
