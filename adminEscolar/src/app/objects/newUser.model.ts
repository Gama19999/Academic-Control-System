import { User } from "./user.model";

export class NewUser {
  constructor(public data: User, public nip: string, public oldNip: string = null) { }
}
