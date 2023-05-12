import { Component } from '@angular/core';
import { User } from './user';
import { ConnexionService } from 'src/service/connexion.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent {
  userModel = new User("","");
  
  private isAuthenticated:Boolean | undefined;

  constructor(private toastr: ToastrService, private service: ConnexionService, private router:Router){}

  
  onLogin(){
    
    this.service.loginPatient(this.userModel)
    .subscribe(resp=>{
      console.log(resp)
      console.log(resp.headers.get('authorization'))
      let jwt = resp.headers.get('authorization');
      this.service.saveToken(jwt)
      console.log(this.service.email)
      console.log(this.service.roles)
      if(this.service.isAdmin()){
        this.router.navigateByUrl("/admin")
      }else{
        this.router.navigateByUrl("/recherche")
      }
    },err=>{
      console.log(err)
      this.toastr.error('ERROR', "Le mot de passe ou l'email est incorrecte !",{timeOut: 2000,})

    })
  }
  onLogout(){
    this.service.logout();
  }
  isMedecin(){
    return this.service.isClient();
  }
  isAdmin(){
    return this.service.isAdmin();
  }


}
