import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgosComponent } from './components/ngos/ngos.component';
import { IndexComponent } from './components/index/index.component';


const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'ngos',component: NgosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
