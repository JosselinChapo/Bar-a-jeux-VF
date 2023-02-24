import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'customDatePipe'
})
export class CustomDatePipe extends DatePipe implements PipeTransform {

  override transform(value: string, args?: any): any {
    let hh = value.substr(0, 2);
    let mm = value.substr(3, 2);
    let time = `${hh}:${mm}`;
    return super.transform(time, "HH:mm");
  }

}
