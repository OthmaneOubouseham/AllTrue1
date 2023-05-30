import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConnexionService } from 'src/service/connexion.service';
import { Image } from './image';
import { ProfileService } from 'src/service/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  fileSelected:any
  fileName:any

  constructor(private toastr: ToastrService, private service: ConnexionService, private router:Router, private profilService:ProfileService){}

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

        },err=>{
          console.log(err)
        })
      }
    }
    
  }


}
