import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Subject, take, tap } from "rxjs";

import { User } from "../objects/user.model";
import { Signature, Term } from "../objects/signature.model";
import { Attendance } from "../objects/attendance.model";
import { NewUser } from "../objects/newUser.model";

@Injectable({ providedIn: 'root' })
export class UserService {
  private user: User;
  public signatures: Signature[] = [];
  public dynamicComp = new Subject<any>();

  constructor(private http: HttpClient) { }

  setUser(user: User) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }

  logoutUser() {
    this.signatures = [];
  }

  loadSignatures() {
    if (this.user.userType == 'student') {
      this.http.get('http://localhost:8080/signature/student/' + this.user.entityId).pipe(
        take(1),
        tap(responseData => {
          this.handleSignatureOfStudent(responseData);
        })
      ).subscribe();
    } else if (this.user.userType == 'teacher') {
      this.http.get('http://localhost:8080/signature/teacher/' + this.user.entityId).pipe(
        take(1),
        tap(responseData => {
          this.handleSignatureOfTeacher(responseData);
        })
      ).subscribe();
    }
  }

  private handleSignatureOfStudent(data: any) {
    for (let pair of data) {
      let sData = pair.a;
      let terms: Term[] = [];

      terms.push(...pair.b);
      this.signatures.push(new Signature(sData.signatureId, sData.name, sData.grade, sData.credits, terms));
    }
  }

  private handleSignatureOfTeacher(data: any) {
    for (let signature of data) {
      this.signatures.push(new Signature(signature.signatureId, signature.name, signature.grade, signature.credits));
    }
  }

  loadStudentsOfSignature(signatureId: number) {
    return this.http.get('http://localhost:8080/signature/teacher/' + signatureId + '/calif');
  }

  saveCalifs(califs: Term[]) {
    for (let term of califs) {
      this.http.put('http://localhost:8080/calif/update', term).subscribe();
    }
  }

  loadAttendances(signatureId: number) {
    return this.http.get('http://localhost:8080/attendance/student/' + this.user.entityId + '/' + signatureId);
  }

  saveAttendances(atts: Attendance[]) {
    let responses = 0;
    for (let att of atts) {
      this.http.post('http://localhost:8080/attendance/save/today', att).subscribe();
      responses++;
    }
    return responses;
  }

  loadAllEntities(type: string) {
    return this.http.get('http://localhost:8080/admin/list/' + type);
  }

  saveEntity(newUser: NewUser) {
    if (newUser.nip != null) {
      return this.http.post('http://localhost:8080/' + newUser.data.userType + '/add', newUser);
    } else {
      return this.http.put('http://localhost:8080/' + newUser.data.userType + '/update', newUser);
    }
  }

  deleteEntity(data: User) {
    return this.http.delete('http://localhost:8080/' + data.userType + '/delete', { body: data });
  }

  updateNip(updUser: NewUser) {
    return this.http.put('http://localhost:8080/admin/update/nip', updUser);
  }

}
