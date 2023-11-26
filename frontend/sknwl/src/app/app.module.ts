import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { AvatarModule } from 'primeng/avatar';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { ChipsModule } from 'primeng/chips';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { MultiSelectModule } from 'primeng/multiselect';
import { PaginatorModule } from 'primeng/paginator';
import { SelectButtonModule } from 'primeng/selectbutton';
import { SliderModule } from 'primeng/slider';
import { StyleClassModule } from 'primeng/styleclass';
import { TagModule } from 'primeng/tag';
import { TooltipModule } from 'primeng/tooltip';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CardContentComponent } from './content/card/card-content.component';
import { ContentService } from './content/content.service';
import { ContentFormComponent } from './content/form/content-form.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SidebarComponent } from './sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    CardContentComponent,
    HomeComponent,
    ContentFormComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    InputTextModule,
    AvatarModule,
    DropdownModule,
    CardModule,
    TagModule,
    ButtonModule,
    PaginatorModule,
    HttpClientModule,
    SelectButtonModule,
    MultiSelectModule,
    InputTextModule,
    InputNumberModule,
    ReactiveFormsModule,
    DialogModule,
    StyleClassModule,
    InputTextModule,
    ChipsModule,
    AutoCompleteModule,
    TooltipModule,
    SliderModule,
    CheckboxModule
  ],
  providers: [ContentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
