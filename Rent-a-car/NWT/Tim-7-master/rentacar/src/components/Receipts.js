import React, {Component} from 'react';
import { Table, Button, Icon, Divider, Row, Col,Form } from 'antd';
import 'antd/dist/antd.css';
import {Link} from 'react-router-dom';
import {Jumbotron} from 'react-bootstrap';
const FormItem = Form.Item;

const divStyle = {
    height: '500px'
  };

export default class Receipts extends Component{

   
    constructor(){
        super();
        console.log('JEDAN');
        this.state={
           rentalId: 1,
           receiptId: '',
           receiptDateCreated: '',
           receiptTransactionNumber: '',
           receiptPrice: '',
           receiptDiscount: '',
           returnId: '',
           returnDate: '',
           returnReason: ''
        }
      }
      obrisiReceipt(){
        console.log('Pokusaj deleta');
        fetch('http://185.91.158.33:8081/rest/receipts/delete/'+this.state.receiptId, {
          method: 'delete',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
          body: JSON.stringify(this.state)
        }).then(function(response) {
          if(response.status==200){
            alert('Receipt successfully deleted');
            window.location.reload();
          }
        });
      }
      obrisiReturn(){
        console.log('Pokusaj deleta');
        fetch('http://185.91.158.33:8081/rest/returns/delete/'+this.state.returnId, {
          method: 'delete',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
          body: JSON.stringify(this.state)
        }).then(function(response) {
          if(response.status==200){
            alert('Return successfully deleted');
            window.location.reload();
          }
        });
      }

      componentDidMount(){
        fetch('http://185.91.158.33:8081/rest/receipts/rental/'+this.state.rentalId)
        .then(
          results => {
          console.log(results);
          //return results.json();  
          return results.json();  }
      ).then(data => {
        console.log(data);
        /*
          let rentals=data.map((rent) => {
              console.log('Vehicle id: '+rent.vehicleId);
            return (
              {
                  id: rent.id,
                  dateCreated: rent.dateCreated,
                  transactionNumber: rent.transactionNumber,
                  price: rent.price,
                  discount: rent.discount
              }
            )
          }  
        )
        */
         // this.setState({rentals: rentals});
         if(data==null) console.log('Data je null');
         else if(data.id==null) console.log('Receipt je null');
         else
        this.setState(
            {
                receiptId: data.id,
                receiptDateCreated: data.dateCreated.substring(0, 10),
                receiptTransactionNumber: data.transactionNumber,
                receiptPrice: data.price,
                receiptDiscount: data.discount
            }
        )
          console.log("state",this.state.receiptId)
          
          
        });
        fetch('http://185.91.158.33:8081/rest/returns/rental/'+this.state.rentalId)
        .then(
          results => {
          console.log(results);
          //return results.json();  
          return results.json();  }
      ).then(data => {
        console.log(data);
        /*
          let rentals=data.map((rent) => {
              console.log('Vehicle id: '+rent.vehicleId);
            return (
              {
                  id: rent.id,
                  dateCreated: rent.dateCreated,
                  transactionNumber: rent.transactionNumber,
                  price: rent.price,
                  discount: rent.discount
              }
            )
          }  
        )
        */
         // this.setState({rentals: rentals});
         if(data==null) console.log('Data je null');
         else if(data.id==null) console.log('Return je null');
         else
         this.setState({
            returnId: data.id,
            returnDate: data.dateReturn.substring(0,10),
            returnReason: data.reason
         });
          
          
        })
      }
      
    render(){
        const button = this.state.receiptId.length!=0 ? (
            <Button type="primary" onClick={() => this.obrisiReceipt()}>Delete</Button>
          ) : (
            <Link to="/addreceipt">
            <Button type="primary">Add receipt</Button>
            </Link>
          );
          const button2 = this.state.returnId.length!=0 ? (
            <Button type="primary" onClick={() => this.obrisiReturn()}>Delete</Button>
          ) : (
            <Link to="/addreturn">
            <Button type="primary">Add return</Button>
            </Link>
          );

        return (
            <div>
                <h2>Details of rental with id: {this.state.rentalId}</h2>
               <Row>
                <Col span={12}>
                <Jumbotron style={divStyle}>
                <FormItem label="Receipt id" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.receiptId}
                </FormItem>
                <FormItem label="Date created" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.receiptDateCreated}
                </FormItem>
                <FormItem label="Price" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.receiptPrice}
                </FormItem>
                <FormItem label="Discount" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.receiptDiscount}
                </FormItem>
                <FormItem label="Transaction number" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.receiptTransactionNumber}
                </FormItem>

                {button}
                
                </Jumbotron>
                </Col>
                <Col span={12}>
                <Jumbotron style={divStyle}>
                <FormItem label="Return id" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.returnId}
                </FormItem>
                <FormItem label="Return date" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.returnDate}
                </FormItem>
                <FormItem label="Reason" labelCol={{ span: 10 }} wrapperCol={{ span: 3 }}>
                    {this.state.returnReason}
                </FormItem> 
                {button2}              
                </Jumbotron>
                </Col>
    </Row>
            </div>
        )
    }
}