import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {ConnexionService} from 'src/service/connexion.service';

@Injectable({
  providedIn: 'root'
})
export class ResultatService {
  public host:string="http://localhost:8091"
  constructor(private http: HttpClient, private serviceJWT: ConnexionService) { }

  chercher(titre:string){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})
    return this.http.post(this.host+"/cherche", titre,{headers:heards})
  }
  historique(){
    let heards = new HttpHeaders({'authorization':'Bearer '+ this.serviceJWT.jwt})

    return this.http.get(this.host+"/historiques", {headers:heards})
  }
}
