import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {UserService} from '../shared/user/user.service';

@Component({
  selector: 'app-userinfo',
  templateUrl: './userinfo.component.html',
  styleUrls: ['./userinfo.component.css']
})

export class UserinfoComponent implements OnInit {

  user: any;

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
	//this.userService.getUserById().subscribe(user => {
		//this.user = user;
//	});
  }
}