import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgosComponent } from './components/ngos/ngos.component';


const routes: Routes = [
  {path: 'ngos',component: NgosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
