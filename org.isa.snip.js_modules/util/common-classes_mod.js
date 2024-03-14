/*Authored by www.integrating-architecture.de*/

/**
 *  A trivial chain element for building a doubly linked chain.
 */
export class ChainElem {
    name = "";
    next = null;
    previous = null;
    
    constructor(pName, pElementsMap = null) {
        this.name = pName;
        pElementsMap ? pElementsMap.set(this.name, this) : null;
    }

    addNext(pNextElem) {
        this.next = pNextElem;
        pNextElem.previous = this;
        return pNextElem;
    }
    
    toString() {
        return this.name;
    }
}