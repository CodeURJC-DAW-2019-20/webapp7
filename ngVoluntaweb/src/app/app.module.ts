import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { OngSettingsComponent } from './components/ong-settings/ong-settings.component';

@NgModule({
  declarations: [
    AppComponent,
    OngSettingsComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
