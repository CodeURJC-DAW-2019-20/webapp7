import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgoDetailsComponent } from './components/ngo-details/ngo-details.component';
import { SearchComponent } from './components/search/search.component';


const routes: Routes = [
  {path: 'ngo/:id', component: NgoDetailsComponent},
  {path: 'search', component: SearchComponent},
  {path: 'search/', component: SearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
