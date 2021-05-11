import {CanActivate, Router} from '@angular/router';
import {Injectable} from '@angular/core';

@Injectable()
export class PublicPageGuard implements CanActivate {

  constructor(private router: Router,
  ) {}

  canActivate() {
    return true;
  }


}
