import { Injectable } from '@angular/core';
import { ConnexionService } from './connexion.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  public host:string="http://localhost:8091"
  constructor(private http: HttpClient, private serviceJWT: ConnexionService) { }

  getClients(){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})

    return this.http.get(this.host+"/clients", {headers:heards})
  }

  changeStatut(email:string){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})

    return this.http.post(this.host+"/offStatut", email, {headers:heards})
  }
  nmClient(){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})

    return this.http.get(this.host+"/countClients", {headers:heards})
  }
  On(email:String){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})

    return this.http.post(this.host+"/onStatut", email, {headers:heards})
  }
  Off(email:String){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})

    return this.http.post(this.host+"/offStatut", email, {headers:heards})
  }

}
