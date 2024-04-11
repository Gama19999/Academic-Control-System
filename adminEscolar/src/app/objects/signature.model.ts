export interface Term {
  ssCalifId: number,
  signStudId: number,
  term: number,
  calif: number
}

export class Signature {
  constructor(public signatureId: number, public name: string, public grade: number, public credits: number, public terms?: Term[]) { }
}
