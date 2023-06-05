import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ResultatService } from 'src/service/resultat.service';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  historiques:any
  resources:any
  resultat:any
  constructor(private router:Router, private toastr: ToastrService, private serviceResultat: ResultatService){}
  ngOnInit(): void {
    this.getHistorique();
  }


  onCherche(text:any){
    this.serviceResultat.chercher(text)
    .subscribe(res=>{
      console.log(text)
      this.getHistorique()
      this.onGoogleSearch(text)
    },err=>{
      console.log("err: ",err)
    })
  }
  getHistorique(){
    this.serviceResultat.historique()
    .subscribe(data=>{
      console.log(data)
      this.historiques = data
      console.log(this.historiques)

    },err=>{

    })
  }
  onGoogleSearch(text:string){
    this.serviceResultat.googleSearch(text)
    .subscribe(res=>{
      this.resources = res
      console.log(res)
    },err=>{
      console.log("err: ", err)
    })
  }
  onHistorique(titre:string){
    this.serviceResultat.getResultat(titre)
    .subscribe(res=>{
      console.log(res)
      this.resources = res

    }, err=>{
      console.log("err= ", err)
    })
  }
}
