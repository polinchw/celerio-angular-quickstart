//
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Template pack-angular:web/src/app/entities/entity-line.component.ts.e.vm
//
import {Component, Input} from '@angular/core';
import {TempReading} from './tempReading';

@Component({
	template: `
        {{ tempReading?.tempType }} 	`,
	selector: 'tempReading-line',
})
export class TempReadingLineComponent {
    @Input() tempReading : TempReading;
}
