import { Component, Input, OnInit, Output, ViewChild } from '@angular/core';
import { User } from '../../../../objects/user.model';
import { NgForm } from '@angular/forms';
import { UserService } from '../../../../user/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { NewUser } from '../../../../objects/newUser.model';
import { DynamicComponent } from '../../../dynamic/dynamic.component';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrl: './item.component.css'
})
export class ItemComponent implements OnInit {
  @Input() entity: User;
  @Input() addingNew: boolean;

  @ViewChild('entityForm') entityForm: NgForm;

  updateMode = false;
  isEqual = true;
  message = '';
  infoVisible = false;

  // NIP properties
  old = '';
  upd = '';
  updConf = '';

  infoComp = null;
  inputs = {
    'title': '',
    'content': '',
    'okButtonText': null,
    'notOkButtonText': null,
    'okButtonAction': () => { this.infoComp = null; this.clearMessage() },
    'notOkButtonAction': () => { this.infoComp = null; this.updateMode = false; this.onDynamicComp() }
  }

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.updateMode = this.addingNew;
    this.infoVisible = this.addingNew;
  }

  show() {
    this.infoVisible = !this.infoVisible;
  }

  check() {
    this.isEqual = this.entityForm.form.controls.nipGroup.value.updConf == this.entityForm.form.controls.nipGroup.value.upd;
  }

  saveItem() {
    if (this.isFormValid()) {
      if (this.addingNew) {
        this.saveNewUser();
      } else {
        this.updateUser();
      }
    } else {
      this.showInvalid();
    }
  }

  private isFormValid() {
    if (this.addingNew || this.updateMode) {
      return this.entityForm.valid && this.isEqual;
    } else {
      return this.entityForm.valid;
    }
  }

  private showInvalid() {
    this.message = "Invalid form!";
    setTimeout(() => this.message = '', 3000);
  }

  private saveNewUser() {
    const nip = this.entityForm.form.controls.nipGroup.value.updConf;
    this.userService.saveEntity(new NewUser(this.entity, nip)).subscribe({
      next: (responseData) => this.handleResponse(responseData),
      error: (errorResponse) => this.handleError(errorResponse)
    });
  }

  private updateUser() {
    this.userService.saveEntity(new NewUser(this.entity, null)).subscribe({
      next: (responseData) => this.handleResponse(responseData),
      error: (errorResponse) => this.handleError(errorResponse)
    });
  }

  private handleResponse(data: any) {
    if (data.isError) {
      this.handleError(data.message + '\n' + data.cause);
      return;
    }
    this.infoVisible = false;
    this.message = data.message;
    this.inputs['title'] = data.message;
    this.inputs['content'] = data.cause;
    this.inputs['okButtonText'] = 'Ok';
    this.inputs['notOkButtonText'] = null;
    this.infoComp = DynamicComponent;
    this.onDynamicComp();
  }

  private handleError(data: any) {
    console.log(data);
    this.inputs['title'] = 'Error!'
    this.inputs['content'] = data.error ? data.error.cause : data;
    this.inputs['okButtonText'] = null;
    this.inputs['notOkButtonText'] = 'Close';
    this.infoComp = DynamicComponent;
    this.onDynamicComp();
  }

  private onDynamicComp() {
    this.userService.dynamicComp.next({ 'component': this.infoComp, 'inputs': this.inputs });
  }

  private clearMessage() {
    this.message = '';
    this.reload();
    this.onDynamicComp();
  }

  private reload() {
    this.router.navigate(['../'], { relativeTo: this.route }).finally(() => {
      this.router.navigate([this.userService.getUser().userType, 'manage', this.entity.userType + 's']);
    });
  }

  deleteItem() {
    if (this.addingNew) {
      this.cancel();
      return;
    }
    this.userService.deleteEntity(this.entity).subscribe(responseData => this.handleResponse(responseData));
    this.infoVisible = !this.infoVisible;
  }

  private cancel() {
    this.message = 'Operation cancelled!'
    this.infoComp = null;
    this.reload();
  }

  updNip() {
    if (this.isFormValid()) {
      const old = this.entityForm.form.controls.nipGroup.value.old;
      const upd = this.entityForm.form.controls.nipGroup.value.updConf;
      this.userService.updateNip(new NewUser(this.entity, upd, old)).subscribe({
        next: (responseData) => this.handleResponse(responseData),
        error: (errorResponse) => this.handleError(errorResponse)
      });
    } else {
      this.showInvalid();
    }
    this.updateMode = !this.updateMode;
    this.entityForm.form.controls.nipGroup.reset();
  }
}

