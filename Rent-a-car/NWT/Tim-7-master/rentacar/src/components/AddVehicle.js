import React, {Component} from 'react';
import { Form, Select, Input, Button, Divider, DatePicker, InputNumber, Card } from 'antd';
import {Grid} from 'react-bootstrap';
import {Link} from 'react-router-dom';
const FormItem = Form.Item;
const Option = Select.Option;

class AddVehicle extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    console.log(JSON.stringify(this.state));
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
    console.log('Pokusaj posta');
    fetch('http://185.91.158.33:8084/vehicle/rest/vehicle/insert', {
    //fetch('http://localhost:8080/rest/vehicle/insert', {
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
    id: 20,
    brand: '',
    name: '',
    type: '',
    producedYear: '',
    transmission: '',
    color: '',
    available: true
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
    id: 21,
    brand: '',
    name: '',
    type: '',
    producedYear: '',
    transmission: '',
    color: '',
    available: true
  })
};


  render() {
    const { getFieldDecorator } = this.props.form;
    return (
        <Grid>
          <h1>Insert new vehicle</h1>
        <Divider type="horizontal" />
      <Form onSubmit={this.handleSubmit}>
       
       
        <FormItem
          label="Brand"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "brand"
              placeholder = "Brand"
              value = {this.state.brand} 
              onChange={e => this.change(e)}/>
        </FormItem>
        
        <FormItem
          label="Name"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "name"
              placeholder = "Name"
              value = {this.state.name} 
              onChange={e => this.change(e)}/>
        </FormItem>
        
        <FormItem
          label="Type"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "type"
              placeholder = "Type"
              value = {this.state.type} 
              onChange={e => this.change(e)}/>
        </FormItem>

        <FormItem
          label="Produced year"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
            <Input min={1} max={10000} defaultValue={1}  name = "producedYear"
              placeholder = "Produced year"
              value = {this.state.producedYear} 
              onChange={e => this.change(e)}/>
        </FormItem>

        <FormItem
          label="Transmission"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "transmission"
              placeholder = "Transmission"
              value = {this.state.transmission} 
              onChange={e => this.change(e)}/>
        </FormItem>

        <FormItem
          label="Color"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 3 }}
        >
         <Input name = "color"
              placeholder = "Color"
              value = {this.state.color} 
              onChange={e => this.change(e)}/>
        </FormItem>
       
        <FormItem
          wrapperCol={{ span: 12, offset: 5 }}
        >
        <Link to="/admin">
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

const WrappedApp = Form.create()(AddVehicle);

export default WrappedApp


