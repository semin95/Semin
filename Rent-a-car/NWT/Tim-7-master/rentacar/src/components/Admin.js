import React, {Component} from 'react';
import { Table, Button, Icon, Divider} from 'antd';
import {Link} from 'react-router-dom';
import 'antd/dist/antd.css';
import VehicleForm from './VehicleForm';
import VehicleViewForm from './VehicleViewForm';
import AddVehicle from './AddVehicle';
import '../style/Admin.css';

export default class Admin extends Component{


	state = {
		fields: {}
	};

	onSubmit = fields => {
		this.setState({fields});
	};


    render(){
        return (

            <div>

            <Link to="/addVehicle">
                <Button id="test" bsStyle="primary">Add vehicle</Button>
            </Link> 

            <Link to="/allVehicles">
                <Button id="test" bsStyle="primary">Show vehicles</Button>
            </Link> 

            <Link to="/addSalesman">
                <Button id="test" bsStyle="primary">Add salesman</Button>
            </Link> 

            <Link to="/allSalesmans">
                <Button id="test" bsStyle="primary">Show salesmans</Button>
            </Link> 

            <Link to="/allClients">
                <Button id="test" bsStyle="primary">Show clients</Button>
            </Link> 

            </div>        

        )
    }
}