//
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Template pack-angular:web/src/app/entities/entity-line.component.ts.e.vm
//
import {Component, Input} from '@angular/core';
import {AlarmRule} from './alarmRule';

@Component({
	template: `
        {{ alarmRule?.emailToAlert }} 	`,
	selector: 'alarmRule-line',
})
export class AlarmRuleLineComponent {
    @Input() alarmRule : AlarmRule;
}