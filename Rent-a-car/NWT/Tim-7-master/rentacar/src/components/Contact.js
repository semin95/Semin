import React, {Component} from 'react';
import { Form, Select, Input, Button, Divider, DatePicker, InputNumber, Card } from 'antd';
import {Grid} from 'react-bootstrap';
import {Link} from 'react-router-dom';
const FormItem = Form.Item;
const Option = Select.Option;
const { TextArea } = Input;
export default class Contact extends Component{
    
    change = e => {
        console.log('Event. '+e);
      };

    render(){
        return (
            <Grid>
          <h2>Contact us</h2>
        <Divider type="horizontal" />
      <form  method="post" action="mailto:someone@example.com">
        <FormItem
          label="Subject"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 6 }}
        >
            <Input onChange={this.handleChange}
            	name = "subject"
              placeholder = "Enter subject"
              onChange={e => this.change(e)} />
        </FormItem>
        <FormItem
          label="E-mail address"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 6 }}
        >
            <Input  name = "email"
              placeholder = "Enter you e-mail"
              onChange={e => this.change(e)}/>
        </FormItem>
        <FormItem
          label="Message"
          labelCol={{ span: 10 }}
          wrapperCol={{ span: 6 }}
        >
         <TextArea name = "message" autosize
              placeholder = "Enter your message here"
              onChange={e => this.change(e)}/>
        </FormItem>
        <FormItem
          wrapperCol={{ span: 12, offset: 6 }}
        >
          <Button type="primary" htmlType="submit">
            Send e-mail
          </Button>
        </FormItem>
        
      </form>
      </Grid>
        )
    }
}