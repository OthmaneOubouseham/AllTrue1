import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ConnexionService } from 'src/service/connexion.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projetAllTrue';
  utilisateur:any
  photo:any
  constructor(private serviceConnexion: ConnexionService, private sanitizer: DomSanitizer){}
  ngOnInit(): void {
  }


 


  isAuthentificated(){
    return this.serviceConnexion.isAuthenticed()
  }
  isLogout(){
    return this.serviceConnexion.logout()
  }
  isClient(){
    return this.serviceConnexion.isClient()
  }
  isAdmin(){
    return this.serviceConnexion.isAdmin()
  }
  isUtilisateur(){
    return this.serviceConnexion.isUtilisateur()
  }
}
