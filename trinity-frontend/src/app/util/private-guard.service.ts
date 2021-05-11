import {CanActivate, Router} from '@angular/router';
import {Injectable} from '@angular/core';

@Injectable()
export class PrivatePageGuard implements CanActivate {

  constructor(private router: Router,
  ) {}

  canActivate() {
    if (!localStorage.getItem('user')) {
      console.log('entrou: ', !!localStorage.getItem('user'));
      this.router.navigate(['/login']);
    }
    return !!localStorage.getItem('user');
  }


}
