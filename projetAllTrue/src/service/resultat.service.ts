import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {ConnexionService} from 'src/service/connexion.service';

@Injectable({
  providedIn: 'root'
})
export class ResultatService {
  public host:string="http://localhost:8091"
  email:any
  constructor(private http: HttpClient, private serviceJWT: ConnexionService) { }

  chercher(titre:string){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})
    return this.http.post(this.host+"/cherche?email="+this.serviceJWT.email, titre,{headers:heards})
  }
  historique(){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})
    this.email = this.serviceJWT.email
    return this.http.get(this.host+"/historiques?email="+this.email, {headers:heards})
  }
  googleSearch(titre:string){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})
    return this.http.get(this.host+"/googleSearch?query="+titre,{headers:heards})
  }
  getResultat(titre:string){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})
    return this.http.get(this.host+"/mesHistoriques?texte="+titre,{headers:heards} )
  }
}
