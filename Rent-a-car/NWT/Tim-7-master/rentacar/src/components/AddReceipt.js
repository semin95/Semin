import React, {Component} from 'react';
import { Form, Select, Input, Button, Divider, DatePicker, InputNumber, Card } from 'antd';
import {Grid} from 'react-bootstrap';
import {Link} from 'react-router-dom';
const FormItem = Form.Item;
const Option = Select.Option;

class AddReceipt extends React.Component {
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(JSON.stringify(this.state));
        this.props.form.validateFields((err, values) => {
          if (!err) {
            console.log('Received values of form: ', values);
          }
        });
        console.log('Pokusaj posta receipts');
        fetch('http://185.91.158.33:8081/rest/receipts/insert', {
          method: 'post',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
          body: JSON.stringify(this.state)
        }).then(function(response) {
          return response.json();
        }).then(function(data) {
          console.log('Created Gist:', data.html_url);
        });
        alert('Receipt successfully addded');
        window.location.replace("/receipts");
      }
  state={
    rental:1,
    dateCreated: '',
    transactionNumber: '',
    price: '',
    discount: ''
 }

onChange(date, dateString) {
  this.setState((prevState) => {
    dateRented: dateString
  });
  console.log()
  console.log(this.state.dateRented);
}

change = e => {
  console.log('Event. '+e);
  this.setState({
    [e.target.name]: e.target.value
  });
};

onSubmit = (e) => {
    e.preventDefault();
    this.props.onSubmit(this.state);
    this.setState({
        rental:1,
        dateCreated: '',
        transactionNumber: '',
        price: '',
        discount: ''
    })
  };
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
        <Grid>
          <h1>Insert Receipt for...</h1>
        <Divider type="horizontal" />

       
      <Form onSubmit={this.handleSubmit}>
                <FormItem label="Date created" labelCol={{ span: 10 }} wrapperCol={{ span: 5 }}>
                <Input min={1} max={10000} defaultValue={1} onChange={this.handleChange}
            	name = "dateCreated"
              placeholder = "Date created"
              value = {this.state.dateCreated} 
              onChange={e => this.change(e)} />
                </FormItem>
                <FormItem label="Transanction number" labelCol={{ span: 10 }} wrapperCol={{ span: 5 }}>
                <Input min={1} max={10000} defaultValue={1} onChange={this.handleChange}
            	name = "transactionNumber"
              placeholder = "Transaction number"
              value = {this.state.transactionNumber} 
              onChange={e => this.change(e)} />
                </FormItem>
                <FormItem label="Price" labelCol={{ span: 10 }} wrapperCol={{ span: 5 }}>
                <Input min={1} max={10000} defaultValue={1} onChange={this.handleChange}
            	name = "price"
              placeholder = "Price"
              value = {this.state.price} 
              onChange={e => this.change(e)} />
                </FormItem>
                <FormItem label="Discount" labelCol={{ span: 10 }} wrapperCol={{ span: 5 }}>
                <Input min={1} max={10000} defaultValue={1} onChange={this.handleChange}
            	name = "discount"
              placeholder = "Discount"
              value = {this.state.discount} 
              onChange={e => this.change(e)} />
                </FormItem>
                <FormItem
          wrapperCol={{ span: 12, offset: 5 }}
        >
        <Link to="/receipts">
                <Button bsStyle="primary">Back</Button>
            </Link>
            <Divider type="vertical" />
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </FormItem>
        </Form>


      </Grid>
    );
  }
}

const WrappedApp = Form.create()(AddReceipt);

export default WrappedApp

