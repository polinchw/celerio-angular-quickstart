//
//
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Template pack-angular:web/src/app/entities/entity.service.ts.e.vm
//
import { Injectable } from '@angular/core';
import { HttpModule, Http, Response, Headers, RequestOptions } from '@angular/http';
import { LazyLoadEvent } from 'primeng/primeng';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { MessageService } from '../../service/message.service';
import { PageResponse, PageRequestByExample } from '../../support/paging';
import { Account } from './account';
import { environment } from '../../../environments/environment';

@Injectable()
export class AccountService {

    private options = new RequestOptions({ headers: new Headers({ 'Content-Type': 'application/json' }) });

    constructor(private http: Http, private messageService : MessageService) {}

    /**
     * Get a Account by id.
     */
    getAccount(id : any) : Observable<Account> {
        return this.http.get(environment.url+'/api/accounts/' + id)
            .map(response => new Account(response.json()))
            .catch(this.handleError);
    }

    /**
     * Update the passed account.
     */
    update(account : Account) : Observable<Account> {
        let body = JSON.stringify(account);

        return this.http.put(environment.url+'/api/accounts/', body, this.options)
            .map(response => new Account(response.json()))
            .catch(this.handleError);
    }

    /**
     * Load a page (for paginated datatable) of Account using the passed
     * account as an example for the search by example facility.
     */
    getPage(account : Account, event : LazyLoadEvent) : Observable<PageResponse<Account>> {
        let req = new PageRequestByExample(account, event);
        let body = JSON.stringify(req);

        return this.http.post(environment.url+'/api/accounts/page', body, this.options)
            .map(response => {
                let pr : any = response.json();
                return new PageResponse<Account>(pr.totalPages, pr.totalElements, Account.toArray(pr.content));
            })
            .catch(this.handleError);
    }

    /**
     * Performs a search by example on 1 attribute (defined on server side) and returns at most 10 results.
     * Used by AccountCompleteComponent.
     */
    complete(query : string) : Observable<Account[]> {
        let body = JSON.stringify({'query': query, 'maxResults': 10});
        return this.http.post(environment.url+'/api/accounts/complete', body, this.options)
            .map(response => Account.toArray(response.json()))
            .catch(this.handleError);
    }

    /**
     * Delete an Account by id.
     */
    delete(id : any) {
        return this.http.delete(environment.url+'/api/accounts/' + id).catch(this.handleError);
    }

    // sample method from angular doc
    private handleError (error: any) {
        // TODO: seems we cannot use messageService from here...
        let errMsg = (error.message) ? error.message :
        error.status ? `Status: ${error.status} - Text: ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        if (error.status === 401 ) {
            window.location.href = '/';
        }
        return Observable.throw(errMsg);
    }
}
