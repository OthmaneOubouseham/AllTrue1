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
  constructor(private router:Router, private toastr: ToastrService, private serviceResultat: ResultatService){}
  ngOnInit(): void {
    this.getHistorique();
  }


  onCherche(text:any){
    this.serviceResultat.chercher(text)
    .subscribe(res=>{
      console.log(text)
      this.getHistorique()
    },err=>{
      console.log("err: ",err)
    })
  }
  getHistorique(){
    this.serviceResultat.historique()
    .subscribe(data=>{
      console.log(data)
      this.historiques = data

    },err=>{

    })
  }
}
