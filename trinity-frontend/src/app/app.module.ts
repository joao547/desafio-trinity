import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatCardModule,
  MatTabsModule,
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatCheckboxModule,
  MatIconModule,
  MatDatepickerModule,
  MatNativeDateModule, MatSnackBarModule, MatListModule
} from '@angular/material';
import { MatMomentDateModule } from "@angular/material-moment-adapter";
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import {PrivatePageGuard} from './util/private-guard.service';
import {PublicPageGuard} from './util/public-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FormsModule,
        MatInputModule,
        MatCardModule,
        MatTabsModule,
        MatFormFieldModule,
        MatButtonModule,
        MatCheckboxModule,
        MatIconModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatMomentDateModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatSnackBarModule,
        MatListModule
    ],
  providers: [
    PrivatePageGuard,
    PublicPageGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
