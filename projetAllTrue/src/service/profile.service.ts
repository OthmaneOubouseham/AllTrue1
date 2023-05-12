import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConnexionService } from './connexion.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  public host:string="http://localhost:8091"
  constructor(private http: HttpClient, private serviceJWT: ConnexionService) { }
}
