/**
 * Created by Влад on 18.03.2017.
 */
class Student{
    fullName: string;

    constructor(public firstName, public middleInitial, public lastName){
        this.fullName = firstName + " " + middleInitial + " " + lastName;
    }
}

interface Person{
    firstName: string;
    lastName: string;
}

function greeter(person: Person){
    return "Hello " + person.firstName + " " + person.lastName;
}

let user = new Student("Aline", "M", "Lee");

document.body.innerHTML = greeter(user);


let decimal: number = 5;

let fullName: string = `Jane Wom`;
let sentense: string = `Of course it is good ${fullName}`;

`I will buy ${decimal}`;

enum color {Blue, Red, Yellow}

let c: color = color.Yellow;

const avg ={
    firstName: "Antonioo",
    numberYear: 56
};

avg.firstName = "Ivan";
avg.numberYear = 35;

let [first, ...next] = [1,2,3,4,5];
console.log(next);

interface LabelValue{
    label: string;
    status?: number;
    [propName: string]: any;
}

function omg(simpleObj: LabelValue){
    console.log(simpleObj.label);
}

let myObj = {size: 10, label: ""};
omg(myObj);

interface test{
    (source: string, des: string) : boolean;
}

let newInt: test;
newInt = function (source, des) {
    let result = source.search(des);
    return result > -1
};

interface Square{
    name: string;
}

interface Shape extends Square{
    length: number;
}

let war = <Shape>{};


class Greeter {
    static standardGreeting = "Hello, there";
    greeting: string;

    greet() {
        if (this.greeting) {
            return "Hello, " + this.greeting;
        }
        else {
            return Greeter.standardGreeting;
        }
    }
}

let greeter1: Greeter;
greeter1 = new Greeter();
console.log(greeter1.greet());

let greeterMaker: typeof Greeter = Greeter;
greeterMaker.standardGreeting = "Hey there!";

let greeter2: Greeter = new greeterMaker();
console.log(greeter2.greet());

let myAdd: (x: number, y: number)=>number =
    function(x: number, y: number): number { return x+y; };





interface Card {
    suit: string;
    card: number;
}

interface Deck {
    suits: string[];
    cards: number[];
    createCardPicker(this: Deck): () => Card;
}




let deck: Deck = {
    suits: ["hearts", "spades", "clubs", "diamonds"],
    cards: Array(52),
    // NOTE: The function now explicitly specifies that its callee must be of type Deck
    createCardPicker: function(this: Deck) {
        return () => {
            let pickedCard = Math.floor(Math.random() * 52);
            let pickedSuit = Math.floor(pickedCard / 13);

            return {suit: this.suits[pickedSuit], card: pickedCard % 13};
        }
    }
};

let cardPicker = deck.createCardPicker();
let pickedCard = cardPicker();

alert("card: " + pickedCard.card + " of " + pickedCard.suit);


class BeeKeeper {
    hasMask: boolean;
}

class ZooKeeper {
    nametag: string;
}

class Animal {
    numLegs: number;
}


class Bee extends Animal {
    keeper: BeeKeeper;
}

class Lion extends Animal {
    keeper: ZooKeeper;
}

function findKeeper<A extends Animal, K> (a: {new(): A;prototype: {keeper: K}}): K {

    return a.prototype.keeper;
}

findKeeper(Lion).nametag;  // typechecks!










