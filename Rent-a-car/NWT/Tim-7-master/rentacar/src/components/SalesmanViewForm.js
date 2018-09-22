import React, {Component} from 'react';
import { Table, Button, Icon, Divider} from 'antd';
import 'antd/dist/antd.css';
import {Link} from 'react-router-dom';

export default class SalesmanViewForm extends React.Component{


  obrisi(id){
    console.log("OBRISANO"+id);
    console.log('Pokusaj deleta');
    //fetch('http://185.91.158.33:8081/rest/rentals/delete/'+id, {
      fetch('http://185.91.158.33:8084/account-ms/salesman/delete/'+id, {
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
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
          }, {
            title: 'Surname', 
            dataIndex: 'surname',
            key: 'surname',
          }, {
            title: 'Birthdate',
            dataIndex: 'birthDate',
            key: 'birthDate',
          }, {
            title: 'Pay',
            dataIndex: 'pay',
            key: 'pay',
          }, {
            title: 'Transmission',
            dataIndex: 'transmission',
            key: 'transmission',
          },{
            title: 'RentACarOffice',
            dataIndex: 'rentACarOffice',
            key: 'rentACarOffice',
          },{
            title: 'JMBG',
            dataIndex: 'jmbg',
            key: 'jmbg',
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
        fetch('http://185.91.158.33:8084/account-ms/salesman/all')
        //fetch('http://localhost:8080/rest/vehicle/all') 
        .then(
          results => {
          console.log(results);
          //return results.json();  
          return results.json();  }
      ).then(data => {
        console.log(data);
          let rentals=data.map((rent) => {

            return (
              { 
                  id: rent.id, 
                  name: rent.name,
                  surname: rent.surname,
                  birthDate: rent.birthDate,
                  pay: rent.pay,
                  rentACarOffice: rent.rentACarOffice,
                  JMBG: rent.JMBG,
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