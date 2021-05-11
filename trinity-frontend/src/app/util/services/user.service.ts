import { Injectable } from '@angular/core';
import {User} from '../user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getToken(): string {
    if (window.localStorage['token']) {
      return window.localStorage['token'];
    }
    return null;
  }

  getUser(): User {
    if (window.localStorage.user) {
      return JSON.parse(window.localStorage.user);
    }
    return null;
  }

  saveUser(user: User) {
    window.localStorage.user = JSON.stringify(user);
  }


}
