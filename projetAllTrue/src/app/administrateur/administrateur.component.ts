import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AdminService } from 'src/service/admin.service';

@Component({
  selector: 'app-administrateur',
  templateUrl: './administrateur.component.html',
  styleUrls: ['./administrateur.component.css']
})
export class AdministrateurComponent implements OnInit {
clientsCount:any
clients:any
message:any

constructor(private router:Router, private toastr: ToastrService, private serviceAdmin: AdminService){}
  ngOnInit(): void {
    this.client();
    this.getNumClient();
  }

client(){
  this.serviceAdmin.getClients()
  .subscribe(data=>{
    this.clients = data
    console.log(this.clients)
  }, err=>{
    console.log(err)
  })
}
getNumClient(){
  this.serviceAdmin.nmClient()
  .subscribe(resp=>{
    this.clientsCount = resp

  },err=>{
    console.log(err)
  })
}
onOn(email:String){
  this.serviceAdmin.On(email)
  .subscribe(res=>{
    console.log(res)
    this.message = res
    this.toastr.success('SUCCESS', this.message,{timeOut: 2000,})

  },err=>{
    console.log(err)
  })
}
onOff(email:String){
  this.serviceAdmin.Off(email)
  .subscribe(res=>{
    console.log(res)
    this.message = res
    this.toastr.success('SUCCESS', this.message,{timeOut: 2000,})

  },err=>{
    console.log(err)
  })
}

}
