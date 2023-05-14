import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConnexionService } from 'src/service/connexion.service';
import { Image } from './image';
import { ProfileService } from 'src/service/profile.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  ngOnInit(): void {
    this.getUtilisateur()
  }

  fileSelected:any
  fileName:any
  profile:any
  file:any

  constructor(private toastr: ToastrService, private service: ConnexionService, private router:Router, private profilService:ProfileService, private sanitizer: DomSanitizer){}

  onFileSelected(event: any){
    this.fileSelected = event.target.files;
    this.onEnvoyer()
  }

  onEnvoyer(){
    if(this.fileSelected){
      const file:File = this.fileSelected[0]
      if(file){
        this.fileName = file.name
        const formData = new FormData();
        formData.append("image", file);
        const image = new Image(formData, this.service.email);
        this.profilService.ajouterImage(formData)
        .subscribe(resp=>{
          this.toastr.success('SUCCESS', 'la photo a bien été téléchargée !',{timeOut: 5000,})
          this.getUtilisateur()

        },err=>{
          console.log(err)
          this.getUtilisateur()

        })
      }
    }
    
  }
  getUtilisateur(){
    this.profilService.getProfile()
    .subscribe((resp:any)=>{
      if(resp.file){
        this.file = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/png;base64,'+resp.file);
      }
      this.profile = resp.profile
      console.log(this.file)
      console.log(this.profile)
    },err=>{
      console.log("err download profile with image ", err)
    })
  }



}
