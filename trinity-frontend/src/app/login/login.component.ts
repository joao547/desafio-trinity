import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { MAT_DATE_FORMATS } from '@angular/material';
import { LoginService } from './login.service';
import {Route, Router} from '@angular/router';
import {UserService} from '../util/services/user.service';

export const DD_MM_YYYY_Format = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [{ provide: MAT_DATE_FORMATS, useValue: DD_MM_YYYY_Format }]
})
export class LoginComponent implements OnInit {



  dateOfBirthday = new FormControl();

  constructor(private loginService: LoginService,
              private formBuilder: FormBuilder,
              private router: Router,
              private userService: UserService) { }

  user = this.formBuilder.group({
    birthday: '',
    email: '',
    firstName: '',
    lastName: '',
    login: '',
    phone: '',
    password: ''
  });

  login = this.formBuilder.group({
    login: '',
    password: ''
  });

  ngOnInit() {
  }

  cadastrar() {
    this.loginService.createUser(this.user.value).subscribe(data => {
      console.log(data);
      this.user.reset();
      this.loginService.showMessage('Usuário criado com sucesso');
      this.router.navigate(['/login']);
    }, error => {
      this.loginService.showMessage('Erro ao criar usuário');
    });
  }

  loginUser() {
    this.loginService.login(this.login.value).subscribe( (data) => {
      this.login.reset();
      console.log('data: ', data);
      localStorage.setItem('token', data.token);
      this.userService.saveUser(data.user);
      this.router.navigate(['']);
    }, error => {
      this.login.reset();
      this.loginService.showMessage('Erro ao logar');
    });
  }

}
