import { Component } from '@angular/core';
import { Navbar } from '../navbar/navbar';
import { RouterOutlet } from '@angular/router';
import { SideBar } from '../sidebar/sidebar';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [Navbar, SideBar, RouterOutlet],
  templateUrl: './main.html',
  styleUrl: './main.scss',
})
export class Main {

}
