import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConnexionService } from './connexion.service';
import { Image } from 'src/app/profile/image';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  public host:string="http://localhost:8091"
  public email:any
  constructor(private http: HttpClient, private serviceJWT: ConnexionService) { }

  ajouterImage(image:FormData){
    this.email = this.serviceJWT.email
    let heards = new HttpHeaders({ 'authorization': 'Bearer ' + this.serviceJWT.jwt })
    return this.http.post(this.host+"/uploadImage?email="+this.email, image, { headers: heards })
  }

}
