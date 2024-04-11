import { Component, ElementRef, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Term } from '../../../../objects/signature.model';
import { SignatureSavingService } from '../signature-saving.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrl: './score.component.css'
})
export class ScoreComponent implements OnInit, OnDestroy {
  @Input() term: Term;
  @Input() idx: number;
  @ViewChild('idx') input: ElementRef;
  score: number;

  subs: Subscription;

  constructor(private signatureSavingSrv: SignatureSavingService) { }

  ngOnInit(): void {
    this.score = this.term.calif;
    this.subs = this.signatureSavingSrv.submitReq.subscribe(() => this.sendData());
  }

  clear(elem: HTMLInputElement) {
    this.score = 0;
  }

  sendData() {
    let name = this.input.nativeElement.attributes['ng-reflect-name'].nodeValue;

    let data = name.split('-');
    this.signatureSavingSrv.addCalif({
      ssCalifId: +data[0],
      signStudId: +data[1],
      term: +data[2],
      calif: this.score
    });
  }

  ngOnDestroy(): void {
    if (this.subs) this.subs.unsubscribe();
  }
}
