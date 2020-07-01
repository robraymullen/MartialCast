import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  values = '';
  query : string;

  constructor(private router : Router, private activatedRoute : ActivatedRoute, private location: Location) { 
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
  }

  ngOnInit() {
    
    this.activatedRoute.params.subscribe( params => {
      this.query = params['query'];
    }); 
    
  }

  onKey(event: any) { // without type info
    this.values = event.target.value;
  }

  search() {
    this.runSearch();
    return false;
  }

  runSearch() {
    
    this.router.navigate(['/search/'+this.values], { relativeTo: this.activatedRoute });
    //this.query = this.values;
    //let url = this.location.path().replace("push", this.values);
    //this.location.go(url);
    return false;
  }

  keyDownFunction(event) {
    if(event.keyCode == 13) {
      this.runSearch();
    }
  }

}
