import { Component, OnInit } from '@angular/core';

interface Sort {
  type: string;
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit{
  sortValues: Sort[] | undefined;

  selectedSort: Sort | undefined;

  ngOnInit() {
    this.sortValues = [
      {type: 'Relevância'},
      {type: 'Nº Avaliações'},
      {type: 'Maior nota'},
      {type: 'Mais recente'}
    ];
}
}
