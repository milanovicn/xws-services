import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './home/welcome.component';
import { ProfilAdminaModule } from './admin/profil-admina.module';
import { LoginComponent } from './login/login.component';
import { LoginServces } from './login/login.services';
import { SignupComponent } from './login/signup.component';
import { ProfilKorisnikaModule } from './korisnik/profil-korisnika.module';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    SignupComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'welcome', component: WelcomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', redirectTo: 'welcome', pathMatch: 'full'},
      
      // { path: 'homePage', component: HomeComponent }

    ]),
    FormsModule,
    ProfilAdminaModule,
    ProfilKorisnikaModule
  ],
  providers:[LoginServces,],
  bootstrap: [AppComponent]
})
export class AppModule { }
