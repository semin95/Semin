import React, {Component} from 'react';
import {Navbar, Nav, NavItem} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export default class CustomNavbar extends Component{
    render(){
        return(
        <Navbar default collapseOnSelect>
            <Navbar.Header>
                <Navbar.Brand>
                    <Link to="/">Rent A Car</Link>
                 </Navbar.Brand>
                 <Navbar.Toggle/>
            </Navbar.Header>
            <Navbar.Collapse>
                <Nav pullRight>
                <NavItem eventKey={1} componentClass={Link}  href="/" to="/">
                Home
                </NavItem>
                <NavItem className={sessionStorage.getItem('loggedRole') == 'admin' ? '' : 'hide-this-bullhit'} eventKey={1} componentClass={Link}  href="/orders" to="/orders">
                Orders
                </NavItem>
                <NavItem eventKey={1} componentClass={Link}  href="/about" to="/about">
                About
                </NavItem>
                <NavItem eventKey={1} componentClass={Link}  href="/contact" to="/contact">
                Contact
                </NavItem>
                <NavItem className={sessionStorage.getItem('loggedRole') == 'admin' ? '' : 'hide-this-bullhit'} eventKey={1} componentClass={Link}  href="/admin" to="/admin">
                Admin
                </NavItem>
                <NavItem className={sessionStorage.getItem('loggedRole') == undefined ? '' : 'hide-this-bullhit'} eventKey={1} componentClass={Link}  href="/login" to="/login">
                Login/Register
                </NavItem>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
        )   
}

}