import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../util/user.model';
import {MatSnackBar} from '@angular/material';
import {Login} from '../util/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseUrlLogin = '/api/signin';
  baseUrlUser = '/api/users';

  constructor(private http: HttpClient,
              private snackBar: MatSnackBar) { }

  showMessage(msg) {
    this.snackBar.open(msg, 'X', {
      duration: 2000,
      horizontalPosition: 'right',
      verticalPosition: 'top'
    });
  }

  createUser(user: User): Observable<any> {
    return this.http.post(environment.api_url + this.baseUrlUser, user);
  }

  login(login: Login): Observable<any> {
    return this.http.post(environment.api_url + this.baseUrlLogin, login);
  }

}
