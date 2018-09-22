import React, {Component} from 'react';
import { Form, Select, Input, Button, Divider, DatePicker, InputNumber, Card } from 'antd';
import {Grid} from 'react-bootstrap';
import {Link} from 'react-router-dom';
const FormItem = Form.Item;
const Option = Select.Option;

class AddOrder extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    console.log(JSON.stringify(this.state));
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
    console.log('Pokusaj posta');
    fetch('http://185.91.158.33:8081/rest/rentals/insert', {
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
  }

  state = {
    clientId: '',
    vehicleId: '',
    dateRented: '',
    dateFrom: '',
    dateTo: ''
};

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
    clientId: '',
    vehicleId: '',
    dateRented: '',
    dateFrom: '',
    dateTo: '',
    value: ''
  })
};


  render() {
    const { getFieldDecorator } = this.props.form;
    return (
        <Grid>
          <h1>Insert new order</h1>
        <Divider type="horizontal" />
      <Form onSubmit={this.handleSubmit}>
        <FormItem
          label="Client ID"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
            <Input min={1} max={10000} defaultValue={1} onChange={this.handleChange}
            	name = "clientId"
              placeholder = "Client ID"
              value = {this.state.clientId} 
              onChange={e => this.change(e)} />
        </FormItem>
        <FormItem
          label="Vehicle ID"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
            <Input min={1} max={10000} defaultValue={1}  name = "vehicleId"
              placeholder = "Vehicle ID"
              value = {this.state.vehicleId} 
              onChange={e => this.change(e)}/>
        </FormItem>
        <FormItem
          label="Reservation date"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "dateRented"
              placeholder = "Date rented"
              value = {this.state.dateRented} 
              onChange={e => this.change(e)}/>
        </FormItem>
        <FormItem
          label="Date from"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "dateFrom"
              placeholder = "Date from"
              value = {this.state.dateFrom} 
              onChange={e => this.change(e)}/>
        </FormItem>
        <FormItem
          label="Date to"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "dateTo"
              placeholder = "Date to"
              value = {this.state.dateTo} 
              onChange={e => this.change(e)}/>
        </FormItem>
        <FormItem
          wrapperCol={{ span: 12, offset: 5 }}
        >
        <Link to="/orders">
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

const WrappedApp = Form.create()(AddOrder);

export default WrappedApp

