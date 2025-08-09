import { FormControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { retry } from 'rxjs';

export class CustomValidator {
  static notOnlyWhiteSpace(control: FormControl): ValidationErrors | null {
    let val: string = control.value;
    if (val != null && val.trim().length === 0) {
      return { notOnlyWhiteSpace: true };
    }
    return null;
  }

  static numeric(control: FormControl): ValidationErrors | null {
    let val: string = control.value;
    if (val === null || val === '') return null;
    if (!isNaN(Number(val))) return { invalidNumber: true };
    return null;
  }
}
