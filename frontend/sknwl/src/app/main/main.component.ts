import { Component, OnInit } from '@angular/core';
import { ContentType } from '../content/content-type';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent{
  modeOptions: any[] = [{label: 'Plano de estudo', value: 'guide'}, {label: 'Conteúdo', value: 'content'}];
  modeValue: string = 'content';



  sortValues: string[] = ['Relevância', 'Nº Avaliações', 'Maior nota', 'Mais recente'];
  selectedSort!: string;
}
