// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import {Component, Input, Output, View} from 'angular2/core';
import {EventEmitter} from "events";

@Component({
    selector: 'todo-cmp',
    templateUrl: "./definition2.html",
    template: `<div>{{checker.check<caret>ed = 1}}</div>`,
})
export class TodoCmp {
    title:string;
    checker:Checker;
}

class Checker {
    checked:boolean;
}
