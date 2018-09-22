import React, {Component} from 'react';
import { Table, Button, Icon, Divider} from 'antd';
import 'antd/dist/antd.css';
import {Link} from 'react-router-dom';

export default class ClientViewForm extends React.Component{


  obrisi(id){
    console.log("OBRISANO"+id);
    console.log('Pokusaj deleta');
      fetch('http://185.91.158.33:8084/account-ms/client/delete/'+id, {
      //fetch('http://localhost:8080/client/delete/'+id, {
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
            title: 'EmailAdress',
            dataIndex: 'emailAdress',
            key: 'emailAdress',
          }, {
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
        fetch('http://185.91.158.33:8084/account-ms/client/all')
        //fetch('http://localhost:8080/client/all')
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
                  emailAdress: rent.emailAdress,
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