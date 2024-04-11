import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../../objects/user.model';
import { UserService } from '../../../user/user.service';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrl: './entity.component.css'
})
export class EntityComponent implements OnInit {
  entity: string;
  items: User[] = [];

  addingNew = false;
  newUser: User = new User(0, null, 'Name', 'Last Name', true, new Date().getTime(), null, null, null, false);

  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.route.url.subscribe(url => this.entity = url[0].path);
    this.newUser.userType = this.entity.substring(0, this.entity.length - 1);
    this.userService.loadAllEntities(this.entity).subscribe(
      responseData => {
        this.handleEntities(responseData);
      }
    );
  }

  private handleEntities(data: any) {
    let userType = this.entity.substring(0, this.entity.length - 1);
    for (let item of data) {
      if (userType == 'student') {
        this.items.push(new User(item.studentId, userType, item.name, item.lastName, item.status, new Date(item.since).getTime(), null, null, item.grade));
      } else {
        this.items.push(new User(item.teacherId, userType, item.name, item.lastName, item.status, new Date(item.since).getTime(), null, null, item.ofGrade));
      }
    }
  }
}
