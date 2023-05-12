import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormControl,  AbstractControl,    } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConnexionService } from 'src/service/connexion.service';
import { Inscription } from './inscription';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  tabSexe = ["homme", "femme", "non binaire"]
  public registerForm!:FormGroup
  submitted = false
  date:Date= new Date()
  connexionModel = new Inscription("","","","","Client","","","",this.date);
  inscription: Inscription | undefined;

  constructor(private  service:ConnexionService, private router:Router, private toastr: ToastrService, private formBuilder:FormBuilder){}

  ngOnInit(){
    this.registerForm = this.formBuilder.group({
      prenom:['',[Validators.required,Validators.minLength(3)]],
      nom:['',Validators.required],
      adresse:['',Validators.required],
      codePostal:['',Validators.required],
      ville:['',Validators.required],
      email:['',[Validators.required,Validators.email]],
      numTel:['',[Validators.required,Validators.minLength(10)]],
      modePasse:['',[Validators.required,Validators.minLength(8)]]
    })
  }
  onSubmit(){
    this.submitted = true
    if(this.registerForm.invalid){return}
    console.log(this.connexionModel);
    this.service.inscrire(this.connexionModel)
    .subscribe(data=>{
      this.toastr.success('SUCCESS', "vous êtes bien inscrit, merci à bientôt !",{timeOut: 5000,})

      this.router.navigateByUrl("/connexion")

    },err=>{
      console.log("connexion err: ", err)
    })
  }



}
