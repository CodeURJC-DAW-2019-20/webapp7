import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgoDetailsComponent } from './components/ngo-details/ngo-details.component';


const routes: Routes = [
  {path: 'ngo/:id', component: NgoDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
