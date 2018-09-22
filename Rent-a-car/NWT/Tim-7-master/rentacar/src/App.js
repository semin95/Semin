import React, { Component } from 'react';
import './App.css';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import Home from './components/Home';
import About from './components/About';
import Orders from './components/Orders';
import Contact from './components/Contact';
import Navbar from './components/CustomNavbar';
import Admin from './components/Admin';
import LoginForm from './components/LoginForm';
import Register from './components/Register';
import AddOrder from './components/AddOrder';
import AddVehicle from './components/AddVehicle';
import VehicleViewForm from './components/VehicleViewForm';
import AddSalesman from './components/AddSalesman';
import ClientViewForm from './components/ClientViewForm';
import SalesmanViewForm from './components/SalesmanViewForm';
import Receipts from './components/Receipts';
import AddReceipt from './components/AddReceipt';
import AddReturn from './components/AddReturn';

class App extends Component {


  
  
  render() {
    return (
      <div>
       <Router>
         <div>
           <Navbar />
          <Route exact path="/" component={Home}/>
          <Route path="/about" component={About}/>
          <Route path="/orders" component={Orders}/>
          <Route path="/contact" component={Contact}/> 
          <Route path="/admin" component={Admin}/>  
          <Route path="/login" component={LoginForm}/>  
          <Route path="/register" component={Register}/>
          <Route path="/addorder" component={AddOrder}/>
          <Route path="/addVehicle" component={AddVehicle}/>
          <Route path="/allVehicles" component={VehicleViewForm}/>
          <Route path="/addSalesman" component={AddSalesman}/>
          <Route path="/allClients" component={ClientViewForm}/>
          <Route path="/allSalesmans" component={SalesmanViewForm}/>
          <Route path="/receipts" component={Receipts}/>
          <Route path="/addreceipt" component={AddReceipt}/>
          <Route path="/addreturn" component={AddReturn}/>
        </div>
      </Router>
      </div>
    )
  }
}

export default App;
