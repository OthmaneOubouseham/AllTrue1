import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/connexion/user';
import { Inscription } from 'src/app/inscription/inscription';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class ConnexionService {
  public host:string="http://localhost:8091"
  public jwt: String | undefined| any;
  public email: String | undefined;
  public roles:Array<string> | undefined;
  constructor(private http:HttpClient) { }

  public inscrire(data:Inscription){
    return this.http.post(this.host+"/inscription",data)
  }
  saveToken(jwt: string | null) {
    if(jwt != null){
      localStorage.setItem('token', jwt)
      this.jwt = jwt;
      this.parseJWT()
    }
    
  }
  public loginPatient(data: User){
    return this.http.post(this.host+"/login", data, {observe:'response'})
  }

  parseJWT(){
    let jwtHelper = new JwtHelperService();
    if(this.jwt != null){
      let objJWT = jwtHelper.decodeToken(this.jwt);
      console.log('objwt',objJWT)
      this.email = objJWT.sub;
      this.roles = objJWT.roles;
    }
  }
  isClient(){
    if(this.roles != undefined){
      return this.roles.indexOf('Client')>=0;
    }
    return 
  }
  
  isAdmin(){
    if(this.roles != undefined){
      return this.roles.indexOf('Administrateur')>=0;
    }
    return 
  }
  isUtilisateur(){
    if(this.roles != undefined || this.roles != undefined){
      return this.roles.indexOf('admin')>=0;
    }
    return 
  }
  isAuthenticed(){
    return this.roles;
  }
  loadToken(){
    this.jwt = localStorage.getItem('token');
    // mes donnees sont charger à chaque fois alors je suis plus obliger de me identifier à chaque fois 
    this.parseJWT();
  }
  logout(){
    localStorage.removeItem('token');
    this.jwt = undefined;
    this.email = undefined;
    this.roles = undefined;

  }
  initParams(){
    this.jwt = undefined;
    this.email = undefined;
    this.roles = undefined;
  }
}
