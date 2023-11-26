import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Subject, debounceTime } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  inputValue: string = '';
  private inputSubject = new Subject<string>();

  @Output() valueChanged = new EventEmitter<string>();

  ngOnInit() {
    this.inputSubject.pipe(debounceTime(500)).subscribe(() => {
      // Emit the input value to the parent component
      this.valueChanged.emit(this.inputValue);
    });
  }

  onInput(event: any) {
    // Notify the subject when the user types
    this.inputSubject.next(this.inputValue);
  }
}
