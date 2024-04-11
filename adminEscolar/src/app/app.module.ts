import { NgModule, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PrimeNGConfig } from 'primeng/api';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { UserComponent } from './user/user.component';
import { NavigationComponent } from './user/navigation/navigation.component';
import { HomeComponent } from './user/home/home.component';
import { SignaturesComponent } from './shared/signatures/signatures.component';
import { AttendanceComponent } from './shared/attendance/attendance.component';
import { SignatureComponent } from './shared/signatures/signature/signature.component';
import { ScoreComponent } from './shared/signatures/signature/score/score.component';
import { SingleAttComponent } from './shared/attendance/single-att/single-att.component';
import { ManageComponent } from './shared/manage/manage.component';
import { EntityComponent } from './shared/manage/entity/entity.component';
import { ItemComponent } from './shared/manage/entity/item/item.component';
import { DynamicComponent } from './shared/dynamic/dynamic.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    UserComponent,
    NavigationComponent,
    HomeComponent,
    SignaturesComponent,
    AttendanceComponent,
    SignatureComponent,
    ScoreComponent,
    SingleAttComponent,
    ManageComponent,
    EntityComponent,
    ItemComponent,
    DynamicComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule implements OnInit {

  constructor(private primengConfig: PrimeNGConfig) { }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
  }
}
