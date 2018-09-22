import React, {Component} from 'react';
import { Table, Button, Icon, Divider} from 'antd';
import 'antd/dist/antd.css';
import {Link} from 'react-router-dom';

export default class VehicleViewForm extends React.Component{


  obrisi(id){
    console.log("OBRISANO"+id);
    console.log('Pokusaj deleta');
    fetch('http://185.91.158.33:8084/vehicle/rest/vehicle/delete/'+id, {
      //fetch('http://localhost:8080/rest/vehicle/delete/'+id, {
      method: 'delete',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
      body: JSON.stringify(this.state)
    }).then(function(response) {
      if(response.status==200){
        alert('Vehicle successfully deleted');
        window.location.reload();
      }
    });
  }

	constructor(){
        super();

        this.p = 'test';

        this.state={
          rentals:[],
          columns : [{
            title: 'Id',
            dataIndex: 'id',
            rowKey: 'id',
          }, {
            title: 'Brand',
            dataIndex: 'brand',
            key: 'brand',
          }, {
            title: 'Name', 
            dataIndex: 'name',
            key: 'name',
          }, {
            title: 'Type',
            dataIndex: 'type',
            key: 'type',
          }, {
            title: 'Produced year',
            dataIndex: 'producedYear',
            key: 'producedYear',
          }, {
            title: 'Transmission',
            dataIndex: 'transmission',
            key: 'transmission',
          },{
            title: 'Color',
            dataIndex: 'color',
            key: 'color',
          /*},{
            title: 'Available',
            dataIndex: 'available',
            key: 'available',*/
          },{
            title: 'Action',
            render: (text, record) => (
              <span>
                <Divider type="vertical" />
                <Button onClick={() => this.obrisi(record.id)}>Delete</Button>
              </span>
            )}
        ]
        };
    
      }


      componentDidMount(){
        fetch('http://185.91.158.33:8084/vehicle/rest/vehicle/all')
        //fetch('http://localhost:8080/rest/vehicle/all') 
        .then(
          results => {
          console.log(results);
          //return results.json();  
          return results.json();  }
      ).then(data => {
        console.log(data);
          let rentals=data.map((rent) => {

            if(rent.available == true){
              p: 'Dostupan';
            }
            if(rent.available == false){
              p: 'Nedostupan';
            }

            return (
              { 
                  id: rent.id, 
                  brand: rent.brand,
                  name: rent.name,
                  type: rent.type,
                  producedYear: rent.producedYear,
                  transmission: rent.transmission,
                  color: rent.color,
                  //available: this.p,
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
            <Table dataSource={this.state.rentals} columns={this.state.columns} />
            </div>
        )
    }

	

}