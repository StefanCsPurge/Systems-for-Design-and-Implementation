import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {UserService} from "./shared/user.service";
import {switchMap} from "rxjs/operators";
import {Location} from '@angular/common';
import {Name} from "./shared/name.model";

@Component({
  selector: 'app-userhello',
  templateUrl: './userhello.component.html',
  styleUrls: ['./userhello.component.css']
})
export class UserhelloComponent implements OnInit {
  nameObj: Name;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.route.params
      .pipe(switchMap((params: Params) => this.userService.getUserName(+params['id'])))
      .subscribe(n => {
        this.nameObj = n
      });
  }

}
