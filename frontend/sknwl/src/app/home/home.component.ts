import { Component, OnInit, ViewChild } from '@angular/core';
import { ContentType } from '../content/content-type';
import { CardContentComponent } from '../content/card/card-content.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent{
  modeOptions: any[] = [{label: 'Plano de estudo', value: 'guide'}, {label: 'Conteúdo', value: 'content'}];
  modeValue: string = 'content';

  sortValues: string[] = ['Relevância', 'Nº Avaliações', 'Maior nota', 'Mais recente'];
  selectedSort!: string;

  searchValue?: string;


  onSearchValueChanged(value: string) {
    this.searchValue = value
  }
}
