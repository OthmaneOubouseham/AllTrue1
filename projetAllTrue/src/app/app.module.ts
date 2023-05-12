import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { RouterModule, Routes } from '@angular/router';
import { ToastNoAnimationModule, ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConnexionComponent } from './connexion/connexion.component';
import { RechercheComponent } from './recherche/recherche.component';
import { AdministrateurComponent } from './administrateur/administrateur.component';
import { ProfileComponent } from './profile/profile.component';

const appRoutes: Routes=[
  {path:'inscription', component: InscriptionComponent},
  {path:'connexion', component: ConnexionComponent},
  {path: 'recherche', component: RechercheComponent},
  {path: 'admin', component: AdministrateurComponent},
  {path:'profil', component:ProfileComponent},
  {path: '' , redirectTo:'/connexion', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    InscriptionComponent,
    ConnexionComponent,
    RechercheComponent,
    AdministrateurComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    // DateTimePickerModule,
    // NgxPaginationModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
