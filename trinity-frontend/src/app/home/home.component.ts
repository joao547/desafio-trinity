import { Component, OnInit } from '@angular/core';
import {UserService} from '../util/services/user.service';
import {HomeService} from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  cars = [];

  constructor(private userService: UserService,
              private homeService: HomeService) { }

  ngOnInit() {
    this.homeService.listAllCars(this.userService.getUser().id).subscribe(data => {
      console.log(data);
    }, error => console.log(error));
  }

}
