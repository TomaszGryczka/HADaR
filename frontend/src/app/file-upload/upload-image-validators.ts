import {AbstractControl, ValidatorFn} from "@angular/forms";

export function hourRange(from: number, to: number): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const time = control.value;
    if (time) {
      const hour = parseInt(time.toString().split(":")[0]);
      return (hour >= 0 && hour <= to)
      || (hour >= from && hour <= 24) ? null : {hourRange: true};
    }
    return null;
  }
}

export function requiredFileType(type: string[]): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const file = control.value;
    if (file) {
      const extension = file.split('.')[1].toLowerCase();
      const properExtension = type.indexOf(extension);
      if (properExtension != -1) {
        return null;
      }
    }
    return {requiredFileType: true};
  };
}
