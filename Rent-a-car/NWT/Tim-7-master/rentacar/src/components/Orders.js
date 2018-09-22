import React, {Component} from 'react';
import { Table, Button, Icon, Divider} from 'antd';
import 'antd/dist/antd.css';
import {Link} from 'react-router-dom';

export default class Orders extends Component{
    

  obrisi(id){
    console.log("OBRISANO"+id);
    console.log('Pokusaj deleta');
    fetch('http://185.91.158.33:8081/rest/rentals/delete/'+id, {
      method: 'delete',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
      body: JSON.stringify(this.state)
    }).then(function(response) {
      if(response.status==200){
        alert('Rental successfully deleted');
        window.location.reload();
      }
    });
  }
  dodaj(){
    console.log("DODANO");
  }
    constructor(){
        super();



        this.state={
          rentals:[],
          columns : [{
            title: 'Order ID',
            dataIndex: 'id',
            rowKey: 'id',
          }, {
            title: 'Vehicle ID',
            dataIndex: 'vehicleId',
          },{
            title: 'Client ID',
            dataIndex: 'clientId',
          }, {
            title: 'Reservation date',
            dataIndex: 'datumRezervacije',
          }, {
            title: 'Date start',
            dataIndex: 'rezervisanoOd',
          }, {
            title: 'Date end',
            dataIndex: 'rezervisanoDo',
          },{
            title: 'Action',
            render: (text, record) => (
              <span>
                <Divider type="vertical" />
                <Button onClick={() => this.obrisi(record.id)}>Delete</Button>
                <Divider type="vertical" />
                <Link to="/receipts">
                <Button bsStyle="primary">Check receipt and return for this car</Button>
            </Link> 
              </span>
            )}
        ]
        };
    
      }

    componentDidMount(){
        fetch('http://185.91.158.33:8081/rest/rentals/all')
        .then(
          results => {
          console.log(results);
          //return results.json();  
          return results.json();  }
      ).then(data => {
        console.log(data);
          let rentals=data.map((rent) => {
              console.log('Vehicle id: '+rent.vehicleId);
            return (
              {
                  id: rent.id,
                  clientId: rent.clientId,
                  vehicleId: rent.vehicleId,
                  rezervisanoOd: rent.dateFrom.substring(0, 10),
                  rezervisanoDo: rent.dateTo.substring(0, 10),
                  datumRezervacije: rent.dateRented.substring(0, 10),
              }
            )
          })
        
          this.setState({rentals: rentals});
          console.log("state",this.state.rentals)
          return data;
          
          
        })
      }
      

    render(){
        return (
            <div>
            <Link to="/addorder">
                <Button bsStyle="primary">Add new</Button>
            </Link> 
            <Table  dataSource={this.state.rentals} columns={this.state.columns} >
            </Table>
            </div>
        )
    }
}