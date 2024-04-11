import { Injectable } from "@angular/core";
import { Term } from "../../../objects/signature.model";
import { Subject } from "rxjs";
import { UserService } from "../../../user/user.service";

@Injectable()
export class SignatureSavingService {
  califs: Term[] = [];
  submitReq = new Subject();

  constructor(private userService: UserService) { }

  addCalif(data: Term) {
    this.califs.push(data);
  }

  save() {
    return this.userService.saveCalifs(this.califs);
  }

  reset() {
    this.califs = [];
  }
}
