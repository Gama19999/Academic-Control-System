export class User {
  constructor(
    public entityId: number,
    public userType: string,
    public name: string,
    public lastName: string,
    public status: boolean,
    public since: number,
    private _token: string,
    private _tokenExpDate: Date,
    public grade?: number,
    public topLevelAcc?: boolean
  ) { }

  get token() {
    if (!this._tokenExpDate || new Date() > this._tokenExpDate) {
      return null;
    }
    return this._token;
  }
}
