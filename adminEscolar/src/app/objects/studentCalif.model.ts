import { Term } from "./signature.model";
import { User } from "./user.model";

export class StudentCalif {
  constructor(public student: User, public terms: Term[]) { }
}
