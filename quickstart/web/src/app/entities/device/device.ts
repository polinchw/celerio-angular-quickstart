//
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//
import {Account} from '../account/account';

export class Device {
    // Raw attributes
    id : number;
    description : string;
    heightAboveSeaLevel : number;
    lat : number;
    longitue : number;
    name : string;
    // x-to-one
    accountFk : Account;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.description = json.description;
            this.heightAboveSeaLevel = json.heightAboveSeaLevel;
            this.lat = json.lat;
            this.longitue = json.longitue;
            this.name = json.name;

            if (json.accountFk != null) {
                this.accountFk = new Account(json.accountFk);
            }
        }
    }

    // Utils

    static toArray(jsons : any[]) : Device[] {
        let devices : Device[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                devices.push(new Device(json));
            }
        }
        return devices;
    }
}