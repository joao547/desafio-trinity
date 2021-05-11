import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {PrivatePageGuard} from './util/private-guard.service';
import {PublicPageGuard} from './util/public-guard.service';


const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    canActivate: [PublicPageGuard],
    children: [
      {
        path: '',
        component: HomeComponent,
        canActivate: [PrivatePageGuard],
      },
      {
        path: '*',
        redirectTo: ''
      },
      {
        path: 'login',
        component: LoginComponent,
        canActivate: [PublicPageGuard]
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
