//
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//
import {Account} from '../account/account';

export class AlarmRule {
    // Raw attributes
    id : number;
    alarmType : number;
    emailToAlert : string;
    high : number;
    low : number;
    // x-to-one
    accountFk : Account;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.alarmType = json.alarmType;
            this.emailToAlert = json.emailToAlert;
            this.high = json.high;
            this.low = json.low;

            if (json.accountFk != null) {
                this.accountFk = new Account(json.accountFk);
            }
        }
    }

    // Utils

    static toArray(jsons : any[]) : AlarmRule[] {
        let alarmRules : AlarmRule[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                alarmRules.push(new AlarmRule(json));
            }
        }
        return alarmRules;
    }
}
