import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { AuthComponent } from "./auth/auth.component";
import { UserComponent } from "./user/user.component";
import { HomeComponent } from "./user/home/home.component";
import { SignaturesComponent } from "./shared/signatures/signatures.component";
import { AttendanceComponent } from "./shared/attendance/attendance.component";

import { AuthGuard } from './auth/auth.guard';
import { ManageComponent } from './shared/manage/manage.component';
import { EntityComponent } from './shared/manage/entity/entity.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'auth', pathMatch: 'full' },
  { path: 'auth', component: AuthComponent },
  {
    path: ':user', component: UserComponent, canActivate: [AuthGuard],
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'signatures', component: SignaturesComponent },
      { path: 'attendance', component: AttendanceComponent },
      {
        path: 'manage', component: ManageComponent, children: [
          { path: 'students', component: EntityComponent },
          { path: 'teachers', component: EntityComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
