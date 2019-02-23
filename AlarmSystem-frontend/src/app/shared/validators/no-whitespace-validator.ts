/**
 * Created by osama.yousry on 07/01/2018.
 */

import {AbstractControl, ValidatorFn} from '@angular/forms';

export function noWhiteSpace(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
    let isWhiteSpace = false;
    if (control && control.value && control.value.length == 0) {
      isWhiteSpace = false;
    } else if (control && control.value) {
      isWhiteSpace = (control.value || '').trim().length === 0;
    }
    const isValid = !isWhiteSpace;
    return isValid ?
      null : {whitespace: true};
  };
}
