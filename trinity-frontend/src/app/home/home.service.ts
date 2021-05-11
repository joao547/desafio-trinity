import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {UserService} from '../util/services/user.service';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  baseUrlCar = '/api/cars';

  constructor(private http: HttpClient,
              private userService: UserService) { }

  listAllCars(id) {
    const header = new Map();
    header.set('userId', id);
    return this.http.get(environment.api_url + this.baseUrlCar, {headers: this.setHeaders(header)});
  }

  private setHeaders(header: Map<string, string>): HttpHeaders {

    const headersConfig = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    };


    if (this.userService.getToken()) {
      headersConfig['Authentication'] = `Bearer ${this.userService.getToken()}`;
    }
    if (header) {
      header.forEach((value, key) => {
        headersConfig[key] = value;
      });
    }
    return new HttpHeaders(headersConfig);
  }

}
