/*Authored by www.integrating-architecture.de*/

/**
 *  A simple chain element to build a double linked, navigatable chain.
 */
export class ChainElem {
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